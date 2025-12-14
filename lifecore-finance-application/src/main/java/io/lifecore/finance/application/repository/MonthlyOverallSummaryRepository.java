package io.lifecore.finance.application.repository;

import io.lifecore.finance.domain.entity.MonthlyOverallSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MonthlyOverallSummaryRepository extends JpaRepository<MonthlyOverallSummary, LocalDate> {

    @Query("SELECT m FROM MonthlyOverallSummary m WHERE m.month = :month")
    Optional<MonthlyOverallSummary> findByMonth(@Param("month") LocalDate month);
}
