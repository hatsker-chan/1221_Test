# Stage 1: Build the application
FROM maven:3.9.9 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Run Maven to clean and package the application
RUN mvn clean package

# Stage 2: Create a smaller image for running the application
FROM openjdk:17-jdk-alpine

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]