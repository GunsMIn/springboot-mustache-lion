# mustache와 jpa를 이용한 게시판
**멋쟁이사자처럼 활동내용**
<img width="900" alt="___________2017-02-22______5 47 08" src="https://user-images.githubusercontent.com/104709432/198947114-5f7b3711-53b9-415c-9f38-f95072dc1cd4.png">
<br>
<img src="https://img.shields.io/badge/Java-E34F26?style=flat&logo=Java&logoColor=white"/></a>
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=Spring Boot&logoColor=white"/></a>
<img src="https://img.shields.io/badge/JUnit5-25A162?style=flat&logo=JUnit5&logoColor=white"/></a>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"/></a>

## Musstache와 jpa를 이용하여 만드는 CRUD 게시판


## 221107 (mustache탬플릿 사용)
- **mustache탬플릿 적용 -> 인식 안될시 plugin 설치 필요**
#### JPA를 사용하는 이유
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
