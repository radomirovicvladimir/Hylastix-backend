# Simple Dockerfile without building
FROM openjdk:21-jdk-slim

WORKDIR /hylastix

# Copy from local machine, not from a "builder"
COPY target/*.jar app.jar

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=mysqldev
ENV DATASOURCE_USERNAME=hylastixDB
ENV DATASOURCE_PASSWORD=gr@yman123
ENV JWTSECRET=9000MetaraNaNebuSamJaZaTrenKadMiOsmehDas

ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -Ddatasource.username=$DATASOURCE_USERNAME -Ddatasource.password=$DATASOURCE_PASSWORD -Djwt.secret=$JWTSECRET -jar app.jar"]
