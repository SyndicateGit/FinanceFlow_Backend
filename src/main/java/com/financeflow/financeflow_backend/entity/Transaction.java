package com.financeflow.financeflow_backend.entity;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // Specifies class as JPA entity
@Table(name = "transactions") // Specifies the name of the table in the database autogenerated by Hibernate
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false)
    private BigDecimal amount;
    private String currency;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(name = "is_recurring")
    private boolean isRecurring;

    @Enumerated(EnumType.STRING)
    @Column(name = "recurring_type")
    private RecurringType recurringType;

    @Column(nullable = false)
    private String note;
    @Column(nullable = false)
    private String category;
    private String description;

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void applyTransaction(Account account){
        if(this.type.equals(TransactionType.TRANSFER)){
            throw new IllegalArgumentException(
                    "Use applyTransaction(Account fromAccount, Account toAccount) for transfer transactions"
            );
        }
        if(this.type.equals(TransactionType.INCOME)){
            account.deposit(this.amount);
        } else {
            account.withdraw(this.amount);
        }
        account.addTransaction(this);
    }

    public void applyTransaction(Account fromAccount, Account toAccount){
        this.setNote("Transfer from " + fromAccount.getAccountType() + " to " + toAccount.getAccountType());
        this.setCategory("Transfer");

        fromAccount.withdraw(this.amount);
        fromAccount.addTransaction(this);

        toAccount.deposit(this.amount);
        toAccount.addTransaction(this);
    }
}
