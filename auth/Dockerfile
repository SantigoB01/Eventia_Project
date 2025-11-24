FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY . .


RUN chmod +x mvnw


RUN ./mvnw -q -DskipTests package

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build target/auth-service.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]