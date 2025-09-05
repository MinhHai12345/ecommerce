#FROM maven:3.8.2-jdk-8
#
#WORKDIR ./ecommerce
#COPY . .
#
##RUN mvn clean install -DskipTests
#
#COPY target/ecommerce-V_01.jar /ecommerce/ecommerce-V_01.jar
#
#EXPOSE 8899
#
#ENTRYPOINT ["java", "-jar", "/ecommerce/ecommerce-V_01.jar"]

# Stage 1: Build the application using Maven
FROM maven:3.8.2-jdk-8 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download the dependencies first (caching layer)
COPY pom.xml /app/pom.xml
#RUN mvn dependency:go-offline -B

# Copy the rest of the project files
COPY . /app

# Package the application (with tests skipped if necessary)
#RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image for the application
FROM openjdk:8-jdk-alpine

# Create a directory for the application
WORKDIR /ecommerce

# Copy the jar from the build stage
COPY --from=build /app/target/ecommerce-V_01.jar /ecommerce/ecommerce-V_01.jar

# Expose the port the application runs on
EXPOSE 8899

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "/ecommerce/ecommerce-V_01.jar"]
