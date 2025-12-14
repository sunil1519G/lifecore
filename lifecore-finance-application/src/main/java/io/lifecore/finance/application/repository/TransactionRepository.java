package io.lifecore.finance.application.repository;

import io.lifecore.finance.domain.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId AND " +
           "(:from IS NULL OR t.txnDate >= :from) AND " +
           "(:to IS NULL OR t.txnDate <= :to) AND " +
           "(:category IS NULL OR t.category.code = :category OR t.category.name = :category)")
    Page<Transaction> findByAccountIdAndFilters(
        @Param("accountId") UUID accountId,
        @Param("from") LocalDate from,
        @Param("to") LocalDate to,
        @Param("category") String category,
        Pageable pageable
    );

    Optional<Transaction> findById(UUID id);
}
