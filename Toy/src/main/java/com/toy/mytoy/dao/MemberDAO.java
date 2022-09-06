package com.toy.mytoy.dao;

//MemberServiceImpl ���� DAO�� �̵�
//resources >sql > member.xml �� mapper�� �̵�

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toy.mytoy.domain.Member;

//DAO : DB�� �����Ͽ� �����͸� ��ȸ �Ǵ� �����ϴ� ��ü
@Repository //�����, DB�� ��ȸ �����ϴµ��� ������ �� ����(mapper���� ���� ����)
public class MemberDAO {
	
	@Autowired//����
	private SqlSessionTemplate sqlSession;
	
	public Member isEmail(String email) {
		return sqlSession.selectOne("Members.emailcheck", email);//member.xml�� �Ѿ
		//null �̳� 1�� ���� 
	}
	
	public Member isId(String id) {
		return sqlSession.selectOne("Members.idcheck", id);//member.xml�� �Ѿ
		//null �̳� 1�� ���� 
	}
	
	public Member isId(String id, String pass) {
		return sqlSession.selectOne("Members.idcheck", id);//member.xml�� �Ѿ
		//null �̳� 1�� ���� 
	}
	
	public Member isEmailOrId(String id) {
		return sqlSession.selectOne("Members.isEmailOrId", id);//member.xml�� �Ѿ
		//null �̳� 1�� ���� 
	}
	
	public int insert(Member m) {
		return sqlSession.insert("Members.insert", m);
	}
	
	public Member getInfo(String m_code) {
		return sqlSession.selectOne("Members.EmpCheck", m_code);
	}
}
