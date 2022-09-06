-- 회원정보 테이블

CREATE TABLE MEMBER
   ("ID" 			VARCHAR2(50 BYTE) NOT NULL ENABLE, 	    --영문 이름
	"PASSWORD"  	VARCHAR2(100 BYTE) NOT NULL ENABLE,   	--비밀번호
	"EMAIL" 		VARCHAR2(150 BYTE) NOT NULL ENABLE, 	--이메일
	"COUNTRY" 		VARCHAR2(50 BYTE) NOT NULL ENABLE,  	--국가/지역
	"JOINDATE" 		DATE NOT NULL ENABLE, 					--가입일(당일날짜)
	"MEMBERCODE" 	NUMBER NOT NULL ENABLE					--회원번호(시퀀스부여)
   )
   
   select*
   from member