FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copiar POM primero para mejor cache de dependencias
COPY pom.xml .

# Descargar dependencias
RUN mvn dependency:go-offline -Dmaven.test.skip=true

# Copiar código fuente
COPY src ./src

# Compilar
RUN mvn clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 9094

ENTRYPOINT ["java", "-jar", "app.jar"]