INSERT INTO account (user_id, account_name, account_type, balance_date, currency_code, balance) VALUES
(1, 'Current Account', 'Checking', '2024-01-01', 'USD', 100000),
(2, 'Savings Account', 'Savings', '2024-01-01', 'EUR', 150050),
(1, 'Investment Account', 'Investment', '2024-01-01', 'GBP', 2500000);

INSERT INTO transaction (src_account_id, dest_account_id, transaction_date_time, currency_code, is_debit, amount_in_cents, memo)
VALUES
    (1, 2, '2024-08-25 10:30:00', 'USD', TRUE, 5000, 'Grocery shopping'),
    (2, 1, '2024-08-25 11:45:00', 'USD', FALSE, 5000, 'Grocery shopping refund'),
    (3, 4, '2024-08-26 09:15:00', 'EUR', TRUE, 7500, 'Online purchase'),
    (5, 3, '2024-08-27 14:20:00', 'GBP', FALSE, 10000, 'Salary deposit'),
    (3, 6, '2024-08-28 16:00:00', 'GBP', TRUE, 2500, 'Utility bill payment'),
    (7, 4, '2024-08-29 08:45:00', 'JPY', FALSE, 1000000, 'Investment return'),
    (4, 8, '2024-08-30 12:30:00', 'JPY', TRUE, 50000, 'Restaurant bill'),
    (5, 9, '2024-08-31 17:10:00', 'AUD', TRUE, 15000, 'Hotel booking'),
    (10, 5, '2024-09-01 11:00:00', 'AUD', FALSE, 7500, 'Partial refund'),
    (6, 7, '2024-09-02 09:30:00', 'CAD', TRUE, 3000, 'Gift purchase');