CREATE TABLE data_ref
(
    id           BIGSERIAL PRIMARY KEY,
    code         VARCHAR(255) NOT NULL,
    parent_code  VARCHAR(255),
    name         VARCHAR(255) NOT NULL,
    english_name VARCHAR(255),
    description  VARCHAR(255),
    create_by    VARCHAR(255) NOT NULL,
    created_at   TIMESTAMP
);
