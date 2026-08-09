INSERT INTO data_ref (code, parent_code, name, english_name, description, create_by, created_at)
VALUES
    ('CURRENCY', NULL, 'Currency', 'Currency', '', 'SYSTEM', CURRENT_TIMESTAMP),
    ('USD', 'CURRENCY', 'ដុល្លា', 'Dollar', 'Dollar', 'SYSTEM', CURRENT_TIMESTAMP),
    ('KHR', 'CURRENCY', 'រៀល', 'Riel', 'Riel', 'SYSTEM', CURRENT_TIMESTAMP)
