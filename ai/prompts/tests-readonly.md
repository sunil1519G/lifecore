Task:
Generate tests for read-only functionality.

Requirements:
- JUnit 5
- SpringBootTest or @DataJpaTest as appropriate
- Use Testcontainers PostgreSQL if DB involved
- No mocks for repositories
- Clear assertions
- No brittle time-based assertions

Input:
- Class under test
- Scenarios to cover

Output:
- Test classes only
