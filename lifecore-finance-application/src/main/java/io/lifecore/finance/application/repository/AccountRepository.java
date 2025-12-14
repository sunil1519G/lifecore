package io.lifecore.finance.application.repository;

import io.lifecore.finance.domain.entity.Account;
import io.lifecore.finance.domain.entity.AccountType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query("SELECT a FROM Account a WHERE " +
           "(:type IS NULL OR a.type = :type) AND " +
           "(:active IS NULL OR a.active = :active)")
    Page<Account> findByFilters(
        @Param("type") AccountType type,
        @Param("active") Boolean active,
        Pageable pageable
    );

    Optional<Account> findById(UUID id);
}
