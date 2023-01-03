FROM openjdk:17-jdk
VOLUME /tmp
ARG JAR_FILE=target/shop-report-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app.jar"]