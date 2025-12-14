# LifeCore

A personal finance insights platform built with Spring Boot, featuring AI-powered analysis and read-only insights.

## Overview

LifeCore is a modular monolith application designed for personal finance management with AI-powered insights. The V1 MVP focuses on:

- Personal finance tracking and analysis
- Transaction ingestion via CSV
- Deterministic financial summaries
- AI-powered explanations (read-only)

## Technology Stack

- **Language**: Java 17
- **Framework**: Spring Boot 3.3.2
- **Build Tool**: Maven
- **Database**: PostgreSQL
- **Migrations**: Flyway
- **Persistence**: Spring Data JPA
- **API Style**: REST

## Project Structure

This is a multi-module Maven project with the following modules:

### Core Modules
- `lifecore-finance-app` - Finance application module
- `lifecore-finance-domain` - Finance domain entities
- `lifecore-finance-application` - Finance application layer
- `lifecore-finance-web` - Finance web layer

### Infrastructure Modules
- `lifecore-api-gateway` - API Gateway
- `lifecore-security` - Security module
- `lifecore-observability` - Observability module
- `lifecore-infra` - Infrastructure utilities
- `lifecore-shared-kernel` - Shared kernel

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL database

### Building the Project

```bash
mvn clean install
```

### Running the Application

```bash
cd lifecore-finance-app
mvn spring-boot:run
```

## Documentation

Detailed documentation is available in the [docs](docs/) directory:

- [V1 High-Level Design (HLD)](docs/v_1_hld_personal_finance_insights_platform.md)
- [V1 Implementation Plan](docs/v_1_implementation_plan_personal_finance_insights.md)
- [V1 Low-Level Design - API Contracts](docs/v_1_lld_api_contracts_endpoints.md)
- [Architecture Overview](docs/all_in_one_personal_management_platform_architecture.md)

## Domain Rules (V1)

- Finance domain (correctness > cleverness)
- V1 is READ-ONLY
- No money mutation logic
- No background jobs
- No async/eventing
- No caching
- No security logic unless explicitly asked

## Coding Standards

- Prefer clarity over abstraction
- No reflection
- No static state
- No Lombok @Builder
- No BigDecimal arithmetic beyond mapping (no calculations)
- Use BigDecimal for money
- Use OffsetDateTime for timestamps
- UUID primary keys
- UTC time only

## Module Boundaries

- **Controllers**: request/response only
- **Services**: orchestration, read-only queries
- **Repositories**: data access only
- No cross-module imports except via API modules

## License

[Add your license here]

## Contributing

[Add contribution guidelines here]

