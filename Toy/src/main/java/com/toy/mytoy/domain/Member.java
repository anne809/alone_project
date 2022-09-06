package com.toy.mytoy.domain;

//사용자 정보 domain단  insert into member(ID,PASSWORD,EMAIL,COUNTRY,JOINDATE,MEMBERCODE)
public class Member {
	private String id; 			 // 사용자 이름(영문)
	private String password; 	 // 비밀번호
	private String email; 		 // 이메일
	private String country; 	 // 국가/지역
	private String joindate;	 // 가입일(자동)
	private int membercode;    	 // 회원번호(자동)
	
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
