## Base ##
FROM maven:3.8.5-eclipse-temurin-17 as base
WORKDIR /home/app
COPY pom.xml google-checkstyle.xml ./
RUN mvn dependency:go-offline -B -ntp
COPY src ./src

## Test ##
FROM base as unit_test
ENTRYPOINT [ "mvn", "test", "-B", "-ntp" ]

## Build ##
FROM base as build
RUN mvn package -DskipTests -B -ntp

## Final ##
FROM eclipse-temurin:17-jre as final
EXPOSE 8080
COPY --from=build /home/app/target/service-portal-*-SNAPSHOT.jar /service-portal.jar
ENTRYPOINT ["java", "-jar", "/service-portal.jar"]
CMD ["--logging.config=classpath:log4j2-docker.xml"]