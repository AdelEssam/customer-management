package com.assignment.bhc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto implements Serializable {

    private Long id;

    private double amount;

    private String transactionDate;

    @JsonIgnore
    private AccountDto accountDto;

}
