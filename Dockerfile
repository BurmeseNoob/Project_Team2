FROM openjdk:23
COPY ./target/devopsethods.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "devopsethods.jar", "db:33061", "30000"]

