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
    $ mvn docker:build


## Run with Docker

```
docker run --rm --name moneyger4u \
 -e "_JAVA_OPTIONS=-Duser.timezone=JST \
 -Dlogging.level.jdbc.sqltiming=ERROR \
 -Dlogging.level.org=WARN \
 " \
 -v /tmp/moneyger4u:/tmp \
 -p 80:8080 \
 making/moneyger4u
```