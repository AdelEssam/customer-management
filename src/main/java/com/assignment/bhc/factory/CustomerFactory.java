package com.assignment.bhc.factory;

import com.assignment.bhc.dto.enums.UseCase;
import com.assignment.bhc.service.IAccountService;
import com.assignment.bhc.service.ICustomerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CustomerFactory {
    private ICustomerService customerService;

    public CustomerFactory(@Qualifier("customerService") ICustomerService customerService){
        this.customerService = customerService;
    }

    public ICustomerService getClient(String useCase) {

        if (UseCase.GET_ALL_CUSTOMERS.getName().equalsIgnoreCase(useCase)) {
            return customerService;
        }
        return null;
    }
}
