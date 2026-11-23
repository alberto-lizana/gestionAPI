# Etapa 1: Compilación
FROM maven:3.9-eclipse-temurin-25 AS build

WORKDIR /app

# Copiar configuración y dependencias
COPY pom.xml .

# Descargar dependencias
RUN mvn dependency:go-offline

# Copiar código fuente
COPY src ./src

# Compilar sin ejecutar tests
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:25-jre

WORKDIR /app

# Copiar JAR generado
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
