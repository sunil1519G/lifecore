# All‑in‑One Personal Management Platform

## Architecture Document

### Purpose
This document defines the **high‑level architecture** for an all‑in‑one platform that manages:
- Personal finances
- Home devices (IoT)
- Cameras and security
- Automation and notifications

The design emphasizes **security, scalability, and controlled AI usage**.

---

## 1. Design Principles

1. **Security First** – Finance and cameras are sensitive; AI must never act blindly.
2. **Domain Separation** – Finance, devices, and cameras are independent domains.
3. **AI as Assistant, Not Authority** – AI suggests and explains; backend enforces.
4. **Auditability** – Every action must be traceable.
5. **Progressive Enhancement** – Start manual → add AI → add automation.

---

## 2. High‑Level System Overview

```
Client Apps (Web / Mobile)
        ↓
API Gateway
        ↓
Domain Services (Backend)
        ↓
Databases & External Integrations
        ↓
AI Layer (LLM + RAG + MCP)
```

---

## 3. Client Layer

### Responsibilities
- User authentication flow
- Dashboards (finance, devices, cameras)
- Manual device control
- Notifications
- AI chat interface

### Rules
- No business logic
- No secrets stored on client

---

## 4. API Gateway

### Responsibilities
- Authentication (OAuth2 / JWT)
- Authorization & scopes
- Rate limiting
- Request validation
- Routing to services
- Audit logging

The gateway is the **single entry point** to the system.

---

## 5. Backend Services (Domain‑Driven)

### Core Services

```
Identity Service
Finance Service
Home Device Service
Camera Service
Automation Service
Notification Service
```

### Identity Service
- Users
- Roles & permissions
- Device ownership

### Finance Service
- Accounts
- Transactions (read/write)
- Budgets & summaries
- Reports

**Strict rules**:
- Strong consistency
- Encrypted data
- No AI direct writes

### Home Device Service
- Device registry
- Device state
- Vendor adapters
- Command validation

### Camera Service
- Camera registry
- Motion events
- Stream metadata
- Access control

(Video streaming handled outside AI path.)

### Automation Service
- Rule engine
- Schedules
- Event triggers

### Notification Service
- Push notifications
- Email / SMS
- Alerts

---

## 6. Data Layer

### Databases

- **Relational DB** – users, finance, rules
- **Time‑Series DB** – sensor & device telemetry
- **Object Storage** – images, video clips
- **Vector Database** – embeddings for AI retrieval

Each database is used **only for what it is good at**.

---

## 7. AI Layer Overview

The AI layer is **separate from core business logic**.

```
AI Agent
 ├── LLM (reasoning)
 ├── RAG (knowledge retrieval)
 └── MCP (controlled actions)
```

---

## 8. RAG (Retrieval‑Augmented Generation)

### Used For
- Financial explanations
- Device usage analysis
- Policy and rule interpretation
- Historical summaries

### Not Used For
- Live balances
- Raw camera feeds
- Secrets or credentials

RAG retrieves **trusted, read‑only data**.

---

## 9. MCP (Model Context Protocol) Servers

### Purpose
Expose **controlled tools** to the AI agent.

### Example MCP Tools
- Get device status
- Turn device on/off
- Fetch financial summaries
- Arm/disarm cameras
- Create draft automation rules

### Hard Rules
- No unrestricted shell access
- No raw DB writes
- Every action permissioned
- Every action audited

---

## 10. Automation Engine

### Architecture
- Deterministic rule engine (primary)
- AI suggestions (secondary)
- User confirmation for risky actions

Example:
```
IF motion detected
AND time > 11 PM
AND user is away
→ turn on lights
→ notify user
```

AI may **suggest** rules but never auto‑deploy them.

---

## 11. Security Model

### Key Controls
- Zero‑trust service communication
- Encryption at rest and in transit
- Fine‑grained permissions
- Read vs write separation
- Explicit confirmations for money or security actions

### AI Safety Rule
AI can:
- Read
- Explain
- Suggest

AI cannot:
- Move money
- Disable security
- Act without confirmation

---

## 12. Observability & Audit

### Must Track
- API calls
- AI prompts and responses
- MCP tool usage
- Automation triggers
- Financial actions

Logs, metrics, traces, and audit events are mandatory.

---

## 13. Recommended Build Phases

### Phase 1 – Foundation
- Identity
- Finance (manual, read/write)
- Device registry
- Manual controls

### Phase 2 – AI Read‑Only
- RAG over finance & devices
- Insights and explanations

### Phase 3 – Controlled Actions
- MCP‑based device actions
- Confirmation flows

### Phase 4 – Automation
- Rule engine
- AI‑assisted suggestions

---

## 14. Final Mental Model

```
Backend = Authority
AI = Advisor
RAG = Memory
MCP = Hands
Human = Final Decision Maker
```

---

## 15. V1 MVP Scope (Defined)

### Target User
- Personal use (single user)
- Architecture supports future expansion to family / household

### Primary Focus
- **Finance insights (read-only AI)**

### Platform
- Web application only (desktop-first)

### Deployment Model
- Local / self-hosted
- Designed to evolve into hybrid (local + cloud)

### AI Risk Profile
- **Strictly read-only in V1**
- No AI-triggered actions

---

## 16. V1 MVP – In Scope

### Core Functionalities

#### Identity & Security
- Single-user authentication
- Local credentials
- Encrypted storage

#### Finance Domain
- Manual import or ingestion of transactions
- Account summaries
- Monthly and category-wise spending
- Historical views

#### AI (Read-Only)
- Natural language questions over finance data
- Spending pattern explanations
- Anomaly detection ("Why was X high?")
- Budget drift explanations

#### RAG Setup
- Finance transaction summaries
- Budget rules (static)
- Historical monthly rollups

### Explicitly Out of Scope (V1)
- Any financial write actions by AI
- Device control
- Cameras
- Automation
- Mobile apps
- Multi-user support

---

## 17. V1 MVP – Minimal Tech Stack

### Frontend
- Web UI (React or similar)

### Backend
- Spring Boot (modular monolith)
- REST APIs

### Data
- PostgreSQL (finance + users)
- Vector DB (local) for embeddings

### AI
- External LLM (API-based)
- Local RAG pipeline

---

## 18. Success Criteria for V1

V1 is successful if:
- You can ask finance questions in natural language
- Answers are grounded in your real data
- No hallucinated numbers
- System runs locally and reliably

---

## 19. V1 Non-Negotiables

- Audit logging enabled
- Clear separation between AI and core finance logic
- Manual data ownership
- Easy path to extend into devices and family accounts

---

## 20. V1 Data Model & Summary Design (Documented)

This section defines the **exact data model and derived summaries** that power V1 finance features and AI queries.

---

## 20.1 Core Tables (Authoritative Data)

### User
- id
- name
- created_at

(Single user in V1)

---

### Account
- id
- name ("Savings", "Credit Card")
- type
- currency
- created_at

Accounts are **read-only descriptors**.

---

### Category
- id
- name
- parent_id (nullable)
- created_at

Categories are manually curated.

---

### Transaction
- id
- account_id
- date
- description
- amount
- direction (CREDIT / DEBIT)
- category_id
- imported_source
- created_at

Rules:
- Transactions are immutable
- Corrections are stored as new records

---

### Budget
- id
- category_id
- monthly_limit
- created_at

Static budgets only in V1.

---

## 20.2 Derived Summary Tables (AI-Safe)

These tables are the **only source used by AI**.

---

### Monthly Category Summary
- month
- category_id
- total_spent
- transaction_count

---

### Monthly Account Summary
- month
- account_id
- total_inflow
- total_outflow

---

### Monthly Overall Summary
- month
- total_income
- total_expense
- net_balance

---

### Subscription Summary (Heuristic)
- merchant_name
- monthly_average
- transaction_count

---

## 20.3 Summary Generation Jobs

### Schedule
- Triggered on data import
- Nightly recomputation

### Rules
- Deterministic calculations
- No AI involvement
- Idempotent execution

---

## 20.4 RAG Inputs (What Gets Embedded)

### Allowed
- Monthly summaries
- Category trends
- Budget vs actual summaries
- Subscription summaries

### Not Allowed
- Raw transactions
- Account balances
- Personally identifiable descriptions

Embeddings are regenerated only when summaries change.

---

## 20.5 AI Context Contract

Each AI request receives:
- Time range
- Relevant summary rows
- Budget data (if applicable)
- User preferences

AI never queries the database directly.

---

## 20.6 Data Integrity Guarantees

- All numbers shown by AI must match summary tables
- Any mismatch is treated as a bug
- Summary regeneration is fully traceable

---

**Next Step:** Define **V1 APIs and service boundaries** for implementation.



