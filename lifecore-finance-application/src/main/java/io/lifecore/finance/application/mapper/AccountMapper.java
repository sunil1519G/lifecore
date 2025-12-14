package io.lifecore.finance.application.mapper;

import io.lifecore.finance.application.dto.AccountDto;
import io.lifecore.finance.domain.entity.Account;

public class AccountMapper {

    private AccountMapper() {
        // Utility class
    }

    public static AccountDto toDto(Account account) {
        if (account == null) {
            return null;
        }
        return new AccountDto(
            account.getId(),
            account.getName(),
            account.getType() != null ? account.getType().name() : null,
            account.getCurrency(),
            account.getInstitution(),
            account.getActive(),
            account.getCreatedAt()
        );
    }
}
