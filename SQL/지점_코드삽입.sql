/*
데이터베이스설계 과제
작성자: 김세연
작성일: 2019-05-23

SQL 설명: 지점DB의 코드 테이블에 코드들을 삽입한다.
*/

--DIVCD(구분코드)에 구분코드 삽입
INSERT INTO DIVCD(DIVCD, DIVCD_NAME) VALUES('CD01', '이벤트이름');
INSERT INTO DIVCD(DIVCD, DIVCD_NAME, CD_RULE_NAME1) VALUES('CD02', '이벤트종류', '최소결제금액');
INSERT INTO DIVCD(DIVCD, DIVCD_NAME) VALUES('CD03', '입고구분');
INSERT INTO DIVCD(DIVCD, DIVCD_NAME) VALUES('CD04', '출고구분');
INSERT INTO DIVCD(DIVCD, DIVCD_NAME) VALUES('CD05', '주문상태');
INSERT INTO DIVCD(DIVCD, DIVCD_NAME) VALUES('CD06', '반품상태');
INSERT INTO DIVCD(DIVCD, DIVCD_NAME) VALUES('CD07', '지불방법');
INSERT INTO DIVCD(DIVCD, DIVCD_NAME) VALUES('CD08', '직책');
INSERT INTO DIVCD(DIVCD, DIVCD_NAME) VALUES('CD09', '포인트변경구분');
INSERT INTO DIVCD(DIVCD, DIVCD_NAME) VALUES('CD10', '연령층');
INSERT INTO DIVCD(DIVCD, DIVCD_NAME) VALUES('CD11', '물품구분');

--DTCD(상세코드)에 상세코드 삽입

--코드구분 이벤트이름의 상세코드 삽입
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD01', '001', '빼빼로데이이벤트');   --CD01001
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD01', '002', '발렌타인데이이벤트'); --CD01002
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD01', '003', '할로윈데이이벤트');   --CD01003

--코드구분 이벤트 종류의 상세코드 예시 삽입
--코드의 의미를 저장하기 위해 삽입
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME, CD_RULE1) VALUES('CD02', 'XXNZZZZZ', 'XX%할인, N+1증정, 최소결제금액:ZZZZZ', 'ZZZZZ');

--코드구분 입고구분의 상세코드 삽입
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD03', '01', '주문');      --CD0301
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD03', '02', '환불');      --CD0302
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD03', '03', '재고불일치'); --CD0303
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD03', '04', '기타');      --CD0304

--출고구분 상세코드 삽입
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD04', '01', '반품');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD04', '02', '판매');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD04', '03', '재고불일치');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD04', '04', '기타');

--주문상태 상세코드 삽입
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD05', '01', '주문준비중');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD05', '02', '주문거절');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD05', '03', '배송중');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD05', '04', '배송완료');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD05', '05', '확인완료');

--반품상태 상세코드 삽입
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD06', '01', '반품준비중');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD06', '02', '반품거절');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD06', '03', '반품처리중');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD06', '04', '반품수거중');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD06', '05', '반품수거완료');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD06', '06', '반품완료');

--지불방법 상세코드 삽입
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD07', '01', '현금');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD07', '02', '카드');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD07', '03', '티머니');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD07', '04', '문화상품권');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD07', '05', '핸드폰소액결제');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD07', '06', '포인트결제');

--직책 상세코드 삽입
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD08', '01', '지점장');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD08', '02', '매니저');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD08', '03', '직원');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD08', '04', '퇴사');

--포인트변경구분 상세코드 삽입
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD09', '01', '결제사용');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD09', '02', '결제적립');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD09', '03', '최초 가입 적립');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD09', '04', '이벤트');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD09', '05', '기타');

--연령층 상세코드 삽입
--X~Y세 => X세이상 Y세 이하를 의미
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD10', '01', '0~10세');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD10', '02', '10~19세');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD10', '03', '20~29세');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD10', '04', '30~39세');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD10', '05', '40~49세');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD10', '06', '50~59세');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD10', '07', '60~69세');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD10', '08', '70~999세');

--물품구분 상세코드 삽입
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD11', '001', '과자');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD11', '002', '아이스크림');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD11', '003', '컵라면');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD11', '004', '봉지라면');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD11', '005', '김밥');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD11', '006', '즉석냉장식품');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD11', '007', '즉석냉동식품');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD11', '008', '탄산음료');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD11', '009', '비탄산음료');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD11', '010', '술');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD11', '011', '담배');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD11', '012', '생필품');
INSERT INTO DTCD(DIVCD, DTCD, CD_NAME) VALUES('CD11', '013', '기타');