# Build stage uses official Gradle image so we don't need a local wrapper
FROM gradle:8.8-jdk21 AS build
WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . ./
RUN gradle clean bootJar --no-daemon

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
