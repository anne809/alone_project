-- 1. ����Ʈ ���� 
create table resort 				-- ����Ʈ ����
("resortcode"	varchar2(50 BYTE) primary key NOT NULL,
 "resortname"	varchar2(100 BYTE) NOT NULL)

insert into resort
values('BL','�߸� ��Ÿ�� ����Ʈ');

insert into resort
values('ML','����� ��Ÿ�� ����Ʈ');

-- 2. ����
CREATE TABLE ROOM
(roomcode varchar2(50) PRIMARY KEY NOT NULL,
 roomtype varchar2(100) NOT NULL,
 roomcharge varchar2(150) NOT NULL,
 resortcode varchar2(50) NOT NULL,          --RESORT ����Ű
 roomcount number,
CONSTRAINT resort_code_fk FOREIGN KEY(resortcode)
 REFERENCES RESORT
);

DELETE FROM ROOM
WHERE resortcode='ML' OR resortcode='BL'

SELECT*
FROM ROOM
ORDER BY ROOMCODE ASC

--�߸� ����
insert into ROOM
values('BR1','standard','300,000','BL',5);

insert into ROOM
values('BR2','deluxe','500,000','BL',4);

insert into ROOM
values('BR3','1 bedroom pool villa','1,000,000','BL',3);

insert into ROOM
values('BR4','2 bedroom pool villa','1,500,000','BL',2);

insert into ROOM
values('BR5','Family Suite pool villa','3,000,000','BL',1);

-- ����� ����
insert into ROOM
values('MR1','Beach pool villa','1,000,000','ML',3);

insert into ROOM
values('MR2','Sunset Beach pool villa','2,000,000','ML',2);

insert into ROOM
values('MR3','Royal Montana Residence','4,500,000','ML',1);

---------------------------------------------------------------
--3. ���̴�

DROP TABLE DINING;

CREATE TABLE DINING
(dicode varchar2(50) PRIMARY KEY NOT NULL, 			--���̴��ڵ�(BD:�߸�/MD:�����)
 ditype varchar2(100) NOT NULL,						--���̴׷������
 dicharge varchar2(150),							--���̴׿��(NULL)
 diseatype varchar2(50) NOT NULL,					--���̴� �¼�(BAR/INSIDE/OUTSIDE/TABLE)
 resortcode varchar2(50) NOT NULL,          		--����Ʈ �з��ڵ�(BL�߸�/ML����� FK)
 CONSTRAINT dining_code_fk FOREIGN KEY(resortcode)  
 REFERENCES RESORT
);

--������� �����ο� �÷� �߰�
ALTER TABLE DINING
ADD(dicount number(10));							--���̴� �����ο�

DELETE FROM DINING
WHERE resortcode='ML' OR resortcode='BL'

-- �߸� ���̴� �������
insert into DINING
values('BD1','The Whale Bar',null,'Bar','BL',6);

insert into DINING
values('BD1-1','Ocean Restaurant',null,'Inside','BL',6);

insert into DINING
values('BD1-2','Ocean Restaurant',null,'Outside','BL',2);

insert into DINING
values('BD2-1','Rin of Fire Dining',null,'Table','BL',6);

insert into DINING
values('BD2-2','Rin of Fire Dining',null,'Terrace','BL',6);

--����� ���̴� �������(NO INSERT)
insert into DINING
values('MD1','Montana Grill Chef��s Table',null,'Table','ML',6);


SELECT*
FROM DINING
order by dicode asc
