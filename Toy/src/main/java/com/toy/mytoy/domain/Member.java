package com.toy.mytoy.domain;

//����� ���� domain��  insert into member(ID,PASSWORD,EMAIL,COUNTRY,JOINDATE,MEMBERCODE)
public class Member {
	private String id; 			 // ����� �̸�(����)
	private String password; 	 // ��й�ȣ
	private String email; 		 // �̸���
	private String country; 	 // ����/����
	private String joindate;	 // ������(�ڵ�)
	private int membercode;    	 // ȸ����ȣ(�ڵ�)
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	public int getMembercode() {
		return membercode;
	}

	public void setMembercode(int membercode) {
		this.membercode = membercode;
	}
	
}
