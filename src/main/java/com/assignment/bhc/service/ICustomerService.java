package com.assignment.bhc.service;

import com.assignment.bhc.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {
    List<CustomerDto> getAllCustomers() ;
    CustomerDto getCustomerByID();
}
