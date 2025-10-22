# Stage 1: build
FROM eclipse-temurin:21-alpine AS build
WORKDIR /app

# Instalar Maven no estágio de build
RUN apk add --no-cache maven

# Copiar os arquivos necessários
COPY mvnw pom.xml ./
COPY . .

# Rodar o Maven para empacotar a aplicação
RUN mvn package -DskipTests

# Stage 2: runtime
FROM eclipse-temurin:21-alpine
COPY --from=build /app/target/BioPure-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]


#DockerAppBioPure.jar app.jar

## Dar permissão de execução ao mvnw
#RUN chmod +x mvnw
#
## Rodar build
#RUN ./mvnw clean package -DskipTests
#
## Stage 2: final image
#FROM eclipse-temurin:21-alpine
#WORKDIR /app
#COPY --from=build /app/target/BioPure-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]