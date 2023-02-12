package com.assignment.bhc.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto implements Serializable {

    private String name;

    private String  surname;

    private Set<AccountDto> accountsDTO;
}
