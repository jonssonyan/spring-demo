FROM java:8
MAINTAINER "Jonsson Yan"
EXPOSE 9092
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/springboot-docker.jar"]