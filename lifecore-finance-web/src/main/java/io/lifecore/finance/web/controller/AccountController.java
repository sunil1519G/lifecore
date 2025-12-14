package io.lifecore.finance.web.controller;

import io.lifecore.finance.application.dto.AccountDto;
import io.lifecore.finance.application.dto.PaginatedResponse;
import io.lifecore.finance.application.service.AccountQueryService;
import io.lifecore.finance.domain.entity.AccountType;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountQueryService accountQueryService;

    public AccountController(AccountQueryService accountQueryService) {
        this.accountQueryService = accountQueryService;
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<AccountDto>> listAccounts(
        @RequestParam(required = false) AccountType type,
        @RequestParam(required = false) Boolean active,
        @RequestParam @Min(0) int page,
        @RequestParam @Min(1) int size
    ) {
        PaginatedResponse<AccountDto> response = accountQueryService.listAccounts(type, active, page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable UUID accountId) {
        AccountDto account = accountQueryService.getAccount(accountId);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }
}
