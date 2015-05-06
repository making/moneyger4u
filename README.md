# Moneyger4U

"House Keeper" web application which can be shared in your family

## Download executable jar and Run

Download jar from [release page](https://github.com/making/moneyger4u/releases).

    $ java -jar moneyger4u*.jar

Access http://localhost:8080

Login with user1@example.com/password

## Run with Docker

Docker image is already deployed. You can run Monger4u soon with Docker!

```
docker run -d \
--name moneyger4u \
-p 80:80 \
-v /tmp/moneyger4u:/tmp \
-v /var/log/moneyger4u:/var/log/moneyger4u \
-e "_JAVA_OPTIONS=-Duser.timezone=JST" \
making/moneyger4u \
--spring.thymeleaf.cache=true \
--log.verbose=WARN \
--logging.file=/var/log/moneyger4u/app.log \
--logging.level.moneyger4u.App=INFO \
--logging.level.moneyger4u=WARN \
--logging.level.jdbc.sqltiming=ERROR \
--logging.level.org=WARN \
--logging.level./=WARN
```

Go to `http://<Docker HOST IP>`

## Build and Run

    $ mvn package
    $ java -jar target/*.jar

### Build Docker image

    $ mvn clean package
    $ cd target
    $ docker build -t making/moneyger4u .
