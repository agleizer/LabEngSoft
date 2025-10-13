# O Dockerfile descreve como criar uma imagem Docker.
# Quando você roda docker compose up --build, o Docker lê esse arquivo e gera ambiente de execução reproduzível e leve, igual em qualquer sistema operacional.

# syntax=docker/dockerfile:1.6

# Etapa de build
FROM maven:3.9.11-eclipse-temurin-21 AS build
WORKDIR /workspace

ENV MAVEN_CONFIG=""

# Copia apenas o pom.xml e o wrapper para baixar dependências
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN chmod +x mvnw

# Baixa dependências
RUN ./mvnw -B -q -DskipTests org.apache.maven.plugins:maven-dependency-plugin:3.6.1:go-offline

# Copia o código e compila
COPY src src
RUN ./mvnw -B -q -DskipTests package

# --- Etapa 2: runtime ---
FROM eclipse-temurin:21.0.8_9-jre
WORKDIR /app
EXPOSE 8080
USER 1001
ENV SPRING_PROFILES_ACTIVE=prod \
    JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"
COPY --from=build /workspace/target/caminhofacil-*.jar app.jar
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
