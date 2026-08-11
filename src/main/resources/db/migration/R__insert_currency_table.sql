INSERT INTO data_ref (code, parent_code, name, english_name, description,is_activate, create_by, created_at)
VALUES
    ('CURRENCY', NULL, 'Currency', 'Currency', '',true, 'SYSTEM', CURRENT_TIMESTAMP),
    ('USD', 'CURRENCY', 'ដុល្លា', 'Dollar', 'Dollar',true, 'SYSTEM', CURRENT_TIMESTAMP),
    ('KHR', 'CURRENCY', 'រៀល', 'Riel', 'Riel',true, 'SYSTEM', CURRENT_TIMESTAMP)
ON CONFLICT DO NOTHING;