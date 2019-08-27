ALTER TABLE usertable ADD COLUMN role CHAR(1) DEFAULT 'U';
UPDATE usertable SET role='U';