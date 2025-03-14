CREATE TABLE persons
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_by         VARCHAR(45)                         NULL,
    created_date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
    last_modified_by   VARCHAR(48)                         NULL,
    last_modified_date TIMESTAMP                           NULL,

    name               VARCHAR(250)                        NOT NULL,
    gender             VARCHAR(10)                         NOT NULL,
    date_of_birth      DATE                                NOT NULL,
    identification     VARCHAR(256)                        NOT NULL UNIQUE,
    address            VARCHAR(256),
    phone              VARCHAR(20)
);

CREATE TABLE customers
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_by         VARCHAR(45)  NULL,
    created_date       TIMESTAMP             DEFAULT CURRENT_TIMESTAMP NULL,
    last_modified_by   VARCHAR(48)  NULL,
    last_modified_date TIMESTAMP    NULL,

    password           VARCHAR(255) NOT NULL,
    status             TINYINT(1)   NOT NULL DEFAULT 0,
    person_id          BIGINT       NOT NULL UNIQUE,

    CONSTRAINT fk_customers_person FOREIGN KEY (person_id) REFERENCES persons (id) ON DELETE CASCADE
);
