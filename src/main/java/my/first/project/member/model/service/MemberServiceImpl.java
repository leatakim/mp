package my.first.project.member.model.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import my.first.project.common.Util;
import my.first.project.common.filter.InitFilter;
import my.first.project.member.model.dao.MemberDAO;
import my.first.project.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Autowired
	private MemberDAO dao;
	
	private Logger logger = LoggerFactory.getLogger(InitFilter.class);
	
	
	
	@Override
	public int selectCertification(String memberEmail) {
		return dao.selectCertification(memberEmail);
	}
	

	@Override
	public int emailReduplicateCheck(String memberEmail) {
		logger.info("memberEmail : " + memberEmail);
		return dao.emailReduplicateCheck(memberEmail);
	}


	@Override
	public int insertCertification(String memberEmail, String cNumber) {
		Map<String, String> map = new HashMap<String, String>();
		logger.info("memberEmail : " + memberEmail);
		logger.info("cNumber : " + cNumber);
		map.put("cNumber", cNumber);
		map.put("memberEmail", memberEmail);
		return dao.insertCertification(map);
	}

	@Override
	public int updateCertification(String memberEmail, String cNumber) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cNumber", cNumber);
		map.put("memberEmail", memberEmail);
		return dao.updateCertification(map);
	}


	@Override
	public int emailMemberCheck(String memberEmail, String cNumber) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cNumber", cNumber);
		map.put("memberEmail", memberEmail);
		return dao.emailMemberCheck(map);
	}


	@Override
	public int signUp(Map<String, Object> map, MultipartFile uploadImage) throws IOException {
		
		// 비밀번호 암호화해서 map에 세팅
		Member member = (Member)map.get("member");
		
		logger.debug("암호화 전 : " + member.getMemberPw());
		
		member.setMemberPw( bcrypt.encode(member.getMemberPw()));
		
		logger.debug("암호화 후 : " + member.getMemberPw());
		
		map.put("member", member);
		
		System.out.println((Member)map.get("member"));
		
			
		String rename = null;
		// 이미지가 업로드 된 경우 Util클래스를 이용해 파일명 rename하기
		if( !uploadImage.isEmpty() ) {
			rename = Util.fileRename( uploadImage.getOriginalFilename() );
			map.put("profileImage", map.get("webPath") + rename);
		}else {
			map.put("profileImage", null);
		}
		int result = dao.signUp( map );
		
		// 회원가입 성공 및 profileImage가 null이 아닌경우 파일을 서버에 저장하기!
		if(result > 0 && map.get("profileImage")!=null ) {
					
			uploadImage.transferTo( new File ( map.get("folderPath")+rename) );
		}
		
		return result;
	}


	//////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Member login(Member inputMember) {
		
		logger.debug(inputMember.getMemberPw() + " / " + bcrypt.encode(inputMember.getMemberPw()));
		Member loginMember = dao.login(inputMember);
		
		if(loginMember != null) {
			if(bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
				loginMember.setMemberPw(null); // 비교 후 비밀번호 지우기
			} else {
				loginMember = null;
			}
		}
		
		return loginMember;
	}


	
	
	
}
