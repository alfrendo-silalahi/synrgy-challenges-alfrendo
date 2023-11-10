CREATE
    OR REPLACE PROCEDURE create_user(
    u_username VARCHAR,
    u_email_address VARCHAR,
    u_password VARCHAR,
    OUT new_user_id VARCHAR
)
    LANGUAGE plpgsql
AS
$$
BEGIN
    INSERT INTO users (id, username, email_address, password)
    VALUES (uuid_generate_v4(), u_username, u_email_address, u_password)
    RETURNING id::VARCHAR
        INTO new_user_id;
END;
$$;

CREATE OR REPLACE PROCEDURE update_user(
    u_id UUID,
    u_username VARCHAR,
    u_email_address VARCHAR,
    u_password VARCHAR,
    OUT updated_user_id VARCHAR
)
    LANGUAGE plpgsql
AS
$$
BEGIN
    UPDATE users
    SET username      = u_username,
        email_address = u_email_address,
        password      = u_password
    WHERE id = u_id
    RETURNING id::VARCHAR INTO updated_user_id;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_user(
    u_id UUID
)
    LANGUAGE plpgsql
AS
$$
BEGIN
    UPDATE users
    SET is_deleted = true
    WHERE id = u_id;
END;
$$;