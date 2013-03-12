## Install

    $ mysql -u root
    DROP DATABASE IF EXISTS `moneyger4u`;
    CREATE DATABASE `moneyger4u` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
    USE moneyger4u;
    SOURCE ddl/moneyger4u.ddl;
