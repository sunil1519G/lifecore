# V1 Low-Level Design (LLD)

## API Contracts & Endpoints

This document defines **all REST APIs for V1**, with strict separation between:
- UI-facing APIs
- AI-facing (read-only) APIs

All endpoints are served by the **Finance Backend Service** and exposed via the API Gateway.

---

## 1. Authentication APIs

### POST /api/v1/auth/login
Authenticates the local user.

**Request**
```
{ "username": "string", "password": "string" }
```

**Response**
```
{ "accessToken": "jwt", "expiresIn": 3600 }
```

---

### POST /api/v1/auth/logout
Invalidates the current session.

---

## 2. Transaction APIs (UI-facing)

### POST /api/v1/transactions/import
Upload CSV file and ingest transactions.

**Behavior**
- Validates schema
- Deduplicates entries
- Triggers summary recomputation

---

### GET /api/v1/transactions
Fetch paginated transactions.

**Query Params**
- fromDate
- toDate
- categoryId
- accountId
- search

---

### PATCH /api/v1/transactions/{id}/category
Override transaction category.

**Request**
```
{ "categoryId": "uuid" }
```

---

## 3. Category APIs

### GET /api/v1/categories
List all categories.

---

### POST /api/v1/categories
Create custom category.

---

### PUT /api/v1/categories/{id}
Rename or merge category.

---

## 4. Account APIs

### GET /api/v1/accounts
List all accounts.

---

### GET /api/v1/accounts/{id}/summary
Get monthly summary for account.

---

## 5. Budget APIs

### GET /api/v1/budgets
List all budgets.

---

### POST /api/v1/budgets
Create or update monthly budget.

---

## 6. Summary APIs (Shared)

These endpoints return **precomputed summaries** and are used by both UI and AI.

---

### GET /api/v1/summaries/monthly

**Query Params**
- fromMonth
- toMonth

---

### GET /api/v1/summaries/categories

Returns monthly spend per category.

---

### GET /api/v1/summaries/accounts

Returns monthly inflow/outflow per account.

---

### GET /api/v1/summaries/subscriptions

Returns detected recurring merchants.

---

## 7. AI Context APIs (AI-facing only)

These endpoints are **not exposed to UI directly**.

---

### GET /api/v1/ai/context/monthly

Returns aggregated summaries for AI reasoning.

---

### GET /api/v1/ai/context/trends

Returns historical trends (rolling months).

---

## 8. AI Query API

### POST /api/v1/ai/query

Accepts a natural language question.

**Request**
```
{ "question": "Why was my spending high last month?" }
```

**Behavior**
- Retrieves summaries via AI Context APIs
- Constructs RAG prompt
- Calls LLM
- Returns explanation

---

## 9. Security & Governance

- All endpoints require authentication
- AI endpoints are strictly read-only
- Rate limits enforced at API Gateway
- All AI queries and responses are audited

---

## 10. Versioning Strategy

All APIs are versioned:
```
/api/v1/...
```

---

**Owner:** Finance Backend Service
