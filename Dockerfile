FROM openjdk:21-slim

WORKDIR /app

COPY target/pedidos-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

LABEL authors="PPMAR"

