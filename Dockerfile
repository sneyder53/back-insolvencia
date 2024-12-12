FROM amazoncorretto:23-alpine-jdk
WORKDIR /app
LABEL authors="sneyd"
EXPOSE 9001
ADD ./target/back-insolvencia-0.0.1-SNAPSHOT.jar back-insolvencia-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "back-insolvencia-0.0.1-SNAPSHOT.jar"]