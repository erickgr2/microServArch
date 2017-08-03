FROM java:8
MAINTAINER Erick Garcia
ADD config-0.0.1-SNAPSHOT.jar config-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-Xmx312m", "-Duser.language=en", "-Dspring.profiles.active=dev", "-jar","ewt-ms-0.0.1-SNAPSHOT.jar"]