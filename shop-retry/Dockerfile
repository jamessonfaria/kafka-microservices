FROM openjdk:17-jdk
VOLUME /tmp
ARG JAR_FILE=target/shop-retry-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app.jar"]