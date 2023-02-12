package com.assignment.bhc.dto;


import lombok.*;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto implements Serializable {

    private Long customerID;

    private double initialCredit;

}
