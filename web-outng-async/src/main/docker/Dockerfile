FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ENV AWS_REGION="us-west-2" AWS_SECRET_KEY="x2d9WG0e+WMalwPGM8W/w+iSyNQutPd0mY5lIwAn" AWS_ACCESS_KEY_ID="AKIAJ4ISON6SWMCXPK3A"
ADD web-outng-async-1.0.0-SNAPSHOT.jar outng-async-app.jar
RUN sh -c 'touch /outng-async-app.jar'
EXPOSE 8088
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar", "-Dapp.port=${app.port} -Dspring.profiles.active=dev", "/outng-async-app.jar"]
LABEL maintainer "DAPP"
