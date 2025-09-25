package com.example.demo;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    // Constructor injection
    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    @Override
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpense(Long id, Expense expenseDetails) {
        // This logic is slightly more complex than just delegating directly.
        // We need to find the existing expense, update its fields, and then save.
        // For now, let's keep it simple by replicating what was in the controller:
        Expense existingExpense = expenseRepository.findById(id).orElse(null);
        if (existingExpense != null) {
            existingExpense.setDescription(expenseDetails.getDescription());
            existingExpense.setAmount(expenseDetails.getAmount());
            existingExpense.setDate(expenseDetails.getDate());
            existingExpense.setCategory(expenseDetails.getCategory());
            return expenseRepository.save(existingExpense);
        }
        return null;
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalExpenses() {
        return expenseRepository.getTotalExpenses();
    }

    @Override
    public LocalDate getLatestExpenseDate() {
        return expenseRepository.getLatestExpenseDate();
    }
}
