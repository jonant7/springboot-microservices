FROM gradle:8-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/customer-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/customer-service.jar"]
