Task:
Generate Spring Data JPA repository interfaces.

Requirements:
- Extend JpaRepository
- Read-only queries only
- Prefer derived queries
- Use @Query only if necessary
- Pagination where result size can be large
- No delete or save methods exposed
- No native queries unless asked

Input:
- Entity name
- Required queries

Output:
- Repository interfaces only
