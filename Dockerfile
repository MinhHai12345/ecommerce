FROM maven:3.8.2-jdk-8

WORKDIR /ecommerce
COPY . .
RUN mvn clean install -DskipTests

#CMD mvn spring-boot:run