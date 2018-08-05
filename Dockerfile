FROM openjdk:8-alpine

# Required for starting application up.
RUN apk update && apk add bash

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app

COPY target/restfull-api-nos-0.0.1-SNAPSHOT.jar.jar $PROJECT_HOME/RESTfull-API-NOS.jar

WORKDIR $PROJECT_HOME

CMD ["java", "-Dspring.data.mongodb.uri=mongodb://mongo:27017/restfull-api-nos-0.0.1-SNAPSHOT.jar","-Djava.security.egd=file:/dev/./urandom","-jar","./restfull-api-nos-0.0.1-SNAPSHOT.jar.jar"]