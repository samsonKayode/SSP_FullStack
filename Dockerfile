##Build Application Jar file
FROM eclipse-temurin:17-jdk-alpine AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package

##STAGE 2: RUN IT!!!##
FROM eclipse-temurin:17-jre-alpine
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]