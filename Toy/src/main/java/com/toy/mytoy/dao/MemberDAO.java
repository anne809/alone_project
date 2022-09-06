package com.toy.mytoy.dao;

//MemberServiceImpl 따라 DAO로 이동
//resources >sql > member.xml 의 mapper로 이동

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toy.mytoy.domain.Member;

//DAO : DB에 접근하여 데이터를 조회 또는 조작하는 객체
@Repository //저장소, DB를 조회 조작하는데에 중점을 둔 개념(mapper보다 상위 개념)
public class MemberDAO {
	
	@Autowired//주입
	private SqlSessionTemplate sqlSession;
	
	public Member isEmail(String email) {
		return sqlSession.selectOne("Members.emailcheck", email);//member.xml로 넘어감
		//null 이나 1로 리턴 
	}
	
	public Member isId(String id) {
		return sqlSession.selectOne("Members.idcheck", id);//member.xml로 넘어감
		//null 이나 1로 리턴 
	}
	
	public Member isId(String id, String pass) {
		return sqlSession.selectOne("Members.idcheck", id);//member.xml로 넘어감
		//null 이나 1로 리턴 
	}
	
	public Member isEmailOrId(String id) {
		return sqlSession.selectOne("Members.isEmailOrId", id);//member.xml로 넘어감
		//null 이나 1로 리턴 
	}
	
	public int insert(Member m) {
		return sqlSession.insert("Members.insert", m);
	}
	
	public Member getInfo(String m_code) {
		return sqlSession.selectOne("Members.EmpCheck", m_code);
	}
}
