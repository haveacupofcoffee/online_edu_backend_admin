FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/service_aws-8002.jar service_aws-8002.jar
ENTRYPOINT ["java","-jar","/service_aws-8002.jar", "&"]