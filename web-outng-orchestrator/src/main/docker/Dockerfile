FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ENV AWS_REGION="us-west-2"
ADD web-outng-orchestrator-1.0.0-SNAPSHOT.jar outng-orchestrator-app.jar
RUN sh -c 'touch /outng-orchestrator-app.jar'
EXPOSE 8086
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-Dapp.port=${app.port}", "-Dspring.profiles.active=dev", "-jar","/outng-orchestrator-app.jar"]
LABEL maintainer "DAPP"
