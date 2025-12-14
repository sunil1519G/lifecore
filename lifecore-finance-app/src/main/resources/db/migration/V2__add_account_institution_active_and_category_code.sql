-- V2__add_account_institution_active_and_category_code.sql
-- Add institution and active fields to account table
-- Add code field to category table

ALTER TABLE account
    ADD COLUMN institution VARCHAR(100),
    ADD COLUMN active BOOLEAN NOT NULL DEFAULT true;

ALTER TABLE category
    ADD COLUMN code VARCHAR(50);

CREATE INDEX idx_account_active ON account(active);
