Task:
Generate a REST controller.

Requirements:
- @RestController
- Versioned path: /api/v1/...
- Read-only endpoints only
- Proper HTTP status codes
- Validation annotations on request params
- No business logic
- Delegate to service layer
- Use DTOs only
- No security annotations

Input:
- Endpoint paths
- Request parameters
- Response DTOs

Output:
- Controller class only
