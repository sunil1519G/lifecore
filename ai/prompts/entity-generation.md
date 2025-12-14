Task:
Generate JPA entities for the following domain objects.

Input:
- Entity names and fields will be provided below

Requirements:
- Use @Entity and @Table
- UUID primary key
- Explicit column names
- No cascading unless specified
- No business logic methods
- Equals/hashCode based on ID only
- Proper indexes using @Index
- Use OffsetDateTime for dates
- Monetary fields use BigDecimal

Output:
- Entity classes only
- No repositories
- No DTOs
- No Flyway SQL


