package com.assignment.bhc.controller;

import com.assignment.bhc.dto.AccountRequestDto;
import com.assignment.bhc.factory.AccountFactory;
import com.assignment.bhc.service.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/service/openAccount")
public class AccountManagementController {
    private final AccountFactory accountFactory;

    private final HttpServletRequest request;

    private String useCase;

    public AccountManagementController(AccountFactory accountFactory, HttpServletRequest request) {
        this.accountFactory = accountFactory;
        this.request = request;
    }

    @PostMapping()
    public ResponseEntity openAccount(@Valid @RequestBody AccountRequestDto accountRequestDto){
        useCase = request.getHeader("use-case");
        IAccountService accountService = accountFactory.getClient(useCase);
        if (accountService == null)
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        try {
             accountService.openNewAccount(accountRequestDto);
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
