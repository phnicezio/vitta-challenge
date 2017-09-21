FROM java:8
MAINTAINER Paulo Nicezio
RUN mkdir -p /opt/vitta-challenge-api
WORKDIR /opt/vitta-challenge-api
COPY vitta-challenge-api/target/vitta-challenge-api-0.0.1-SNAPSHOT.jar vitta-challenge-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "vitta-challenge-api-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080