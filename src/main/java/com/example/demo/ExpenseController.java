package com.example.demo;

// import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    // private final ExpenseRepository expenseRepository;
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id).orElse(null);
    }

    @GetMapping("/total")
    public BigDecimal getTotalExpenses() {
        return expenseService.getTotalExpenses();
    }

    @GetMapping("/date")
    public LocalDate getLatestExpenseDate() {
        return expenseService.getLatestExpenseDate();
    }

    @PostMapping
    public Expense createExpense(@Valid @RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @Valid @RequestBody Expense expenseDetails) {
        Expense expense = expenseService.getExpenseById(id).orElse(null);
        if (expense != null) {
            expense.setDescription(expenseDetails.getDescription());
            expense.setAmount(expenseDetails.getAmount());
            expense.setDate(expenseDetails.getDate());
            expense.setCategory(expenseDetails.getCategory());
            return expenseService.saveExpense(expense);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }
}
