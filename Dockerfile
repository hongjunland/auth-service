# 빌드 스테이지
FROM adoptopenjdk:11-jdk-hotspot AS builder
WORKDIR /app

# 필요한 파일들 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# gradlew에 실행 권한 부여 및 Windows 개행 문자 제거
RUN chmod +x ./gradlew && sed -i 's/\r$//' ./gradlew

# bootJar 태스크를 사용하여 JAR 생성
RUN ./gradlew bootJar

# 실행 스테이지
FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app

# 빌드 스테이지에서 생성된 JAR 파일을 현재 스테이지로 복사
COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
