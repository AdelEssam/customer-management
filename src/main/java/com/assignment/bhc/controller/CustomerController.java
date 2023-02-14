package com.assignment.bhc.controller;

import com.assignment.bhc.dto.CustomerDto;
import com.assignment.bhc.exception.CustomerExceptions;
import com.assignment.bhc.factory.CustomerFactory;
import com.assignment.bhc.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/service/customer")
public class CustomerController {

    private final CustomerFactory customerFactory;

    private final HttpServletRequest request;

    private String useCase;

    public CustomerController(CustomerFactory customerFactory, HttpServletRequest request) {
        this.customerFactory = customerFactory;
        this.request = request;
    }

    @GetMapping
    public ResponseEntity getAllCustomers() {
        useCase = request.getHeader("use-case");
        ICustomerService customerService = customerFactory.getClient(useCase);
        if (customerService == null)
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Use case: " + useCase + " not Implemented..!");
        List<CustomerDto> customers = customerService.getAllCustomers();
        if (customers == null || customers.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Customers Found..!");

        return ResponseEntity.ok().body(customers);
    }

    @GetMapping({"/{customerID}"})
    public ResponseEntity getCustomer(@PathVariable Long customerID) {
        CustomerDto customerDto=null;
        useCase = request.getHeader("use-case");
        ICustomerService accountService = customerFactory.getClient(useCase);
        if (accountService == null)
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Use case: " + useCase + " not Implemented..!");
        try {
            customerDto = accountService.getCustomerByID(customerID);
        } catch (CustomerExceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().body(customerDto);
    }
}
