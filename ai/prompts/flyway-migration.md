Task:
Generate a Flyway SQL migration for PostgreSQL.

Requirements:
- Versioned as V__description.sql
- Use snake_case table and column names
- UUID as primary key
- Add foreign keys
- Add indexes for:
  - all foreign keys
  - frequently queried date columns
- Use NOT NULL where appropriate
- No triggers
- No functions
- No data inserts

Input:
<<PASTE ENTITY FIELDS OR TABLE STRUCTURE HERE>>

Output:
- SQL only
- PostgreSQL compatible
