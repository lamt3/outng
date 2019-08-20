FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ADD web-outng-async-1.0.0-SNAPSHOT.jar outng-async-app.jar
RUN sh -c 'touch /outng-async-app.jar'
EXPOSE 8088
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-Dapp.port=${app.port}", "-Dspring.profiles.active=dev", "-jar","/outng-async-app.jar"]
LABEL maintainer "DAPP"
