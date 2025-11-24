
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copiar archivos del proyecto
COPY . .

# Dar permisos de ejecución al mvnw
RUN chmod +x mvnw

# Compilar el proyecto (SALTANDO los tests)
RUN ./mvnw clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiar el JAR desde la etapa de build
COPY --from=build /app/target/auth-*.jar app.jar

EXPOSE 9094

ENTRYPOINT ["java", "-jar", "app.jar"]