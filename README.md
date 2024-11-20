## 인증 서비스용 템플릿

## Overview
사이드프로젝트와 같이 여러 서비스 또는 나중에 참여할 프로젝트들을 대비해서 공통될만한 부분을 구현한 인증서버입니다.
Java 17와 Spring Boot 3.x 버전을 사용했으며, Spring Security를 통해 Authentication 및 Authorization부분을 구현하였습니다.
그리고 DB에 의존성을 줄이고자 ORM으로 구성했습니다. MSA방식에 도입할 수 있도록 도메인 별로 모듈을 나누도록 하였고, 클린 아키텍처를 구성했습니다.

개발방법론은 TDD를 수행했습니다. Mockito로 외부 의존성과 독립적으로 테스트 하도록 하였으며, Jacoco를 통해 테스트 커버리지를 측정하도록 했습니다.
테스트환경과 실행환경을 구분하고자 환경마다 Database를 분리하였습니다. 

Spring Security와 JWT와 그리고 테스트 코드를 작성해보면서 발생하는 문제에 대해 해결해보면서, 관련 기술 및 코드에 대해 깊게 알아보는 경험이 되었습니다.


## 주요 기능
* JWT 인증(로그인)
* 회원 관리(회원가입, 회원조회 등등)

## 배경
프로젝트를 수행할 때마다 처음에 환경 구축하는데에 있어서 시간을 줄이고 싶었습니다. 특히 인증관련 부분이 가장 오래 걸렸던 부분이기도하고 또, 다른 요구사항들에 비해 가장 공통적인 부분이 많아서 미리 템플릿처럼 구성해 놓으면 좋겠다는 생각으로 시작했습니다. 

## 과정
Database뿐만 아니라 Service Discovery, JWT, Spring Security등등 여러 외부 모듈들을 사용하다보니 직접 테스트하기에 힘든 요소가 많았습니다. 그래서 Mockist 방식으로 흐름위주로 단위테스트위주로 테스트코드를 작성하고자 했습니다. 그래도 발생할 만한 트러블 슈팅들을 해결해나가면서 기록해 나갔습니다. 

## 개발과정에서 작성한 글
* [AuthenticationMangerBuilder의 Mocking이 힘든 이유
](https://velog.io/@hongjunland/AuthenticationMangerBuilder%EC%9D%98-Mocking%EC%9D%B4-%ED%9E%98%EB%93%A0-%EC%9D%B4%EC%9C%A0)
* [@RequestBody가 선언된 객체의 final field 이슈
](https://velog.io/@hongjunland/RequestBody%EA%B0%80-%EC%84%A0%EC%96%B8%EB%90%9C-%EA%B0%9D%EC%B2%B4%EC%9D%98-final-field-%EC%9D%B4%EC%8A%88)
* [DefaultOAuth2UserService 의 super method 의 mocking 문제](https://velog.io/@hongjunland/DefaultOAuth2UserService-%EC%9D%98-super-method-%EC%9D%98-mocking-%EB%AC%B8%EC%A0%9C)
* [SpringBoot + JWT 인증 리팩토링](https://velog.io/@hongjunland/SpringBoot-JWT-%EC%9D%B8%EC%A6%9D-%EB%A6%AC%ED%8C%A9%ED%86%A0%EB%A7%81)
* [Spring REST Docs 도입기
](https://velog.io/@hongjunland/Spring-REST-Docs-%EB%8F%84%EC%9E%85%EA%B8%B0)
## 의의
처음에는 그냥 단일 서버에서 시작하고자 했습니다. 그러나 점차 공부해보면서 알게되었던 TDD, DDD, MSA, Service Discovery, Broker 등등 여러 기술들을 가장 먼저 적용해 보는 프로젝트가 되었습니다. 또, 다른 프로젝트를 수행할때 가장 먼저 참고해보는 프로젝트가 되었습니다. 수행하면서 여러 문제들을 해결과 관련 기술들을 공부해보면서 해당 사항들을 블로그에 기록하는 습관을 가지게 되는 계기가 되었습니다. 그리고 계속해서 리팩토링을 한다는 생각으로 코드를 짤때 찍어내기용 보다는 확장성을 직접적으로 고려하며 작성하는 경험을 할 수 있게 되었습니다.


## 개발 환경
* Language: Java 17
* Library & Framework: Spring Boot 3.0.x, JPA, OAuth2
* Testing: JUnit5, AssertJ, Mockito, Jacoco
* DataBase: runtime-> MySQL, test->h2
* CI/CD: AWS EC2, GitHub Actions, Docker, Docker Compose

## Release
### 버전 1.2.2
- **날짜**: 2024년 11월 20일
- **주요 변경 사항**:
    - JWT 재발급 기능 `[FEAT]리프레시 토큰 추가`
    - Refresh Token 저장소 선택 옵션 추가 `[FEAT]: Refresh Token 저장소 선택 옵션 추가`
### 버전 1.2.1
- **날짜**: 2024년 7월 8일
- **주요 변경 사항**:
    - OAuth 로그인 추가 `[FEAT]: OAuth 로그인 추가`
    - 로그인 테스트 부분 오류 수정 `[TEST]: 로그인 테스트 부분 오류 수정`
  
### 버전 1.2.0
- **날짜**: 2024년 7월 2일
- **주요 변경 사항**:
    - Java11 -> 17 전환 `[REFACTOR]: update properties`
    - 패키지 구조 변경 `[CHORE]: Refactoring package`

### 버전 1.1.0
- **날짜**: 2024년 4월 2일
- **주요 변경 사항**:
    - 배포환경 구축 `[FIX]: 배포환경 구축`
    - 현재 사용자 정보 기능 추가 `[FEAT]: 현재 사용자 정보 기능UserKafkaService 추가`
    - Kafka 추가 `[FEAT]: Kafka 추가`
    - Authentication Handling 관련 추가 `[FEAT]: Authentication Handling 관련 추가`
    - Eureka 설정 추가 `[FEAT]: eureka 설정`

### 버전 1.0.1
- **날짜**: 2023년 11월 2일
- **주요 변경 사항**:
    - Transactional 추가 `[FEAT]: Transactional 추가`
    - 데이터베이스 변경 (H2 -> MySQL) `[FEAT]: h2 -> mysql 변경`
    - Docker 테스트 추가 `[FEAT]: docker test`

### 버전 1.0.0
- **날짜**: 2023년 7월 14일
- **주요 변경 사항**:
    - 회원가입, 로그인, ApiResponse, ErrorHandler, Exception 정의 `[FEAT]: 회원가입, 로그인, ApiResponse, ErrorHandler, Exception 정의`
    - Spring Rest Docs 추가 `[Feat]: Spring Rest Docs 추가`
    - Spring Rest Docs snippets 설정, auth-login test 문서 추가 `[Chore]: Spring Rest Docs snippets 설정, auth-login test 문서 추가`
    - root 패키지 이름 변경 `[FEAT]: root package name 변경`
    - 배포 기능 추가, GitHub Actions 추가 `[FEAT]: 배포기능 추가, github actions 추가`