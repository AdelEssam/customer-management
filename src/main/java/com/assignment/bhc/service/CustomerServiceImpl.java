package com.assignment.bhc.service;

import com.assignment.bhc.domain.Customer;
import com.assignment.bhc.dto.CustomerDto;
import com.assignment.bhc.exception.AccountExceptions;
import com.assignment.bhc.exception.CustomerExceptions;
import com.assignment.bhc.repository.CustomerRepository;
import com.assignment.bhc.utilities.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("customerService")
public class CustomerServiceImpl implements ICustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDto> getAllCustomers() {
        return ObjectMapperUtils.mapAll(customerRepository.findAll(), CustomerDto.class); }

    @Override
    public CustomerDto getCustomerByID(Long customerID) throws CustomerExceptions {
        Optional<Customer> customer = customerRepository.findById(customerID);
        if(!customer.isPresent())
            throw new CustomerExceptions.NotFoundCustomerExceptions("Customer not found..!");
        return ObjectMapperUtils.map(customer.get(),CustomerDto.class);
    }


}
