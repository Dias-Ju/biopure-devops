# Stage 1: build JAR
FROM eclipse-temurin:21-alpine AS build
WORKDIR /app
COPY mvnw pom.xml ./
COPY .mvn .mvn
COPY src src
RUN ./mvnw clean package -DskipTests

# Stage 2: final image
FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY --from=build /app/target/BioPure-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
