FROM gradle:8.6.0-jdk21 as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM eclipse-temurin:21-alpine
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/ktor-sample-all.jar /app/ktor-sample-all.jar
ENTRYPOINT ["java", "-jar", "/app/ktor-sample-all.jar"]