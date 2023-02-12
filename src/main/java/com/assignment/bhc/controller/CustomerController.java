package com.assignment.bhc.controller;

import com.assignment.bhc.dto.CustomerDto;
import com.assignment.bhc.factory.CustomerFactory;
import com.assignment.bhc.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
