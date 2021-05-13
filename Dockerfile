FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/coinrates.jar
ADD ${JAR_FILE} coinrates.jar
ENTRYPOINT ["java","-jar","/coinrates.jar"]