FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/review-*.jar app.jar
EXPOSE 9093
ENTRYPOINT ["java", "-jar", "app.jar"]