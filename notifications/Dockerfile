FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY . .


FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build target/notifications-*.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]