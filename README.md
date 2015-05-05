# Moneyger4U

"House Keeper" web application which can be shared in your family

## Download executable jar and Run

Download jar from [release page](https://github.com/making/moneyger4u/releases).

    $ java -jar moneyger4u*.jar

Access http://localhost:8080

Login with user1@example.com/password

## Build and Run

    $ mvn package
    $ java -jar target/*.jar

### Build Docker image

    $ mvn clean package
    $ cd target
    $ docker build -t making/moneyger4u .


## Run with Docker

```
docker run -d \
--name moneyger4u \
-p 8080:8080 \
-v /tmp/moneyger4u:/tmp \
-e "_JAVA_OPTIONS=-Duser.timezone=JST -Dlog.verbose=WARN" \
making/moneyger4u \
--spring.thymeleaf.cache=true \
--logging.level.moneyger4u.App=INFO \
--logging.level.moneyger4u=WARN \
--logging.level.jdbc.sqltiming=ERROR \
--logging.level.org=WARN
```

Go to `http://<Docker HOST IP>:8080`