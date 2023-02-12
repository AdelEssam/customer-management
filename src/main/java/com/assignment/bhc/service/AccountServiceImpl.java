package com.assignment.bhc.service;

import com.assignment.bhc.domain.Account;
import com.assignment.bhc.domain.Customer;
import com.assignment.bhc.domain.Transaction;
import com.assignment.bhc.dto.AccountRequestDto;
import com.assignment.bhc.exception.AccountExceptions;
import com.assignment.bhc.repository.AccountRepository;
import com.assignment.bhc.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Qualifier("accountService")
@Service
@Component
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void openNewAccount(AccountRequestDto accountRequestDto) throws AccountExceptions,Exception {
        try {
            Optional<Customer> customer = customerRepository.findById(accountRequestDto.getCustomerID());
            if(!customer.isPresent())
                throw new AccountExceptions.openNewAccountExceptions("Customer not found..!");

            Account account = openNewAccount(accountRequestDto, customer.get());
            accountRepository.save(account);
        }catch (AccountExceptions ac) {
            throw new AccountExceptions.openNewAccountExceptions("Customer Not Found..!");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Internal Server Error..!");
        }
    }

    private Account openNewAccount(AccountRequestDto accountRequestDto, Customer customer) throws Exception{
        Account account = new Account();
        account.setCustomer(customer);
        account.setBalance(accountRequestDto.getInitialCredit());
        account.setCreationDate(new Date().toString());
        if (accountRequestDto.getInitialCredit() > 0) {
            transferNewTransaction(accountRequestDto, account);
        }
        return account;
    }

    private void transferNewTransaction(AccountRequestDto accountRequestDto, Account account) throws Exception{
        Set<Transaction> transactions = new HashSet<Transaction>();
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(accountRequestDto.getInitialCredit());
        transaction.setTransactionDate(new Date().toString());
        transactions.add(transaction);
        account.setTransaction(transactions);
    }

}
