FROM openjdk:8-jre
VOLUME /tmp
ADD web-outng-profile-1.0.0-SNAPSHOT.jar outng-profile-app.jar
RUN sh -c 'touch /vendor-app.jar'
EXPOSE 8086
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-Dapp.port=${app.port}", "-jar","/outng-profile-app.jar"]
LABEL maintainer "DAPP"
