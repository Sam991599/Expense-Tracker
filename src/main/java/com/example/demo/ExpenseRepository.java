package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Custom queries, thats why we need to call them here
    @Query("SELECT SUM(e.amount) FROM Expense e")
    BigDecimal getTotalExpenses();

    @Query("SELECT MAX(e.date) FROM Expense e")
    LocalDate getLatestExpenseDate();
}
