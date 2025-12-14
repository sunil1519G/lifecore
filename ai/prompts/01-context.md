Project Context:
Project name: lifecore
Architecture: Multi-module Spring Boot
Language: Java 17
Build: Maven
DB: PostgreSQL
Migrations: Flyway
API style: REST
Persistence: Spring Data JPA
IDs: UUID
Time: UTC only

Domain Rules:
- Finance domain (correctness > cleverness)
- V1 is READ-ONLY
- No money mutation logic
- No background jobs
- No async/eventing
- No caching
- No security logic unless explicitly asked

Coding Rules:
- Prefer clarity over abstraction
- No reflection
- No static state
- No Lombok @Builder
- No BigDecimal arithmetic beyond mapping (no calculations)
- Use BigDecimal for money
- Use OffsetDateTime for timestamps

Module Boundaries:
- Controllers: request/response only
- Services: orchestration, read-only queries
- Repositories: data access only
- No cross-module imports except via API modules

Do NOT:
- Introduce new modules
- Add business rules not specified
- Change existing public APIs
- Add write operations
