FROM openjdk:8

MAINTAINER lucasdonsilva@gmail.com

COPY . /app

WORKDIR /app

RUN ./mvnw clean package

CMD ["java", "-jar", "/app/target/collaborator.jar"]