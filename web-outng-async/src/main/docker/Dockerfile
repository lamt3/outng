FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ENV AWS_REGION="us-west-2" 
ADD web-outng-async-1.0.0-SNAPSHOT.jar outng-async-app.jar
RUN sh -c 'touch /outng-async-app.jar'
EXPOSE 8088
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar", "-Dapp.port=${app.port} -Dspring.profiles.active=dev", "/outng-async-app.jar"]
LABEL maintainer "DAPP"
