package com.assignment.bhc.service;

import com.assignment.bhc.domain.Customer;
import com.assignment.bhc.dto.CustomerDto;
import com.assignment.bhc.repository.CustomerRepository;
import com.assignment.bhc.utilities.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements ICustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers=customerRepository.findAll();
        return ObjectMapperUtils.mapAll(customers, CustomerDto.class); }

    @Override
    public CustomerDto getCustomerByID() {
        return null;
    }
}
