#
# BUILD STAGE
#
FROM maven:3.8.3-openjdk-17 AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

#
# PACKAGE STAGE
#
FROM openjdk:17-jdk-alpine
COPY --from=build /usr/src/app/target/duxus.jar /usr/app/duxus.jar
EXPOSE 8080  
CMD ["java","-jar","/usr/app/duxus.jar"]
