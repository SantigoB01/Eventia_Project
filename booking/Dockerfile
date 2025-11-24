FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copiar archivos del proyecto
COPY . .

# Compilar el proyecto usando Maven (NO mvnw)
RUN mvn clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiar el JAR desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 9094

ENTRYPOINT ["java", "-jar", "app.jar"]