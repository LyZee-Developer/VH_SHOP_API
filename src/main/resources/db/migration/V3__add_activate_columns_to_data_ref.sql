ALTER TABLE data_ref
    ADD COLUMN is_activate BOOLEAN,
    ADD COLUMN updated_at  TIMESTAMP,
    ADD COLUMN update_by   VARCHAR(255);