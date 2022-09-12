FROM amazoncorretto:11-alpine-jdk
MAINTAINER baeldung.com
COPY target/backbase-0.0.1-SNAPSHOT.jar backbase-1.0.0.jar
#ENTRYPOINT ["java","-Dspring.profiles.active=dev", "-jar","/backbase-1.0.0.jar"]
ENTRYPOINT ["java", "-jar","/backbase-1.0.0.jar"]