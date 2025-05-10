FROM alpine/java:22-jdk

COPY ./target/Health-Measurement-0.0.1-SNAPSHOT.jar app-0.0.1.jar

ENTRYPOINT [ "java", "-jar", "app-0.0.1.jar" ]