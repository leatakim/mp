package my.first.project.member.model.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import my.first.project.member.model.vo.Member;

public interface MemberService {

	public int selectCertification(String memberEmail);

	public int insertCertification(String memberEmail, String cNumber);

	public int updateCertification(String memberEmail, String cNumber);

	public int emailReduplicateCheck(String memberEmail);

	public int emailMemberCheck(String memberEmail, String cNumber);

	public int signUp(Map<String, Object> map, MultipartFile uploadImage) throws IOException;

	public Member login(Member inputMember);

	public Member kakaoEmailCheck(String kakaoEmail);

	public int insertNo(Member mem);
	
}
