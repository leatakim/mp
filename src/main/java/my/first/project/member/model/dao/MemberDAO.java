package my.first.project.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.first.project.common.filter.InitFilter;
import my.first.project.member.model.vo.Member;

@Repository
public class MemberDAO {

	private Logger logger = LoggerFactory.getLogger(InitFilter.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	//중복 체크 메소드 집합
	/** 이메일 중복
	 * @param userEmail
	 * @return
	 */
	public int emailReduplicateCheck(String memberEmail) {
		return sqlSession.selectOne("memberMapper.emailReduplicateCheck", memberEmail);
	}
	
	public int selectCertification(String memberEmail) {
		return sqlSession.selectOne("memberMapper.selectCertification", memberEmail);
	}

	public int insertCertification(Map<String, String> map) {
		logger.info("map : " + map);
		return sqlSession.insert("memberMapper.insertCertification", map);
	}

	public int updateCertification(Map<String, String> map) {
		return sqlSession.update("memberMapper.updateCertification", map);
	}

	public int emailMemberCheck(Map<String, String> map) {
		return sqlSession.selectOne("memberMapper.emailMemberCheck", map);
	}

	public int signUp(Map<String, Object> map) {
		return sqlSession.insert("memberMapper.signUp", map);
	}

	public Member login(Member inputMember) {
		return sqlSession.selectOne("memberMapper.login", inputMember);
	}

	public Member kakaoEmailCheck(String kakaoEmail) {
		System.out.println("kakaoEmailCheck DAO");
		return sqlSession.selectOne("memberMapper.kakaoEmailCheck", kakaoEmail);
	}

	public int insertNo(Member mem) {
		return sqlSession.insert("memberMapper.insertNo", mem);
	}


}
