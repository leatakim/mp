package my.first.project.member.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import my.first.project.member.model.service.MemberService;
import my.first.project.member.model.vo.Member;

@SessionAttributes({"loginMember"})
@Controller
public class KakaoController {

	@Autowired
	private MemberService service;
	
	private Logger logger = LoggerFactory.getLogger(KakaoController.class); 
	

	@GetMapping("/member/kakaoLogin")
	@ResponseBody
	public String getKakaoAuthUrl(HttpServletRequest request) throws Exception {
		
		String reqUrl = 
						"https://kauth.kakao.com/oauth/authorize"
						+ "?client_id=650534ca42e8dabf8bdeb6a0405f98a7"
						+ "&redirect_uri=http://localhost:8082/MP/member/kakaoLogin2"
						+ "&response_type=code";
		
		return reqUrl;
	}
	
	// 카카오 연동정보 조회 -> 카카오 이메일/프로필사진/이름 조회
	@RequestMapping(value = "/member/kakaoLogin2")
	public String kakaoLogin2(@RequestParam(value = "code", required=false) String code,
							  Model model,
							  HttpServletRequest req,
							  HttpServletResponse resp
							  ) throws Exception {
		
		String message = null;
		
		// 카카오에서 제공하는 토큰가지고 조회 하는것
		String access_Token = getAccessToken(code);
		
		HashMap<String, Object> userInfo = getUserInfo(access_Token);
		
		System.out.println("###access_Token#### : " + access_Token);
		System.out.println("###userInfo#### : " + userInfo.get("email"));
		System.out.println("###nickname#### : " + userInfo.get("nickname"));
		System.out.println("###profileImage#### : " + userInfo.get("profileImage"));
		
		// 1. 해당 이메일에 비밀번호가 있는가?
        // 2. --> 있다면, 이미 사이트에서 가입한 이력이 있음.
        //        --> 사이트에 가입한 이메일로 가입하시오. 
        // 3. --> 없다면, sns로그인한 사람이니까.
        //        --> 회원테이블에 일치하는 이메일이 있는지 확인!
        // 4. 일치하는 회원이 있다면,
        //    --> else 로 그냥 로그인 고
        // 5. 일치하는 회원이 없다면, 
        //    --> 회원 넘버 삽입 
		
		if(userInfo != null) {
			System.out.println("here1");
			String kakaoEmail = (String)userInfo.get("email");
			String nickname = (String)userInfo.get("nickname");
			String profileImage = (String)userInfo.get("profileImage");
			System.out.println("kakaoEmail : " + kakaoEmail);
			
			// 카카오 아이디(이메일) 일치하는 회원 정보를 조회하는 Service 호출 후 결과 반환 받기
			Member kakaoEmailCheck = service.kakaoEmailCheck(kakaoEmail);
			logger.info("kakaoEmailCheck" + kakaoEmailCheck);
			
			if(kakaoEmailCheck != null) {
				System.out.println("here2");
				if(kakaoEmailCheck.getMemberPw() != null) {
					System.out.println("here3");
					
					// 사이트 회원가입 - 사이트 로그인
					message = "홈페이지로 회원가입한 회원입니다.";
					return "redirect:/member/login-page";
				
				} else {
					System.out.println("here4");
					kakaoEmailCheck.setProfileImage(profileImage);
					kakaoEmailCheck.setMemberName(nickname);
					
					/// 회원 정보 JSON 형태로 저장
					kakaoEmailCheck.setMemberJson(new Gson().toJson(kakaoEmailCheck));
					
					// 카카오 로그인 했었던 사람
					model.addAttribute("loginMember", kakaoEmailCheck);
					
					return "redirect:/";
				}
			} else {
				
				System.out.println("before insert");
				// 카카오 이메일, 이름, 프로필 이미지 (왜 null?) 회원 테이블에 삽입
				Member mem = new Member();
				mem.setMemberEmail(kakaoEmail);
				mem.setMemberName(nickname);
				mem.setProfileImage(profileImage);
				
				int result = service.insertNo(mem);
				
				if(result > 0) {
					mem.setMemberJson(new Gson().toJson(mem));
					model.addAttribute("loginMember", mem);
					message = "카카오 로그인 성공";
					
					return "redirect:/";
					
				} else {
					System.out.println("before insert fail");
					message = "카카오 로그인 실패";
					return "redirect:/member/login-page"; // 회원 로그인 페이지
				}
			}
		}
		
		return "redirect:/";
	}

	
	
	private HashMap<String, Object> getUserInfo(String access_Token) {
		
		System.out.println("getUserInfo1");
		//요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {

            // HttpHeader 오브젝트 생성
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + access_Token);
            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
            RestTemplate rt = new RestTemplate();
            HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

            // Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
            ResponseEntity<String> response = rt.exchange(
                  reqURL,
                    HttpMethod.POST,
                    kakaoProfileRequest,
                    String.class
            );
            
            
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(response.getBody());

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String profileImage = properties.getAsJsonObject().get("profile_image").getAsString();
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
            
            userInfo.put("accessToken", access_Token);
            userInfo.put("nickname", nickname);
            userInfo.put("email", email);
            userInfo.put("profileImage", profileImage);
            
            System.out.println("access_Token : " + access_Token);
            System.out.println("profileImage : " + profileImage);
            System.out.println("nickname : " + nickname);
            System.out.println("email : " + email);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userInfo;
	}
	

	//토큰발급
	public String getAccessToken (String authorize_code) {
		
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //  URL연결은 입출력에 사용 될 수 있고, POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //	POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=650534ca42e8dabf8bdeb6a0405f98a7");  //본인이 발급받은 key
            sb.append("&redirect_uri=http://localhost:8082/MP/member/kakaoLogin2"); // 본인이 설정해 놓은 경로
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }
}
