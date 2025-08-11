FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/power-tracker-api.jar powertracker-api.jar
EXPOSE 8080
CMD ["java","-jar","powertracker-api.jar"]