FROM gradle:8-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/account-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/account-service.jar"]
