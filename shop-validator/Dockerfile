FROM openjdk:17-jdk
VOLUME /tmp
ARG JAR_FILE=target/shop-validator-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]