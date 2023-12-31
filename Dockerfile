FROM openjdk:17-alpine

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE} receba.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","receba.jar"]