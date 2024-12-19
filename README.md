# JPA PLUS

---
## 👨‍💻 Period : 2024/12/10 ~ 2024/12/19

---

## 👨‍💻 API명세서

---

### AdminController

<details>
<summary>1. 유저 조회 (POST / admins/report-users)</summary>

- 요청
  - **Headers**:
    - Content-Type: application/json
    - 조회 할 유저들의 유저 아이디
  - **Body**:

  ```json
  {
      "userIds": [1, 2, 3]
  }
  ```

  - 설명:

  | # | 이름 | 타입 | 설명 | Required |
  |---|-----|------|-----|----------------|
  |1|userIds|List<Long>|유저들의 아이디|X|
</details>

---
### HealthCheckController

<details>
<summary>1. 헬스체크 홈 (GET "/")</summary>

- 요청
    - **Headers**: 없음
    - 설명: 홈 api로 String "home-aws"를 반환해줍니다.
</details>

<details>
<summary>2. 헬스체크 헬스 (GET "/healthz")</summary>

- 요청
    - **Headers**: 없음
    - 설명: 헬스 api로 String "healthz-aws"를 반환해줍니다.
</details>

---
### UserController

<details>
<summary>1. 유저 생성 (POST "/users")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
        - 가입할 유저 정보
    - **Body**:

  ```json
  {
      "email": "test@test.com",
      "nickname": "name",
      "password": "test1234",
      "role": "user"
  }
  ```
  
    - 설명:

  | # | 이름       | 타입     | 설명       | Required |
    |---|----------|--------|----------|----------------|
  | 1 | email    | String | 유저의 이메일  |X|
  | 2 | nickname | String | 유저의 닉네임  |X|
  | 3 | password | String | 유저의 비밀번호 |X|
  | 4 | role     | String | 계정 권한    |X|
- 
</details>

<details>
<summary>2. 로그인 (POST "/users/login")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
        - 로그인 이메일과 비밀번호
    - **Body**:

  ```json
  {
      "email": "test@test.com",
      "password": "test1234"
  }
  ```

    - 설명:

  | # | 이름       | 타입     | 설명       | Required |
      |---|----------|--------|----------|----------------|
  | 1 | email    | String | 유저의 이메일  |X|
  | 2 | password | String | 유저의 비밀번호 |X|

</details>

<details>
<summary>3. 로그아웃 (POST "/users/logout")</summary>

- 요청
    - **Headers**: 없음
    - 설명: 유저 로그아웃과 세션 삭제

</details>

---
### ItemController

<details>
<summary>1. 아이템 생성 (POST "/items")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
        - 생성할 아이템 정보
    - **Body**:

  ```json
  {
      "name": "item name",
      "description": "item description",
      "managerId": 2,
      "ownerId": 1
  }
  ```

    - 설명:

  | # | 이름          | 타입     | 설명       | Required |
      |---|-------------|--------|----------|----------|
  | 1 | name        | String | 아이템의 이름  | X        |
  | 2 | description | String | 아이템의 설명  | X        |
  | 3 | managerId   | Long   | 메니저의 아이디 | O        |
  | 4 | ownerId     | Long   | 주인 아이디   | O        |

</details>

---
### ReservationController

<details>
<summary>1. 예약 생성 (POST "/reservations")</summary>

- 요청
    - **Headers**:
        - Content-Type: application/json
        - 생성할 예약 정보
    - **Body**:

  ```json
  {
      "itemId": 1,
      "userId": 1,
      "startAt": "12-12-12T10:20:30",
      "endAt": "12-12-12T10:20:30"
  }
  ```

    - 설명:

  | # | 이름      | 타입            | 설명          | Required |
        |---|---------|---------------|-------------|----------|
  | 1 | itemId  | Long          | 예약 아이템의 아이디 | O        |
  | 2 | userId  | Long          | 예약 유저의 아이디  | O        |
  | 3 | startAt | LocalDateTime | 예약 시작 시간    | X        |
  | 4 | endAt   | LocalDateTime | 예약 종료 시간    | X        |

</details>

<details>
<summary>2. 예약 수정 (PATCH "/reservations/{id}/update-status")</summary>

- 요청
    - **Headers**:
        - Content-Type: String
        - 수정할 상태
    - **Path Variables**:
      - {id}: 수정할 예약 ID
    - **Body**:

  ```
    CANCELED
  ```

    - 설명: 예약 상태
      - "EXPIRED", "PENDING", "APPROVED", "CANCELED"
</details>

<details>
<summary>3. 예약 다건 조회 (GET "/reservations")</summary>

- 요청
    - **Headers**:
        - Session
</details>

<details>
<summary>4. 예약 수정 (PATCH "/reservations/search")</summary>

- 요청
    - **Request Param**:
        - userId: 예약한 유저 ID
        - itemId: 예약한 아이템 ID
</details>

---
## 👨‍💻 About Project

- 유저( 소유자와 메니저 )와 아이템를 생성하고 예약을 하는 프로그렘
---
## 🥵 Trouble Shooting & 🚀 Refactoring
**테스트코드**
- 테스트코드를 작성하여 테스트를 진행하였습니다. 그 과정에서 @MockBean이 @MockitoBean으로 변경된 것을 알게되었습니다.
- 테스트코드를 작성 할 때 로그인 필터로 인하여 테스트가 되지 않는 상황이 발생하였습니다.

---
## 😭 아쉬운점
- 아직 테스트코드의 작성이 미흡하여 테스트코드작성 부분이 아쉽습니다.
- 과제에서 S3로 이미지를 관리하고 RDS를 구축하지 못한것이 아쉽습니다.
