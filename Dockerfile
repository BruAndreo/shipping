FROM gradle:8.7 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:21-ea-1-jdk-slim
COPY --from=build /home/gradle/src/build/libs/shipping-0.0.1-SNAPSHOT.jar /app/
RUN bash -c 'touch /app/shipping-0.0.1-SNAPSHOT.jar'
EXPOSE 3001
ENTRYPOINT [
    "java",
    "-XX:+UnlockExperimentalVMOptions",
    "-XX:+UseContainerSupport",
    "-Djava.security.egd=file:/dev/./urandom",
    "-jar",
    "/app/shipping-0.0.1-SNAPSHOT.jar"
]