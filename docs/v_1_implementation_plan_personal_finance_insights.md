# V1 Implementation Plan

## Personal Finance Insights Platform (Read-Only AI)

This document defines a **step-by-step execution plan** to implement V1 based on the approved Architecture, HLD, and LLD.

The plan is structured to:
- Build trust first
- Reduce rework
- Keep AI integration safe and late

---

## Guiding Principles

1. **Backend truth before AI** – AI comes last
2. **Deterministic before intelligent** – summaries first
3. **Read-only AI** – no exceptions in V1
4. **Vertical slices** – finish features end-to-end

---

## Phase Breakdown Overview

| Phase | Duration | Outcome |
|-----|--------|--------|
| Phase 0 | Week 1 | Project foundation |
| Phase 1 | Weeks 2–4 | Core finance backend |
| Phase 2 | Weeks 5–6 | Summaries & budgets |
| Phase 3 | Weeks 7–8 | Web UI |
| Phase 4 | Weeks 9–10 | AI + RAG |
| Phase 5 | Weeks 11–12 | Hardening & release |

---

## Phase 0 – Foundation (Week 1)

### Goals
- Stable development baseline

### Tasks
- Repo setup (backend + frontend)
- Docker & local environment
- PostgreSQL setup
- Basic CI (build + test)
- Logging framework

### Exit Criteria
- App boots locally
- DB migrations run successfully

---

## Phase 1 – Core Finance Backend (Weeks 2–4)

### Goals
- Authoritative finance data model
- CSV ingestion pipeline

### Tasks
- User & auth module
- Account, category, transaction schemas
- CSV import service
- Deduplication logic
- Transaction query APIs

### Exit Criteria
- Transactions visible via API
- Category overrides persist
- No AI involved

---

## Phase 2 – Summaries & Budgets (Weeks 5–6)

### Goals
- Deterministic, AI-safe summaries

### Tasks
- Monthly summary jobs
- Category summaries
- Account summaries
- Budget CRUD
- Budget vs actual calculations

### Exit Criteria
- All summary APIs return correct values
- Numbers match raw data

---

## Phase 3 – Web UI (Weeks 7–8)

### Goals
- Usable personal finance dashboard

### Tasks
- Login screen
- CSV upload UI
- Transaction table
- Monthly overview dashboard
- Category breakdown charts
- Budget comparison view

### Exit Criteria
- End-to-end finance flow usable without AI

---

## Phase 4 – AI & RAG Integration (Weeks 9–10)

### Goals
- Safe, read-only AI explanations

### Tasks
- Vector DB setup
- Summary embedding pipeline
- AI context APIs
- Prompt templates
- AI chat UI

### Guardrails
- Summaries only
- No raw data access
- No recommendations

### Exit Criteria
- AI answers match summary data
- No hallucinated numbers

---

## Phase 5 – Hardening & Release (Weeks 11–12)

### Goals
- Stability and trust

### Tasks
- Audit logging
- Rate limiting
- Error handling
- Data reset & re-import
- Performance tuning
- Documentation

### Exit Criteria
- System stable for daily personal use
- AI behavior predictable

---

## Definition of Done (V1)

V1 is complete when:
- Finance insights work without AI
- AI explanations are correct and conservative
- No financial actions possible
- System runs locally and reliably

---

## Explicitly Deferred to V2

- Family / multi-user support
- Bank integrations
- Device management
- Automation
- AI recommendations

---

**Next Step:** Begin **Phase 0 – Foundation**.

