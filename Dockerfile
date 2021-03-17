FROM maven:3.6.3-openjdk-11-slim

RUN mkdir /usr/players

COPY . /usr/players

WORKDIR /usr/players

RUN mvn clean install -U

WORKDIR /usr/players/target

EXPOSE 8080

CMD ["java", "-jar", "players-0.0.1-SNAPSHOT.jar"]
