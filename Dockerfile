FROM maven:latest AS build-stage
COPY . /build
WORKDIR /build
RUN mvn clean package -Pprod -DskipTests

FROM openjdk:11.0.11-jre AS runtime
WORKDIR /opt/help-queue
COPY --from=build-stage /build/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]