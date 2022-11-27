# 병원 공공데이터 대용량 데이터 처리(10만건 이상) / JPA를 이용한 RestApi 구현
## Running Docker on AWS EC2
<img width="700" alt="aws" src="https://user-images.githubusercontent.com/104709432/204135014-c5b8d034-2b25-479b-8014-d730eb7331ef.PNG">
<br>
<img src="https://img.shields.io/badge/Java-E34F26?style=flat&logo=Java&logoColor=white"/></a>
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=Spring Boot&logoColor=white"/></a>
<img src="https://img.shields.io/badge/JUnit5-25A162?style=flat&logo=JUnit5&logoColor=white"/></a>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"/></a>

# AWS (10만건 이상 병원 RestApi)
### http://ec2-3-39-177-240.ap-northeast-2.compute.amazonaws.com:8080/

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
### @GetMapping("/api/reviews") :리뷰 전체 조회

## Book RestApi(책)
- **Publisher와 @oneToOne관계(Lazy) / Author와 @ManyToOne관계(Lazy)**
### @GetMapping("/api/books/{id}") : 해당 Book ID로 책/출판사/저자 조회(@EntityGraph 사용)
### @GetMapping("/api/books") :책/출판사/저자 전체 조회
### @PostMapping("/api/books") : 책 등록 (출판사,저자 연관관계)


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
