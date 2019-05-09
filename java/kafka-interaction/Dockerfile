FROM openjdk:8-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dkafka.bootstrapAddress=192.168.0.1:9092", "-jar","/app.jar"]
EXPOSE 8080
