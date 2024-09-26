FROM openjdk:17

COPY ./target/classes/ProjectTeam2 /tmp/ProjectTeam2
WORKDIR /tmp
ENTRYPOINT ["java","ProjectTeam2.App"]