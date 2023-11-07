CREATE  table IF NOT EXISTS user_role
(
    id        BIGSERIAL                NOT NULL
    CONSTRAINT user_role_pkey
    PRIMARY KEY,
    name     varchar(64)           NOT NULL
);

CREATE table IF NOT EXISTS department
(
    id        BIGSERIAL                NOT NULL
    CONSTRAINT department_pkey
    PRIMARY KEY,
    name     varchar(64)           NOT NULL,
    active  boolean DEFAULT FALSE NOT NULL
);



CREATE table IF NOT EXISTS users
    (
      id        BIGSERIAL              NOT NULL
          CONSTRAINT user_pkey
              PRIMARY KEY,
      login     varchar(64)           NOT NULL,
      password  varchar(256)          NOT NULL,
      full_name varchar(256)          NOT NULL,
      role_id   bigint
          CONSTRAINT fk_user_role_id
              REFERENCES user_role,
      email     varchar(128),
      department_id        bigint
          CONSTRAINT fk_user_department_id
              REFERENCES department,
      birth_day            timestamp NOT NULL,
      active  boolean DEFAULT FALSE NOT NULL,
      update_date            timestamp NOT NULL
  );

