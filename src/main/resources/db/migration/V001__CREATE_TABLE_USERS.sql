 CREATE TABLE IF NOT EXISTS USERS (
    ID BIGSERIAL NOT NULL PRIMARY KEY,
    NAME CHARACTER VARYING(100),
    EMAIL CHARACTER VARYING(100),
    PASSWORD CHARACTER VARYING(20),
    DATE_REGISTER DATE DEFAULT NOW()
);