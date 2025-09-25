//Service layer for expense
//It should be an interface, not a class
//this is just interface level, we will implement business logic inside of expenseserviceimpl.java

package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExpenseService {

    List<Expense> getAllExpenses();

    Optional<Expense> getExpenseById(Long id);

    Expense saveExpense(Expense expense);

    Expense updateExpense(Long id, Expense expenseDetails);

    void deleteExpense(Long id);

    BigDecimal getTotalExpenses();

    LocalDate getLatestExpenseDate();
}