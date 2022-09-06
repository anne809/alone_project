DROP TABLE COMMENTS;

CREATE TABLE COMMENTS(
	NUM				 NUMBER		 		PRIMARY KEY,
	ID 				 VARCHAR2(50 BYTE) 	REFERENCES MEMBER(ID),
	CONTENT 	     VARCHAR2(200),
	REG_DATE		 DATE,
	BOARD_NUM        NUMBER 			REFERENCES BOARD(BOARD_NUM)
					 ON DELETE CASCADE
	);
						
						--ON DELETE CASCADE : ������ �����ϸ� �� �������� �����ϴ� ��۵� �����˴ϴ�.--
						
	CREATE SEQUENCE COM_SEQ;
	
	select*
	from comments
