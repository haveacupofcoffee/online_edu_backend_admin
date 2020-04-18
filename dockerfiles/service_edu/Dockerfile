FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/service_edu-8001.jar service_edu-8001.jar
ENTRYPOINT ["java","-jar","/service_edu-8001.jar", "&"]