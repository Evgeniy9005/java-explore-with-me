FROM amazoncorretto:11-alpine-jdk
COPY /service/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]