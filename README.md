# 병원 공공데이터 대용량 데이터 처리(10만건 이상) / JPA를 이용한 RestApi 구현 / Spring Security (JWT 토큰)
## Running Docker on AWS EC2(10만건 이상 병원 RestApi)
### http://ec2-3-39-177-240.ap-northeast-2.compute.amazonaws.com:8080/
# Hospital Project ERD 다이어그램
<img width="537" alt="ERD" src="https://user-images.githubusercontent.com/104709432/206074048-b9f3e109-3fd4-4075-a8ed-38bad8cb9734.PNG">

# Spring Security + JWT Token
### [병원공공데이터 처리 프로젝트 Spring Security와 JWT Token 인증,인가 처리 (Velog 정리본)](https://velog.io/@guns95/Spring-Security%EC%99%80-JWT-%ED%86%A0%ED%81%B0%EC%9D%98-%EC%9D%B8%EC%A6%9D%EC%9D%B8%EA%B0%80-%EB%A1%9C%EA%B7%B8%EC%9D%B8)
## User RestApi(회원) - JWT 로그인 구현
### @PostMapping("/api/users/join") : 회원가입 api -> BCryptPasswordEncoder를 사용하여 비밀번호 암호화 후 DB저장
#### 🆗회원가입 성공 시
<img width="400" alt="캡처2" src="https://user-images.githubusercontent.com/104709432/204977035-f6ff4669-d473-4d0e-923b-80fcc24129db.PNG"> <br>
- **BCryptPasswordEncoder 암호화된 PassWord로 저장⬇**<br><br>
<img width="779" alt="캡처9" src="https://user-images.githubusercontent.com/104709432/204985692-b9c6ed6a-5616-47b8-a6ba-6ec36b8aa69f.PNG"> <br>
#### 🆖회원가입 실패 시<br>
- **중복되는 userName존재 할 때 ExceptionHandeler에서 HttpStatus.CONFLICT(409), message = "User name is duplicated errorCode"처리**
<img width="550" alt="캡처4" src="https://user-images.githubusercontent.com/104709432/204979030-d5385eec-7a57-45ec-90ae-c561b77e3598.PNG">

### @PostMapping("/api/users/login") : 로그인 -> 로그인 성공 시 Jwt토큰 반환
#### 🆗로그인 성공 시 - > JWT토큰 (인증,인가)
<img width="750" alt="캡처5" src="https://user-images.githubusercontent.com/104709432/204987426-c4ea9a39-a668-4ff1-b16c-a66af691a7a3.PNG"><br>
#### 🆖로그인 실패 시<br>
- **1.userName 존재 X 로그인 실패->HttpStatus.NOT_FOUND(404), message = "Not Found errorCode"처리**
<img width="550" alt="캡처7" src="https://user-images.githubusercontent.com/104709432/204981292-1375306f-966a-4b19-8f80-c15d5b97d5a0.PNG"><br>
- **2.비밀번호 틀릴 시 로그인 실패->HttpStatus.BAD_REQUEST(400), message = "Not correct password errorCode"처리**
<img width="550" alt="캡처6" src="https://user-images.githubusercontent.com/104709432/204980562-6777a266-6046-480a-bed1-be44673ac462.PNG"><br>


## Hospital RestApi(병원) 
- **Review와 @OneTomany관계**
### @GetMapping("/api/hospitals/{id}") :해당 id병원 정보만 조회
### @GetMapping("/api/hospitals/{id}/reviews") :해당 id 병원 정보와 해당 병원 리뷰 조회
### @GetMapping("/api/hospitals") :병원 전체 조회
### @GetMapping("/api/hospitals/reviews") :리뷰가 존재하는 병원 전체 조회

## Review RestApi(병원 리뷰)
- **Hospital와 @ManyToOne관계(Lazy)**
### @GetMapping("/api/{id}/reviews") : 해당 Review ID로 리뷰와 병원정보 조회
### @PostMapping("/api/{id}/reviews") : 리뷰 쓰기
### @GetMapping("/api/reviews") :리뷰 전체 조회</br>


## Book RestApi(책)
- **Publisher와 @oneToOne관계(Lazy) / Author와 @ManyToOne관계(Lazy)**
### @GetMapping("/api/books/{id}") : 해당 Book ID로 책/출판사/저자 조회(@EntityGraph 사용)
### @GetMapping("/api/books") :책/출판사/저자 전체 조회
### @PostMapping("/api/books") : 책 등록 (출판사,저자 연관관계)

## Article RestApi(게시글)
### @GetMapping("/api/articles/{id}") : 해당 ID로 게시글 조회
### @PostMapping("/api/articles") : 게시글 등록 
### @PatchMapping("/api/articles/{id}") : 게시글 수정(변경감지 dirty cash 사용)
### @DeleteMapping("/api/articles/{id}") : 게시글 삭제
### @GetMapping("/api/articles") : 게시글 전체 조회(제네릭타입을 응답 타입으로 사용하여 회원 count 기능 추가)

<br><br>
# JPA를 사용하는 이유
- **1. JPA는 자바(서버) 언어를 DB 언어로 바꿔준다**
- **2. JPA의 핵심도구는 ENTITY와 REPOSITORY가 있다**
- **3. ENTITY는 자바 객체를 DB가 이해할 수 있게 규격화한 데이터이다**
- **4. ENTITY는 REPOSITORY 라는 일꾼을 통해 DB에게 전달되고 처리된다.**
- **5. DB속 테이블에 관리된다.**  
<img width="443" alt="jpa" src="https://user-images.githubusercontent.com/104709432/200232901-932a6e72-8640-483f-9e37-b72814d4ac6a.PNG">

#### DTO를 사용하는 이유
- **1. 엔티티 내부 구현을 캡슐화할 수 있다.**
- **2. 화면에 필요한 데이터를 선별할 수 있다.**
- **3. validation 코드와 모델링 코드를 분리할 수 있다.**
- **4.요청과 응답으로 DTO를 사용하면 각각의 DTO 클래스가 데이터를 전송하는 클래스로서의 역할을 명확히 가질 수 있다.**
<img width="607" alt="DTO" src="https://user-images.githubusercontent.com/104709432/200233512-f4d6e991-58e2-45d5-a754-d21d42547de7.PNG">

## 컨트롤러 tdd에서 Mock객체를 사용하는 이유
- **MockMvc란?**
- **-> 브라우저에서 요청과 응답을 의미하는 객체로서 Controller 테스테 사용을 용이하게 해주는 라이브러리이다.**<BR>
- **-> 컨트롤러는 service를 주입 받았고 service는 repository를 주입받았다. 그래서 온전한 단위테스트를 하기에는 무리가있어서 Mock 라이브러리를 사용해서 TDD를 진행한다.**
<img width="800" alt="ahr" src="https://user-images.githubusercontent.com/104709432/202337823-4e874240-16fd-4ddd-95fc-c5d8795da152.PNG">
