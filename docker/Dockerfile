FROM dockerfile/java:oracle-java8
MAINTAINER Toshiaki Maki <makingx at gmail.com>

ADD moneyger4u.jar /opt/moneyger4u/
EXPOSE 8080 8443
WORKDIR /opt/moneyger4u/
VOLUME ["/tmp"]
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms256m", "-Xmx512m", "-jar", "moneyger4u.jar"]