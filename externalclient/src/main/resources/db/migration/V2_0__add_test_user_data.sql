INSERT INTO users ( login, full_name, phone_number, email, birth_day, active, update_date)
SELECT
        'user' || generate_series AS login,
        'Updated user' || generate_series AS full_name,
        '8-999-777-66-55' as phone_nubmer,
        'updated_user' || generate_series || '@example.com' AS email,
        timestamp '1990-01-01' + (random() * (timestamp '2022-01-01' - timestamp '1990-01-01')) AS birth_day,
        true AS active,
        CURRENT_TIMESTAMP AS update_date
FROM generate_series(1, 500);
