FROM openjdk:11

RUN mkdir application

ADD docker-0.0.1-SNAPSHOT.jar /application/app.jar

WORKDIR /application
ENTRYPOINT ["java", "-jar", "app.jar"]