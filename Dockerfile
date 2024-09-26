FROM openjdk:17
COPY ./target/Project_Team2-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java","-jar","Project_Team2-1.0-SNAPSHOT-jar-with-dependencies.jar"]