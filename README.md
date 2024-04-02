## 인증서버 모듈

## Overview
사이드프로젝트와 같이 여러 서비스 또는 나중에 참여할 프로젝트들을 대비해서 공통될만한 부분을 구현한 인증서버입니다.
Java 11와 Spring Boot 2.7 버전을 사용했으며, Spring Security를 통해 Authentication 및 Authorization부분을 구현하였습니다.
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
  
## 의의
처음에는 그냥 단일 서버에서 시작하고자 했습니다. 그러나 점차 공부해보면서 알게되었던 TDD, DDD, MSA, Service Discovery, Broker 등등 여러 기술들을 가장 먼저 적용해 보는 프로젝트가 되었습니다. 또, 다른 프로젝트를 수행할때 가장 먼저 참고해보는 프로젝트가 되었습니다. 수행하면서 여러 문제들을 해결과 관련 기술들을 공부해보면서 해당 사항들을 블로그에 기록하는 습관을 가지게 되는 계기가 되었습니다. 그리고 계속해서 리팩토링을 한다는 생각으로 코드를 짤때 찍어내기용 보다는 확장성을 직접적으로 고려하며 작성하는 경험을 할 수 있게 되었습니다.


## 개발 환경
* Language: Java 11
* Library & Framework: Spring Boot 2.7.x, JPA
* Testing: JUnit5, AssertJ, Mockito, Jacoco, Spring Rest Docs
* DataBase: runtime-> MySQL, test->h2
* Message Broker: Apache Kafka
* Service Discovery: Eureka
* CI/CD: AWS EC2, Github Actions, Docker, Docker Compose
