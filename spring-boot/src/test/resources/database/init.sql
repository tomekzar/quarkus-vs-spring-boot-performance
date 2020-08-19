BEGIN;

SET client_encoding = 'UTF8';

CREATE TABLE CITY
(
    ID           integer      NOT NULL,
    NAME         text         NOT NULL,
    COUNTRY_CODE character(3) NOT NULL,
    POPULATION   integer      NOT NULL
);

INSERT INTO CITY
VALUES (1, 'Kabul', 'AFG', 1780000),
       (2, 'Qandahar', 'AFG', 237500),
       (3, 'Herat', 'AFG', 186800),
       (4, 'Mazar-e-Sharif', 'AFG', 127800),
       (5, 'Amsterdam', 'NLD', 731200),
       (6, 'Rotterdam', 'NLD', 593321);

ALTER TABLE ONLY CITY
    ADD CONSTRAINT CITY_PKEY PRIMARY KEY (ID);

CREATE INDEX CITY_COUNTRY_CODE_IDX ON CITY (COUNTRY_CODE);

COMMIT;