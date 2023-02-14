package com.assignment.bhc.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto implements Serializable {

    private  Long id;

    private String name;

    private String  surname;

    private Set<AccountDto> account;
}
