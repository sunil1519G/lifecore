-- V1__init_finance_core.sql
-- Flyway V1 migration for lifecore-finance
-- Authoritative finance schema (AI has NO direct access)

-- =========================
-- USERS (single user in V1)
-- =========================
CREATE TABLE lc_user (
    id              UUID PRIMARY KEY,
    username        VARCHAR(100) NOT NULL UNIQUE,
    password_hash   VARCHAR(255) NOT NULL,
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- =========================
-- ACCOUNTS
-- =========================
CREATE TABLE account (
    id          UUID PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    type        VARCHAR(50) NOT NULL, -- SAVINGS, CREDIT_CARD, CASH, WALLET
    currency    VARCHAR(3) NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_account_type ON account(type);

-- =========================
-- CATEGORIES
-- =========================
CREATE TABLE category (
    id          UUID PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    parent_id   UUID NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_category_parent
        FOREIGN KEY (parent_id) REFERENCES category(id)
);

CREATE UNIQUE INDEX ux_category_name ON category(name);

-- =========================
-- TRANSACTIONS (IMMUTABLE)
-- =========================
CREATE TABLE transaction (
    id              UUID PRIMARY KEY,
    account_id      UUID NOT NULL,
    txn_date        DATE NOT NULL,
    description     VARCHAR(255) NOT NULL,
    amount          NUMERIC(14, 2) NOT NULL,
    direction       VARCHAR(10) NOT NULL, -- CREDIT / DEBIT
    category_id     UUID NOT NULL,
    source          VARCHAR(50) NOT NULL, -- CSV, MANUAL
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_txn_account
        FOREIGN KEY (account_id) REFERENCES account(id),
    CONSTRAINT fk_txn_category
        FOREIGN KEY (category_id) REFERENCES category(id)
);

-- Indexes for query performance
CREATE INDEX idx_txn_date ON transaction(txn_date);
CREATE INDEX idx_txn_account ON transaction(account_id);
CREATE INDEX idx_txn_category ON transaction(category_id);
CREATE INDEX idx_txn_direction ON transaction(direction);

-- Deduplication helper index
CREATE INDEX idx_txn_dedupe ON transaction(account_id, txn_date, amount, description);

-- =========================
-- BUDGETS (STATIC, V1)
-- =========================
CREATE TABLE budget (
    id              UUID PRIMARY KEY,
    category_id     UUID NOT NULL,
    monthly_limit   NUMERIC(14, 2) NOT NULL,
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_budget_category
        FOREIGN KEY (category_id) REFERENCES category(id),
    CONSTRAINT ux_budget_category UNIQUE (category_id)
);

-- =========================
-- MONTHLY CATEGORY SUMMARY (AI-SAFE)
-- =========================
CREATE TABLE monthly_category_summary (
    month               DATE NOT NULL, -- first day of month
    category_id         UUID NOT NULL,
    total_spent         NUMERIC(14, 2) NOT NULL,
    transaction_count   INTEGER NOT NULL,

    PRIMARY KEY (month, category_id),
    CONSTRAINT fk_mcs_category
        FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE INDEX idx_mcs_month ON monthly_category_summary(month);

-- =========================
-- MONTHLY ACCOUNT SUMMARY (AI-SAFE)
-- =========================
CREATE TABLE monthly_account_summary (
    month           DATE NOT NULL,
    account_id      UUID NOT NULL,
    total_inflow    NUMERIC(14, 2) NOT NULL,
    total_outflow   NUMERIC(14, 2) NOT NULL,

    PRIMARY KEY (month, account_id),
    CONSTRAINT fk_mas_account
        FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE INDEX idx_mas_month ON monthly_account_summary(month);

-- =========================
-- MONTHLY OVERALL SUMMARY (AI-SAFE)
-- =========================
CREATE TABLE monthly_overall_summary (
    month           DATE PRIMARY KEY,
    total_income    NUMERIC(14, 2) NOT NULL,
    total_expense   NUMERIC(14, 2) NOT NULL,
    net_balance     NUMERIC(14, 2) NOT NULL
);

-- =========================
-- SUBSCRIPTION SUMMARY (HEURISTIC)
-- =========================
CREATE TABLE subscription_summary (
    merchant_name       VARCHAR(150) PRIMARY KEY,
    monthly_average     NUMERIC(14, 2) NOT NULL,
    transaction_count   INTEGER NOT NULL
);

-- =========================
-- AUDIT EVENTS (FOUNDATION)
-- =========================
CREATE TABLE audit_event (
    id              UUID PRIMARY KEY,
    event_type      VARCHAR(50) NOT NULL,
    entity_type     VARCHAR(50) NOT NULL,
    entity_id       UUID NULL,
    metadata        JSONB NULL,
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_audit_event_type ON audit_event(event_type);
CREATE INDEX idx_audit_event_time ON audit_event(created_at);
