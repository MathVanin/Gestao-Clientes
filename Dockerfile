FROM openjdk:11-jre-slim

VOLUME /tmp

COPY target/gestao-clientes-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app.jar"]