CREATE TABLE accounts
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_by         VARCHAR(45) NULL,
    created_date       TIMESTAMP               DEFAULT CURRENT_TIMESTAMP NULL,
    last_modified_by   VARCHAR(48) NULL,
    last_modified_date TIMESTAMP NULL,

    number             VARCHAR(250)   NOT NULL UNIQUE,
    type               VARCHAR(10)    NOT NULL,
    opening_balance    DECIMAL(10, 2) NOT NULL,
    status             BOOLEAN        NOT NULL DEFAULT TRUE,
    customer_id        BIGINT         NOT NULL
);

CREATE TABLE movements
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_by         VARCHAR(45) NULL,
    created_date       TIMESTAMP               DEFAULT CURRENT_TIMESTAMP NULL,
    last_modified_by   VARCHAR(48) NULL,
    last_modified_date TIMESTAMP NULL,

    date               DATE           NOT NULL,
    type               VARCHAR(10)    NOT NULL,
    value              DECIMAL(10, 2) NOT NULL DEFAULT 0,
    balance            DECIMAL(10, 2) NOT NULL,
    account_id         BIGINT         NOT NULL,

    CONSTRAINT fk_movements_account FOREIGN KEY (account_id) REFERENCES accounts (id)
);
