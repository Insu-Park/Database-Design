/*
데이터베이스설계 과제
작성자: 김세연
작성일: 2019-05-23
적용DB: 지점DB
설명: 지점 초기 직원들 삽입.
*/

INSERT INTO STAFF(STAFF_NO, STAFF_NAME, PR_NO, PHONE_NO, POSITION_CD, NHT_HWAGE, DAY_HWAGE, BANK_NAME, ACNT_NO)
VALUES(1, '김세연', '9509231659845', '010-2938-0000', 'CD0801', 0, 0, '신한', '110-316-000000'); -- CD0801=지점장
INSERT INTO STAFF(STAFF_NO, STAFF_NAME, PR_NO, PHONE_NO, POSITION_CD, NHT_HWAGE, DAY_HWAGE, BANK_NAME, ACNT_NO)
VALUES(2, '박인수', '9509231123154', '010-1234-1234', 'CD0802', 15000, 10000, '우리', '1234-123-123456'); -- CD0802=매니저
INSERT INTO STAFF(STAFF_NO, STAFF_NAME, PR_NO, PHONE_NO, POSITION_CD, NHT_HWAGE, DAY_HWAGE, BANK_NAME, ACNT_NO)
VALUES(3, '이동수', '9309231894524', '010-1234-5678', 'CD0803', 9000, 8350, '농협', '123-1234-1234-12'); -- CD0803=직원
INSERT INTO STAFF(STAFF_NO, STAFF_NAME, PR_NO, PHONE_NO, POSITION_CD, NHT_HWAGE, DAY_HWAGE, BANK_NAME, ACNT_NO)
VALUES(4, '홍길동', '9703211265845', '010-1212-3232', 'CD0803', 9000, 8350, '기업', '123-123456-12-123'); -- CD0803=직원
