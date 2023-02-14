package com.assignment.bhc.service;

import com.assignment.bhc.domain.Account;
import com.assignment.bhc.domain.Customer;
import com.assignment.bhc.dto.AccountDto;
import com.assignment.bhc.dto.AccountRequestDto;
import com.assignment.bhc.exception.AccountExceptions;
import com.assignment.bhc.repository.AccountRepository;
import com.assignment.bhc.repository.CustomerRepository;
import com.assignment.bhc.utilities.MockReadUtil;
import com.assignment.bhc.utilities.ObjectMapperUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class AccountServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Customer customer;

    private AccountRequestDto accountRequestDto;

    private AccountDto accountDto;

    private Account account;

    @Test
    @DisplayName("Open New Account | Success Scenario")
    void newAccountRequest_success() throws Exception, AccountExceptions {
        accountDto=(AccountDto) MockReadUtil.readObjectFromJsonFile("classpath:openAccount-response.json", AccountDto.class);
        accountRequestDto=(AccountRequestDto) MockReadUtil.readObjectFromJsonFile("classpath:openAccount-request.json", AccountRequestDto.class);
        customer=(Customer)  MockReadUtil.readObjectFromJsonFile("classpath:customer.json",Customer.class);
        account=(Account)  MockReadUtil.readObjectFromJsonFile("classpath:account.json",Account.class);
        Optional<Customer> customerOptional=Optional.of(customer);
        when(customerRepository.findById(any())).thenReturn(customerOptional);
        when(accountRepository.save(any())).thenReturn(account);
        assertNotNull(accountService.newAccountRequest(accountRequestDto));
    }

    @Test
    @DisplayName("Open New Account | Failed Scenario | Customer Not exist")
    void newAccountRequest_failure_customer_notExist() throws Exception, AccountExceptions {
        accountRequestDto=(AccountRequestDto) MockReadUtil.readObjectFromJsonFile("classpath:openAccount-customerNotExist-request.json", AccountRequestDto.class);
        account=(Account)  MockReadUtil.readObjectFromJsonFile("classpath:account.json",Account.class);
        Optional<Customer> customerOptional=Optional.empty();
        when(customerRepository.findById(any())).thenReturn(customerOptional);
        assertThrows(AccountExceptions.openNewAccountExceptions.class, () -> {
            accountService.newAccountRequest(accountRequestDto);
    });
}
}