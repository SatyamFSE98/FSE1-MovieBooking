FROM adoptopenjdk/openjdk17:alpine-jre
VOLUME /tmp
COPY target/moviebookingapp-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]