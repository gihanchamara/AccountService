DROP TABLE IF EXISTS account;

CREATE TABLE account (
    account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    account_name VARCHAR(255) NOT NULL,
    account_type VARCHAR(50) NOT NULL,
    balance_date DATE NOT NULL,
    currency_code VARCHAR(3) NOT NULL,
    balance BIGINT NOT NULL,
    time_create TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    time_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS transaction;
CREATE TABLE transaction (
    transaction_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    src_account_id BIGINT NOT NULL,
    dest_account_id BIGINT NOT NULL,
    transaction_date_time TIMESTAMP NOT NULL,
    currency_code VARCHAR(3) NOT NULL,
    is_debit BOOLEAN NOT NULL,
    amount_in_cents BIGINT NOT NULL,
    memo VARCHAR(255)
);