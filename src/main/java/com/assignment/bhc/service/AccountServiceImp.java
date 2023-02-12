package com.assignment.bhc.service;

import com.assignment.bhc.domain.Account;
import com.assignment.bhc.domain.Customer;
import com.assignment.bhc.domain.Transaction;
import com.assignment.bhc.dto.AccountDto;
import com.assignment.bhc.repository.AccountRepository;
import com.assignment.bhc.repository.CustomerRepository;
import com.assignment.bhc.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Qualifier("accountService")
@Service
@Component
public class AccountServiceImp implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private TransactionRepository transactionRepository;

    @Override
    public AccountDto openNewAccount(AccountDto accountDto) {
        try {
            Customer customer = customerRepository.getById(accountDto.getCustomerID());
            if (customer == null) {
            }
            Account account = openNewAccount(accountDto, customer);
            accountRepository.save(account);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new AccountDto();
        }

        private Account openNewAccount (AccountDto accountDto, Customer customer){
            Account account = new Account();
            account.setCustomer(customer);
            account.setBalance(accountDto.getInitialCredit());
            account.setCreationDate(new Date().toString());
            if (accountDto.getInitialCredit() > 0) {
                transferNewTransaction(accountDto, account);
            }
            return account;
        }

        private void transferNewTransaction (AccountDto accountDto, Account account){
            Set<Transaction> transactions = new HashSet<Transaction>();
            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setAmount(accountDto.getInitialCredit());
            transaction.setTransactionDate(new Date().toString());
            transactions.add(transaction);
            account.setTransaction(transactions);
        }

    }
