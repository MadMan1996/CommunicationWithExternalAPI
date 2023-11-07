INSERT INTO users ( login, password, full_name, role_id, email, department_id, birth_day, active, update_date)
SELECT
        'user' || generate_series AS login,
        'password' || generate_series AS password,
        'User ' || generate_series AS full_name,
        CASE
            WHEN random() < 0.37 THEN 1
            ELSE floor(random() * 10) + 1
        END AS role_id,
        'user' || generate_series || '@example.com' AS email,
        floor(random() * 10) + 1 AS department_id,
        timestamp '1990-01-01' + (random() * (timestamp '2022-01-01' - timestamp '1990-01-01')) AS birth_day,
        random() < 0.45 AS active,
        CURRENT_TIMESTAMP - (FLOOR(RANDOM() * 50)) * INTERVAL '1 DAY' AS update_date
FROM generate_series(1, 500);
