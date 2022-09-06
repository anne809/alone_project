-- 1. 리조트 정보 
create table resort 				-- 리조트 정보
("resortcode"	varchar2(50 BYTE) primary key NOT NULL,
 "resortname"	varchar2(100 BYTE) NOT NULL)

insert into resort
values('BL','발리 몬타나 리조트');

insert into resort
values('ML','몰디브 몬타나 리조트');

-- 2. 객실
CREATE TABLE ROOM
(roomcode varchar2(50) PRIMARY KEY NOT NULL,
 roomtype varchar2(100) NOT NULL,
 roomcharge varchar2(150) NOT NULL,
 resortcode varchar2(50) NOT NULL,          --RESORT 참조키
 roomcount number,
CONSTRAINT resort_code_fk FOREIGN KEY(resortcode)
 REFERENCES RESORT
);

DELETE FROM ROOM
WHERE resortcode='ML' OR resortcode='BL'

SELECT*
FROM ROOM
ORDER BY ROOMCODE ASC

--발리 객실
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

-- 몰디브 객실
insert into ROOM
values('MR1','Beach pool villa','1,000,000','ML',3);

insert into ROOM
values('MR2','Sunset Beach pool villa','2,000,000','ML',2);

insert into ROOM
values('MR3','Royal Montana Residence','4,500,000','ML',1);

---------------------------------------------------------------
--3. 다이닝

DROP TABLE DINING;

CREATE TABLE DINING
(dicode varchar2(50) PRIMARY KEY NOT NULL, 			--다이닝코드(BD:발리/MD:몰디브)
 ditype varchar2(100) NOT NULL,						--다이닝레스토랑
 dicharge varchar2(150),							--다이닝요금(NULL)
 diseatype varchar2(50) NOT NULL,					--다이닝 좌석(BAR/INSIDE/OUTSIDE/TABLE)
 resortcode varchar2(50) NOT NULL,          		--리조트 분류코드(BL발리/ML몰디브 FK)
 CONSTRAINT dining_code_fk FOREIGN KEY(resortcode)  
 REFERENCES RESORT
);

--레스토랑 수용인원 컬럼 추가
ALTER TABLE DINING
ADD(dicount number(10));							--다이닝 수용인원

DELETE FROM DINING
WHERE resortcode='ML' OR resortcode='BL'

-- 발리 다이닝 레스토랑
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

--몰디브 다이닝 레스토랑(NO INSERT)
insert into DINING
values('MD1','Montana Grill Chef’s Table',null,'Table','ML',6);


SELECT*
FROM DINING
order by dicode asc
