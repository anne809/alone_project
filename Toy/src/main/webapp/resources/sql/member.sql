-- ȸ������ ���̺�

CREATE TABLE MEMBER
   ("ID" 			VARCHAR2(50 BYTE) NOT NULL ENABLE, 	    --���� �̸�
	"PASSWORD"  	VARCHAR2(100 BYTE) NOT NULL ENABLE,   	--��й�ȣ
	"EMAIL" 		VARCHAR2(150 BYTE) NOT NULL ENABLE, 	--�̸���
	"COUNTRY" 		VARCHAR2(50 BYTE) NOT NULL ENABLE,  	--����/����
	"JOINDATE" 		DATE NOT NULL ENABLE, 					--������(���ϳ�¥)
	"MEMBERCODE" 	NUMBER NOT NULL ENABLE					--ȸ����ȣ(�������ο�)
   )
   
   select*
   from member