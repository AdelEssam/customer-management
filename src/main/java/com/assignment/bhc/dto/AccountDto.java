package com.assignment.bhc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto implements Serializable {

    private double balance;

    private String creationDate;

    @JsonIgnore
    private CustomerDto customer;

    private Set<TransactionDto> transaction;
}
