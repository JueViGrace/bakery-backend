FROM openjdk:21

EXPOSE 5000:5000

RUN MKDIR /app

COPY ./server/build/libs/*-all.jar /app/bakery.jar

ENTRYPOINT["java", "-jar", "/app/bakery.jar"]
