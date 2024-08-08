package com.financeflow.financeflow_backend.dto;

import com.financeflow.financeflow_backend.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private Account.AccountType accountType;
    private BigDecimal balance;
    private String currency;
    private List<Long> transactionIds;
}
