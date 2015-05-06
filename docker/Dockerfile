FROM java:8
MAINTAINER Toshiaki Maki <makingx at gmail.com>

EXPOSE 80
WORKDIR /opt/moneyger4u/
VOLUME ["/tmp"]
RUN mkdir -p /var/log/moneyger4u
ADD moneyger4u.jar /opt/moneyger4u/
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "moneyger4u.jar", "--server.port=80"]