FROM openjdk:17
COPY ./target/Project_Team2-0.1.0.3-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java","-jar","Project_Team2-0.1.0.3-jar-with-dependencies.jar"]