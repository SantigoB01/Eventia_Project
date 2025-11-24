FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY . .


RUN chmod +x mvnw

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build target/booking-*.jar app.jar

EXPOSE 9094

ENTRYPOINT ["java", "-jar", "app.jar"]