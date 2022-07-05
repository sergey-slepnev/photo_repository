CREATE TABLE photographer
(
    id             SERIAL
        PRIMARY KEY,
    name           VARCHAR(128) NOT NULL,
    surname        VARCHAR(128) NOT NULL,
    phone_number   VARCHAR(32)  NOT NULL
        UNIQUE,
    social_network VARCHAR(128)
        UNIQUE,
    is_active      BOOLEAN      NOT NULL,
    photo_id       BIGINT
);

CREATE TABLE photo_theme
(
    id   SERIAL
        PRIMARY KEY,
    name VARCHAR(64) NOT NULL
        UNIQUE
);

CREATE TABLE photo_format
(
    id     INTEGER      NOT NULL
        PRIMARY KEY,
    format VARCHAR(128) NOT NULL
);

CREATE TABLE resolution_value
(
    id    INTEGER     NOT NULL
        PRIMARY KEY,
    value VARCHAR(64) NOT NULL
        UNIQUE
);

CREATE TABLE photo
(
    id                BIGSERIAL
        PRIMARY KEY,
    photographer_id   INTEGER
        REFERENCES photographer,
    photo_theme_id    INTEGER
        REFERENCES photo_theme,
    photo_format_id   INTEGER
        REFERENCES photo_format,
    resolution_id     INTEGER
        REFERENCES resolution_value,
    size              NUMERIC(4, 2),
    is_free           BOOLEAN      NOT NULL,
    price             NUMERIC(4, 2),
    added_time        TIMESTAMP    NOT NULL,
    registration_date VARCHAR(128) NOT NULL
);

ALTER TABLE photographer
    ADD FOREIGN KEY (photo_id) REFERENCES photo;

CREATE TABLE site_user
(
    id       SERIAL
        PRIMARY KEY,
    email    VARCHAR(128)
        UNIQUE,
    password VARCHAR(128) NOT NULL
);

CREATE TABLE user_order
(
    id       INTEGER       NOT NULL
        PRIMARY KEY,
    photo_id BIGINT
        REFERENCES photo,
    cost     NUMERIC(5, 2) NOT NULL,
    is_paid  BOOLEAN       NOT NULL
);

CREATE TABLE admin
(
    id              SERIAL
        PRIMARY KEY,
    email           VARCHAR(128)
        UNIQUE,
    password        VARCHAR(128) NOT NULL,
    photographer_id INTEGER
        REFERENCES photographer,
    site_user_id    INTEGER
        REFERENCES site_user
);


