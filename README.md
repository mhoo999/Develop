![develop drawio](https://github.com/user-attachments/assets/0cf11440-0ac2-48e6-a449-f7a2123364a0)

API LINK : https://documenter.getpostman.com/view/41349915/2sAYXBHLQv

## SQL
```sql
CREATE TABLE user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '유저 식별자',
    username VARCHAR(100) NOT NULL COMMENT '유저 이름',
    email VARCHAR(100) NOT NULL UNIQUE COMMENT '이메일',
    password VARCHAR(100) NOT NULL COMMENT '비밀번호',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'
)
CREATE TABLE schedule (
    schedule_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '스케줄 식별자',
    user_id BIGINT NOT NULL COMMENT '유저 식별자',
    title VARCHAR(100) NOT NULL COMMENT '제목',
    contents TEXT NOT NULL COMMENT '내용',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
)
CREATE TABLE comment (
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '댓글 식별자',
    user_id BIGINT NOT NULL COMMENT '유저 식별자',
    schedule_id BIGINT NOT NULL COMMENT '스케줄 식별자',
    contents TEXT NOT NULL COMMENT '댓글 내용',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (schedule_id) REFERENCES schedule(schdule_id) ON DELETE CASCADE
);
```

개요
- 유저 정보와 스케줄 정보를 저장하는 프로그램
- 필터가 적용되어 인증/인가 기능 내장
- 비밀번호를 암호화하여 관리
- 스케줄에 댓글을 작성할 수 있음(댓글 또한 테이블로 저장됨)
  
구현 내용
- 유저, 스케줄, 댓글을 각각 테이블로 작성 후, 연관관계를 매핑
- FilterRegistrationBean 클래스를 사용하여 커스텀 필터와 로그인 필터를 만들어 적용
- 회원가입시 세션을 생성하여 서버에 저장하고, 로그인시 쿠키에 세션 정보를 담아 배포
  - 유저는 로그인 이후, 쿠키의 세션이 만료할 때 까지 쿼리 호출이 가능함
- 비밀번호를 암호화/복호화 할 수 있는 PasswordEncoder 클래스를 작성하여 관리
- Pageable 객체를 통해 pagenation 기능 구현
