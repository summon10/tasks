INSERT INTO users (id, username, password, role, is_account_non_locked, failed_login_attempts)
VALUES
    (RANDOM_UUID(), 'moder', '$2a$05$iRmg/ysmUoOued33BACVwup8xT3orNYQhS1fj0TBlByVYgB6uHDJa', 1, true, 0),
    (RANDOM_UUID(), 'ivan', '$2a$05$iRmg/ysmUoOued33BACVwup8xT3orNYQhS1fj0TBlByVYgB6uHDJa', 0, true, 0),
    (RANDOM_UUID(), 'superuser', '$2a$05$iRmg/ysmUoOued33BACVwup8xT3orNYQhS1fj0TBlByVYgB6uHDJa', 2, true, 0);