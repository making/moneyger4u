## Prerequisites

* JDK6+
* MySQL

## Install

    $ mysql -u root
    DROP DATABASE IF EXISTS `moneyger4u`;
    CREATE DATABASE `moneyger4u` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
    USE moneyger4u;
    SOURCE ddl/moneyger4u.ddl;

## Run

    $ export DATABASE_URL_M=mysql://user:pass@hostname:port/databasename
    (or "set DATABASE_URL_M=mysql://user:pass@hostname:port/databasename" for windows)
    $ mvn jetty:run
    or
    $ mvm tomcat7:run
    
## License

Licensed under the Apache License, Version 2.0.
