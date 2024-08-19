FROM openjdk:12-jdk-alpine

RUN apk add --no-cache bash

WORKDIR /bakery-backend

CMD ./gradle run

