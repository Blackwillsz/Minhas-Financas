 CREATE TABLE IF NOT EXISTS ENTRY (
    ID BIGSERIAL NOT NULL PRIMARY KEY,
    DESCRIPTION CHARACTER VARYING(100) NOT NULL,
    MONTH INTEGER NOT NULL,
    YEAR INTEGER NOT NULL,
    AMOUNT NUMERIC(16,2),
    TYPE CHARACTER VARYING(20) CHECK ( TYPE IN ('REVENUE', 'EXPENSE')) NOT NULL,
    STATUS CHARACTER VARYING(20) CHECK (STATUS IN ('PENDING', 'CANCELED', 'MADE EFFECTIVE')) NOT NULL,
    ID_USER BIGINT REFERENCES USERS (ID) NOT NULL,
    DATE_REGISTER DATE DEFAULT NOW()
);