FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/notifications-*.jar app.jar
EXPOSE 9092
ENTRYPOINT ["java", "-jar", "app.jar"]