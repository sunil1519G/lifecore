# V1 High-Level Design (HLD)

## Personal Finance Insights Platform (Read-Only AI)

---

## 1. Purpose of This Document

This HLD defines the **system-level design** for **V1 MVP**, focused exclusively on:

- Personal finance insights
- Web-based access
- Local / self-hosted deployment
- Read-only AI (no actions)

This document intentionally avoids future domains (devices, cameras, automation).

---

## 2. V1 Scope Summary

### In Scope
- Single-user personal finance management
- Transaction ingestion via CSV
- Deterministic financial summaries
- AI-powered explanations (read-only)

### Out of Scope
- Bank integrations
- Device management
- Cameras
- Automation
- Multi-user / family
- Mobile apps
- AI-triggered actions

---

## 3. High-Level Architecture

```
Web UI
  ↓
API Gateway
  ↓
Finance Backend (Modular Monolith)
  ↓
PostgreSQL + Vector DB
  ↓
AI Layer (LLM + RAG)
```

---

## 4. Logical Components

### 4.1 Web Application

**Responsibilities**:
- Authentication
- CSV upload
- Finance dashboards
- AI chat interface

**Characteristics**:
- Stateless
- Desktop-first
- No business logic

---

### 4.2 API Gateway

**Responsibilities**:
- Authentication & authorization
- Request validation
- Rate limiting
- Audit logging

Acts as the **single entry point**.

---

### 4.3 Finance Backend Service

A **modular monolith** for V1.

#### Internal Modules
- Identity Module
- Transaction Module
- Category Module
- Budget Module
- Summary Module
- AI Context Module

**Key Rule**: AI never touches raw tables.

---

## 5. Data Architecture

### 5.1 Authoritative Data (PostgreSQL)

- Users
- Accounts
- Transactions
- Categories
- Budgets

These tables are **write-protected from AI**.

---

### 5.2 Derived Data (PostgreSQL)

- Monthly category summaries
- Monthly account summaries
- Monthly overall summaries
- Subscription summaries

Used for dashboards and AI.

---

### 5.3 Vector Database

**Purpose**:
- Store embeddings of summaries
- Enable semantic retrieval for AI

**Content Rules**:
- Aggregated data only
- No raw transactions
- No PII-heavy descriptions

---

## 6. AI & RAG Design

### 6.1 AI Role

AI acts as:
- Explainer
- Pattern identifier
- Insight narrator

AI does NOT:
- Modify data
- Predict future values
- Provide financial advice

---

### 6.2 RAG Flow

```
User Question
  ↓
Retrieve Relevant Summaries
  ↓
Construct AI Context
  ↓
LLM Generates Explanation
```

---

### 6.3 AI Guardrails

- Answers only from provided context
- Quotes exact numbers
- Responds "I don’t know" if data missing
- No assumptions or recommendations

---

## 7. Security Design

### Controls
- Local authentication
- Encrypted storage
- Read/write separation
- AI isolated behind service layer

### Trust Boundary

```
User → UI → API Gateway → Backend → AI (read-only)
```

---

## 8. Observability

### Logged Events
- CSV uploads
- Summary generation
- AI queries and responses
- Data access patterns

Logs are local and retained by user.

---

## 9. Deployment Model

### V1 Deployment
- Local machine or single server
- Docker-based services
- No external dependencies except LLM API

---

## 10. Non-Functional Requirements

- Deterministic calculations
- No hallucinated numbers
- Fast query response (<2s for AI)
- Easy data reset

---

## 11. V1 Success Criteria

V1 is successful if:
- Finance dashboards are accurate
- AI explanations match backend summaries
- System is trustworthy for personal use

---

## 12. V1 Exit Criteria

V1 is complete when:
- All defined AI questions are supported
- No AI answer contradicts stored summaries
- Architecture can extend to family + devices

---

## API Design Reference

Detailed V1 API contracts and endpoints are defined in:

**V1 LLD – API Contracts & Endpoints**

This HLD intentionally omits low-level API details to preserve architectural clarity.


