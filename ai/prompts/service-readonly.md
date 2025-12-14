Task:
Generate a read-only service class.

Requirements:
- @Service
- Constructor injection
- No @Transactional unless readOnly=true
- Orchestrate repository calls only
- No calculations
- No mutation
- No caching
- Clear method names
- Return DTOs, not entities

Input:
- Service name
- Repository methods to use
- DTOs involved

Output:
- Service class only
