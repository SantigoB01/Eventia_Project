FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/booking-*.jar app.jar
EXPOSE 9094
ENTRYPOINT ["java", "-jar", "app.jar"]