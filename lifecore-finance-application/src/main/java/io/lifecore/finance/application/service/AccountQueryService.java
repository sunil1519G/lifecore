package io.lifecore.finance.application.service;

import io.lifecore.finance.application.dto.AccountDto;
import io.lifecore.finance.application.dto.PaginatedResponse;
import io.lifecore.finance.application.mapper.AccountMapper;
import io.lifecore.finance.application.repository.AccountRepository;
import io.lifecore.finance.domain.entity.Account;
import io.lifecore.finance.domain.entity.AccountType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountQueryService {

    private final AccountRepository accountRepository;

    public AccountQueryService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    public PaginatedResponse<AccountDto> listAccounts(AccountType type, Boolean active, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Account> accountPage = accountRepository.findByFilters(type, active, pageable);
        
        List<AccountDto> items = accountPage.getContent().stream()
            .map(AccountMapper::toDto)
            .collect(Collectors.toList());
        
        return new PaginatedResponse<>(
            items,
            accountPage.getNumber(),
            accountPage.getSize(),
            accountPage.getTotalElements()
        );
    }

    @Transactional(readOnly = true)
    public AccountDto getAccount(UUID accountId) {
        return accountRepository.findById(accountId)
            .map(AccountMapper::toDto)
            .orElse(null);
    }
}
