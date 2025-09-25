package com.example.demo; //Declares that this class lives in the com.example.demo package.

// jakarta.persistence.* – JPA annotations so this class can be mapped to a database table.

// jakarta.validation.* – Bean Validation (JSR-380) annotations for input validation.

// java.math.BigDecimal – Precise decimal type for money.

// java.time.LocalDate – Modern date/time API for the expense date.
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity // This annotation tells JPA that this class is a JPA entity.
public class Expense {

    @Id // This annotation tells JPA that this field is a JPA entity identifier.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This annotation tells JPA that this field is a JPA entity identifier.
    private Long id;

    @NotBlank(message = "Description is mandatory") // This annotation tells JPA that this field is not blank.
    @Size(min = 1, max = 255, message = "Description must be between 1 and 255 characters")
    private String description;

    @NotNull(message = "Amount is mandatory")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Date is mandatory")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;

    @NotBlank(message = "Category is mandatory")
    @Size(min = 1, max = 100, message = "Category must be between 1 and 100 characters")
    private String category;

    public Expense() {
    }

    public Expense(String description, BigDecimal amount, LocalDate date, String category) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", category='" + category + '\'' +
                '}';
    }
}
