# Todo Project

# 🗓️ 개발 기간

- 2025.05.01. 목 - 2025.05.15. 목

# 🛠️ 기술 스택
**언어 및 프레임워크**
- Java 17

**데이터베이스**
- MySQL

**API**
- Notion

**ERD**
- DBdiagram

**기타**
- Postman

## **🔍 요구사항**

## LV0. ERD 작성하기 & API 명세 작성하기

- [ ]  API 작성하기
    - [ ]  일정 CRUD / 댓글 CRUD / 대댓글 CRUD에 대한 API 명세서를 작성합니다.
    - [ ]  RestAPI 원칙을 고민해보고 url / request / response / 예외 등에 대해 작성합니다.
- [ ]  ERD 작성하기
    - [ ]  일정 / 댓글 / 대댓글을 관리하기 위한 테이블의 구조를 ERD 다이어그램으로 나타냅니다.
- [ ]  작성된 API 명세서와 ERD를 ReadMe에 게시 해주세요

## LV1. 일정 CRUD

- [ ]  사용자는 일정을 생성할 수 있다.
- [ ]  사용자는 일정을 조회할 수 있다.
    - [ ]  전체 일정 목록을 조회할 수 있다.
    - [ ]  단일 일정(상세)을 조회할 수 있다.
- [ ]  사용자는 일정을 수정할 수 있다.
- [ ]  사용자는 일정을 삭제할 수 있다.
- [ ]  일정은 아래와 같은 필드를 가집니다.
    - [ ]  `일정 제목(title)`, `일정 내용(content)`, `작성일(createdAt)`, `수정일(updatedAt)`, `작성자 ID(writerId)`
    - [ ]  `작성일`, `수정일` 필드는 `JPA Auditing`을 활용하여 적용합니다.

## LV2. 댓글 CRUD

- [ ]  생성한 일정에 댓글을 남길 수 있습니다.
    - [ ]  댓글과 일정은 연관관계를 가집니다.
- [ ]  댓글을 저장, 조회, 수정, 삭제할 수 있습니다.
- [ ]  댓글은 아래와 같은 필드를 가집니다.
    - [ ]  `댓글 내용(content)`, `작성일(createdAt)`, `수정일(updatedAt)`, `작성자 ID(writerId)`, `일정 ID(scheduleId)`
    - [ ]  `작성일`, `수정일` 필드는 `JPA Auditing`을 활용하여 적용합니다.
    

## LV3. 게시물 조회 시 댓글 조회

- [ ]  일정 목록 조회 시, 각 일정마다 댓글 개수(commentCount)를 함께 조회할 수 있습니다.
    - [ ]  댓글 개수는 조회할 때마다 계산하거나, 별도 필드를 통해 관리해도 무방합니다.
- [ ]  일정 상세 조회 시, 해당 일정에 작성된 모든 댓글 목록을 함께 조회할 수 있습니다.
    - [ ]  댓글 목록은 작성일 기준 오름차순(작성 오래된 순)으로 정렬합니다.
    

## LV4. 대댓글 (1 Depth)

- [ ]  댓글에 답글(대댓글)을 작성할 수 있습니다.
    - [ ]  대댓글은 부모 댓글(parentComment)과 연관관계를 가집니다.
- [ ]  대댓글은 1 Depth까지만 허용합니다.
    - [ ]  즉, 대댓글에 다시 대댓글은 불가능합니다.
- [ ]  대댓글도 댓글과 동일한 필드를 가집니다.
    - [ ]  `댓글 내용(content)`, `작성일(createdAt)`, `수정일(updatedAt)`, `작성자 ID(writerId)`, 
    `일정 ID(scheduleId)`, `부모 댓글 ID(parentCommentId)`
- [ ]  대댓글 조회 시, 부모 댓글 하위에 정렬되어 함께 조회합니다.
    - [ ]  부모 댓글 → 대댓글 순으로 정렬
     
## API 명세서
![image](https://github.com/user-attachments/assets/8bd8e250-bd7a-451c-838f-888668fbfa85)
![image](https://github.com/user-attachments/assets/72164e53-ae5f-44c9-960d-85b76bc6b11c)


## ERD
![image](https://github.com/user-attachments/assets/dc4baad9-218a-4065-bdf2-294bb916b8e4)

