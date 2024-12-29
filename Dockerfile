FROM openjdk:23-jdk

WORKDIR /app

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "notificationserviceapplication.jar"]
