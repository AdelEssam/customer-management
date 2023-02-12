package com.assignment.bhc.dto;


import lombok.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequestDto implements Serializable {

    @NotNull(message = "Customer ID is missing")
    private Long customerID;

    private double initialCredit=0;

}
