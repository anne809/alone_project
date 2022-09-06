package com.toy.mytoy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.toy.mytoy.dao.MemberDAO;
import com.toy.mytoy.domain.Member;

@Service
public class LoginMemberServiceImp implements LoginMemberService {
	
	@Autowired
	private MemberDAO dao;
	
	//스프링 시큐리티 추가 > 비밀번호 암호화 조회
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public int isId(String id, String password) {
		//member.xml id 쿼리 조회 > idcheck
		//Member rmember = dao.isId(id);
		Member rmember = dao.isEmailOrId(id);
		int result = -1; //아이디가 존재하지 않는 경우 rmember = null
		if(rmember != null) {
			if(rmember.getPassword().equals(password) || passwordEncoder.matches(password,rmember.getPassword())) {
				result = 1; // 아이디와 비밀번호가 일치하는 경우
			}else 
				result = 0;
		}
		return result;
	}

	@Override
	public int insert(Member m) {
		return dao.insert(m);
	}

	@Override
	public int isId(String id) {
		Member rmember = dao.isId(id);//dao로 이동
		return(rmember == null) ? -1 : 1; // -1은 이메일이 존재하지 않는 경우
										  //  1은 아이디가 존재하는 경우
	}

	@Override
	public Member getInfo(String m_code) {
		return dao.isEmailOrId(m_code);
	}

	@Override
	public int update(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update_admin(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update_res(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int isEmail(String email) {
		Member rmember = dao.isEmail(email);//dao로 이동
		return(rmember == null) ? -1 : 1; // -1은 이메일이 존재하지 않는 경우
										  //  1은 아이디가 존재하는 경우
	}

}
