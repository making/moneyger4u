CREATE TABLE daily_outcome (
  daily_outcome_id          SERIAL,
  amount                    INTEGER      NOT NULL,
  created_at                TIMESTAMP    NOT NULL,
  is_waste                  BOOLEAN      NOT NULL,
  outcome_date              DATE         NOT NULL,
  outcome_name              VARCHAR(255) NOT NULL,
  payment                   VARCHAR(255) NOT NULL,
  quantity                  INTEGER      NOT NULL,
  remarks                   VARCHAR(255),
  updated_at                TIMESTAMP    NOT NULL,
  version                   INTEGER,
  daily_outcome_category_id INTEGER      NOT NULL,
  updated_by                INTEGER      NOT NULL,
  user_id                   INTEGER      NOT NULL,
  PRIMARY KEY (daily_outcome_id)
);

CREATE TABLE daily_outcome_category (
  daily_outcome_category_id  SERIAL,
  category_name              VARCHAR(255) NOT NULL,
  created_at                 TIMESTAMP    NOT NULL,
  updated_at                 TIMESTAMP    NOT NULL,
  version                    INTEGER,
  parent_outcome_category_id INTEGER      NOT NULL,
  PRIMARY KEY (daily_outcome_category_id),
  UNIQUE (DAILY_OUTCOME_CATEGORY_ID, CATEGORY_NAME)
);

CREATE TABLE family (
  family_id   SERIAL,
  created_at  TIMESTAMP    NOT NULL,
  family_name VARCHAR(255) NOT NULL,
  updated_at  TIMESTAMP    NOT NULL,
  version     INTEGER,
  PRIMARY KEY (family_id)
);

CREATE TABLE income (
  income_id          SERIAL,
  amount             INTEGER      NOT NULL,
  created_at         TIMESTAMP    NOT NULL,
  income_date        DATE         NOT NULL,
  income_name        VARCHAR(255) NOT NULL,
  updated_at         TIMESTAMP    NOT NULL,
  version            INTEGER,
  family_id          INTEGER      NOT NULL,
  income_category_id INTEGER      NOT NULL,
  PRIMARY KEY (income_id)
);

CREATE TABLE income_category (
  income_category_id SERIAL,
  category_name      VARCHAR(255) NOT NULL,
  created_at         TIMESTAMP    NOT NULL,
  updated_at         TIMESTAMP    NOT NULL,
  version            INTEGER,
  PRIMARY KEY (income_category_id),
  UNIQUE (category_name)
);

CREATE TABLE monthly_outcome (
  monthly_outcome_id          SERIAL,
  amount                      INTEGER   NOT NULL,
  created_at                  TIMESTAMP NOT NULL,
  outcome_date                DATE      NOT NULL,
  outcome_name                VARCHAR(255),
  quantity                    DECIMAL(19, 2),
  remarks                     VARCHAR(255),
  updated_at                  TIMESTAMP NOT NULL,
  version                     INTEGER,
  family_id                   INTEGER   NOT NULL,
  monthly_outcome_category_id INTEGER   NOT NULL,
  updated_by                  INTEGER   NOT NULL,
  PRIMARY KEY (monthly_outcome_id)
);

CREATE TABLE monthly_outcome_category (
  monthly_outcome_category_id SERIAL,
  category_name               VARCHAR(255) NOT NULL,
  created_at                  TIMESTAMP    NOT NULL,
  unit_name                   VARCHAR(255),
  updated_at                  TIMESTAMP    NOT NULL,
  version                     INTEGER,
  PRIMARY KEY (monthly_outcome_category_id)
);

CREATE TABLE parent_outcome_category (
  parent_outcome_category_id SERIAL,
  category_name              VARCHAR(255) NOT NULL,
  created_at                 TIMESTAMP    NOT NULL,
  updated_at                 TIMESTAMP    NOT NULL,
  version                    INTEGER,
  PRIMARY KEY (parent_outcome_category_id),
  UNIQUE (category_name)
);

CREATE TABLE role (
  role_name  VARCHAR(255) NOT NULL,
  created_at TIMESTAMP    NOT NULL,
  updated_at TIMESTAMP    NOT NULL,
  version    INTEGER,
  PRIMARY KEY (role_name)
);

CREATE TABLE "user" (
  user_id    SERIAL,
  created_at TIMESTAMP    NOT NULL,
  email      VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  updated_at TIMESTAMP    NOT NULL,
  version    INTEGER,
  family_id  INTEGER      NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE user_role (
  user_id   INTEGER      NOT NULL,
  role_name VARCHAR(255) NOT NULL
);

ALTER TABLE daily_outcome
ADD CONSTRAINT FK953B632C4DA3B3E0
FOREIGN KEY (daily_outcome_category_id)
REFERENCES daily_outcome_category;

ALTER TABLE daily_outcome
ADD CONSTRAINT FK953B632C54DAC73A
FOREIGN KEY (user_id)
REFERENCES "user";

ALTER TABLE daily_outcome
ADD CONSTRAINT FK953B632C4C036B86
FOREIGN KEY (updated_by)
REFERENCES "user";

ALTER TABLE daily_outcome_category
ADD CONSTRAINT FK88EB5CB162147E80
FOREIGN KEY (parent_outcome_category_id)
REFERENCES parent_outcome_category;

ALTER TABLE income
ADD CONSTRAINT FKB969A1A99701C9A
FOREIGN KEY (family_id)
REFERENCES family;

ALTER TABLE income
ADD CONSTRAINT FKB969A1A972FF358D
FOREIGN KEY (income_category_id)
REFERENCES income_category;

ALTER TABLE monthly_outcome
ADD CONSTRAINT FK153AE1209701C9A
FOREIGN KEY (family_id)
REFERENCES family;

ALTER TABLE monthly_outcome
ADD CONSTRAINT FK153AE1204C036B86
FOREIGN KEY (updated_by)
REFERENCES "user";

ALTER TABLE monthly_outcome
ADD CONSTRAINT FK153AE120FF04B360
FOREIGN KEY (monthly_outcome_category_id)
REFERENCES monthly_outcome_category;

ALTER TABLE "user"
ADD CONSTRAINT FK36EBCB9701C9A
FOREIGN KEY (family_id)
REFERENCES family;

ALTER TABLE user_role
ADD CONSTRAINT FK143BF46A71A2FA8A
FOREIGN KEY (role_name)
REFERENCES role;

ALTER TABLE user_role
ADD CONSTRAINT FK143BF46A54DAC73A
FOREIGN KEY (user_id)
REFERENCES "user";