ALTER TABLE artwork
    ADD COLUMN length float;

ALTER TABLE artwork
    ADD COLUMN width float;

ALTER TABLE artwork
    ADD COLUMN height float;

ALTER TABLE artwork
DROP COLUMN size;