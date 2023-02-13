package com.assignment.bhc.service;

import com.assignment.bhc.dto.CustomerDto;
import com.assignment.bhc.exception.CustomerExceptions;

import java.util.List;

public interface ICustomerService {
    List<CustomerDto> getAllCustomers() ;
    CustomerDto getCustomerByID(Long customerID) throws CustomerExceptions;
}
