FROM openjdk:23-jdk-slim AS build

WORKDIR /app

# Copy the project files into the container
COPY mvnw /app/
COPY .mvn /app/.mvn
COPY src /app/src
COPY pom.xml /app/

RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests

FROM openjdk:23-jdk-slim

WORKDIR /app

COPY --from=build /app/target/NotificationServiceApplication-0.0.1-SNAPSHOT.jar notificationserviceapplication.jar


EXPOSE 8082

ENTRYPOINT ["java", "-jar", "notificationserviceapplication.jar"]
