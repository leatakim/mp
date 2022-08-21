package my.first.project.member.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import my.first.project.common.filter.InitFilter;
import my.first.project.member.model.service.MemberService;
import my.first.project.member.model.vo.Member;

@SessionAttributes({"loginMember"})
@Controller
@RequestMapping("member/*")
public class MemberController {

	@Autowired
	private MemberService service;
	
	private Logger logger = LoggerFactory.getLogger(InitFilter.class);
	
	// 회원가입 화면 전환
	@GetMapping("signUp-page")
	public String signUp() {
		return "member/signUp";
	}
	
	// 회원가입
	@PostMapping("sign-up")
	public String signUp(Member member,
					     String[] memberAddress, // 회원 주소
					     @RequestParam("uploadImage") MultipartFile uploadImage, // 프로필 사진
					     @RequestParam Map<String, Object> map, // 프로필 사진 삭제여부
					     HttpServletRequest req, // 파일 저장 경로 탐색시 사용
						 RedirectAttributes ra) throws IOException {
		
		logger.info("here2");
		logger.info("here2");
		logger.info("here2");
		// 주소에 구분자 ",," 추가
		member.setMemberAddress( String.join(",,", memberAddress));
		
		// 주소 입력이 안 된 경우 (",,,,") null 세팅
		if(member.getMemberAddress().equals(",,,,")) member.setMemberAddress(null);
		
		// 프로필 이미지를 위한 웹 접근경로, 서버 저장경로 선언
		String webPath = "/resources/images/memberProfile/";
		String folderPath = req.getSession().getServletContext().getRealPath(webPath);
		
		// 전부 map에 담기
		map.put("member", member);
		map.put("webPath", webPath );
		map.put("folderPath", folderPath );
		
		logger.info(map.toString());
		
		// 회원가입 서비스 호출
		int result = service.signUp( map, uploadImage );
		
		if(result>0) {
			ra.addFlashAttribute("message", "회원가입 완료");
		}else {
			ra.addFlashAttribute("message", "회원가입 실패");
		}
		
		
		return "redirect:/";
	}
	
	// 이메일 중복 확인
	@ResponseBody
	@GetMapping("email-reduplicate-check")
	public int duplicateEmail(@RequestParam("memberEmail") String memberEmail) {
		return service.emailReduplicateCheck(memberEmail);
	}
	
	// 이메일 인증 번호 발송
	@ResponseBody
	@GetMapping("sendEmail")
	public int sendEmail(@RequestParam("inputEmail") String memberEmail,
						 @RequestParam("flag") int flag) {
		
		logger.info(memberEmail);
		logger.info("flag : " + flag);
		
		String subject = "MP 인증 번호 안내";
		String fromEmail = "shellfish2d@gmail.com";
		String fromUsername = "관리자";
		String toEmail = memberEmail; // 받는사람, 콤마(,)로 여러개 나열 가능

		final String smtpEmail = "shellfish2d@gmail.com"; // 이메일 (관리자 메일)
		final String password = "ekeugwouumtnicjy"; // 발급 받은 비밀번호

		// 메일 옵션 설정
		Properties props = new Properties();
		
		// 중요
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587"); //465, 587
		props.put("mail.smtp.auth", "true");

		// 추가 옵션
		props.put("mail.smtp.quitwait", "false");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "true");
		props.put("mail.smtp.starttls.enable", "true");

		try {
			// 메일 세션 생성
			Session session = Session.getDefaultInstance(props);

			// 메일 송/수신 옵션 설정(1명 보내기)
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail, fromUsername)); 	// 송신자(보내는 사람) 지정
			message.addRecipient(RecipientType.TO, new InternetAddress(toEmail)); // 수신자(받는사람) 지정
			message.setSubject(subject); // 이메일 제목 지정
			
			// 메일 콘텐츠 설정
			Multipart mParts = new MimeMultipart();
			MimeBodyPart mTextPart = new MimeBodyPart();

			// 인증번호 6자리 생성코드(영어 대/소문 + 숫자)
			String cNumber = "";
			for(int i=0 ; i< 6 ; i++) {
				
				int sel1 = (int)(Math.random() * 3); // 0:숫자 / 1,2:영어
				
				if(sel1 == 0) {
					int num = (int)(Math.random() * 10); // 0~9
					cNumber += num;
				}else {
					char ch = (char)(Math.random() * 26 + 65); // A~Z
					int sel2 = (int)(Math.random() * 2); // 0:소문자 / 1:대문자
					if(sel2 == 0) {
						ch = (char)(ch + ('a' - 'A')); // 대문자로 변경
					}
					cNumber += ch;
				}
			}
			
			// 메일에 출력할 텍스트
			StringBuffer sb = new StringBuffer(); // 가변성 문자열 저장 객체
			sb.append("<h3>인증 번호 </h3>\n");
			sb.append("<h3>인증 번호 : <span style='color:red'>"+ cNumber +"</span></h3>\n");
			
			String mailContent = sb.toString(); // 문자열로 반환
			
			// 메일 콘텐츠 - 내용 , 메일인코딩, "html" 추가 시 HTML 태그가 해석됨
			mTextPart.setText(mailContent, "UTF-8", "html");
			mParts.addBodyPart(mTextPart);
			
			// 메일 콘텐츠 지정
			message.setContent(mParts);

			
			// 메일 발송
			Transport t = session.getTransport("smtp");
			t.connect(smtpEmail, password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			
			int result = service.selectCertification(memberEmail);
			
			logger.info("result : " + result);
			
			if(flag == 0 && result == 0) {
				result = service.insertCertification(memberEmail, cNumber);
			}
			if(result == 1) {
				result = service.updateCertification(memberEmail, cNumber);
			}
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	@ResponseBody
	@GetMapping("checkNumber")
	public int emailMemeberCheck(@RequestParam("memberEmail") String memberEmail,
								 @RequestParam("cNumber") String cNumber) {
		logger.info("memberEmail : " + memberEmail);
		logger.info("cNumber : " + cNumber);
		
		return service.emailMemberCheck(memberEmail, cNumber);
	}
	
	//////////////////////////////////////////////////////////////////
	
	// 로그인 화면 전환
	@GetMapping("login-page")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("login")
	public String login(@ModelAttribute Member inputMember,
						@RequestParam(value="saveId", required=false) String saveId,
						RedirectAttributes ra,
						HttpServletRequest req,
						HttpServletResponse resp,
						Model model) {
		
		Member loginMember = service.login(inputMember);
		
		logger.info("loginMember : " + loginMember);
		
		if(loginMember != null) {
			model.addAttribute("loginMember", loginMember);
			
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			
			if(saveId != null) {
				cookie.setMaxAge(60 * 60 * 24 * 365);
			} else {
				cookie.setMaxAge(0);
			}
			
			cookie.setPath(req.getContextPath());
			resp.addCookie(cookie);
		} else {
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		
		return "redirect:/member/login-page";
		
	}
	
}
