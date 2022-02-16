FROM openjdk:11
LABEL maintainer="adorabilstone@gmail.com"
VOLUME /tmp
EXPOSE 8080
ADD target/ms-msisdn-categorizer-service-0.0.1.jar ms-msisdn-categorizer-service.jar
ENV TZ=Africa/Nairobi
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-Xmx512m","-Djava.security.egd=file:/dev/./urandom","-jar","/ms-msisdn-categorizer-service.jar"]
