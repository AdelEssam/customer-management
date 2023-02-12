package com.assignment.bhc.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {

    private String transactionType;

    private double amount;

    private AccountDto accountDto;

}
