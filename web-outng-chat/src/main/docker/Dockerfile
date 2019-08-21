FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ENV AWS_REGION="us-west-2" 
ADD web-outng-chat-1.0.0-SNAPSHOT.jar outng-chat-app.jar
RUN sh -c 'touch /outng-chat-app.jar'
EXPOSE 8087
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar", "-Dapp.port=${app.port} -Dspring.profiles.active=dev","/outng-chat-app.jar"]
LABEL maintainer "DAPP"
