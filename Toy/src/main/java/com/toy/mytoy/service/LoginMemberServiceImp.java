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
	
	//������ ��ť��Ƽ �߰� > ��й�ȣ ��ȣȭ ��ȸ
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public int isId(String id, String password) {
		//member.xml id ���� ��ȸ > idcheck
		//Member rmember = dao.isId(id);
		Member rmember = dao.isEmailOrId(id);
		int result = -1; //���̵� �������� �ʴ� ��� rmember = null
		if(rmember != null) {
			if(rmember.getPassword().equals(password) || passwordEncoder.matches(password,rmember.getPassword())) {
				result = 1; // ���̵�� ��й�ȣ�� ��ġ�ϴ� ���
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
		Member rmember = dao.isId(id);//dao�� �̵�
		return(rmember == null) ? -1 : 1; // -1�� �̸����� �������� �ʴ� ���
										  //  1�� ���̵� �����ϴ� ���
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
		Member rmember = dao.isEmail(email);//dao�� �̵�
		return(rmember == null) ? -1 : 1; // -1�� �̸����� �������� �ʴ� ���
										  //  1�� ���̵� �����ϴ� ���
	}

}
