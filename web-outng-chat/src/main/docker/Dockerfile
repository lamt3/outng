FROM openjdk:8-jre
VOLUME /tmp
ADD web-outng-chat-1.0.0-SNAPSHOT.jar outng-chat-app.jar
RUN sh -c 'touch /vendor-app.jar'
EXPOSE 8087
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-Dapp.port=${app.port}", "-jar","/outng-chat-app.jar"]
LABEL maintainer "DAPP"
