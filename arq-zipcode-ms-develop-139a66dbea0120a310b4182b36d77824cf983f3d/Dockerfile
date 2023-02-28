FROM openjdk:8u212-jre-alpine3.9

ARG APP_VERSION
ARG VER

EXPOSE 8080
    
COPY ./ARQ_Zipcode_WS/target/ARQ_Zipcode_WS-${APP_VERSION}.jar /app/arq-zipcode-ws-${APP_VERSION}.jar
COPY ./ARQ_Zipcode_Conf /app

ENTRYPOINT ["/bin/sh","-c", "java -jar /app/arq-zipcode-ws-${APP_VERSION}.jar --spring.config.location=/app/${ENV}/application.properties --app.datasource.ps.password=${PG_PASSWORD}"]
