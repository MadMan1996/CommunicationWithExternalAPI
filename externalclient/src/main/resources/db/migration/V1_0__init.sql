
CREATE table IF NOT EXISTS users
    (
      id        BIGSERIAL              NOT NULL
          CONSTRAINT user_pkey
              PRIMARY KEY,
      login     varchar(64)           NOT NULL,
      full_name varchar(256)          NOT NULL,
      email     varchar(128),
      birth_day            timestamp NOT NULL,
      phone_number varchar(64) NOT NULL,
      active  boolean DEFAULT FALSE NOT NULL,
      update_date            timestamp NOT NULL
  );

