package com.assignment.bhc.service;

import com.assignment.bhc.domain.Account;
import com.assignment.bhc.domain.Customer;
import com.assignment.bhc.domain.Transaction;
import com.assignment.bhc.dto.AccountRequestDto;
import com.assignment.bhc.exception.AccountExceptions;
import com.assignment.bhc.repository.AccountRepository;
import com.assignment.bhc.repository.CustomerRepository;
import com.assignment.bhc.utilities.audit.LoggableAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
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
    @LoggableAction(action = "Open new account", layer = "Service", method = "newAccountRequest")
    public void newAccountRequest(AccountRequestDto accountRequestDto) throws AccountExceptions,Exception {
        try {
            Optional<Customer> customer = customerRepository.findById(accountRequestDto.getCustomerID());
            if(!customer.isPresent())
                throw new AccountExceptions.openNewAccountExceptions("Customer not found while opening account..!");

            Account account = addAccountToCustomer(accountRequestDto, customer.get());
            accountRepository.save(account);
        }catch (AccountExceptions ac) {
            throw new AccountExceptions.openNewAccountExceptions("Customer Not Found..!");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Internal Server Error..!");
        }
    }

    @LoggableAction(action = "Add account to customer", layer = "Service", method = "addAccountToCustomer")
    private Account addAccountToCustomer(AccountRequestDto accountRequestDto, Customer customer) throws Exception, AccountExceptions.openNewAccountExceptions {
        Account account = new Account();
        account.setCustomer(customer);
        account.setBalance(accountRequestDto.getInitialCredit());
        account.setCreationDate(new Date().toString());
        if (accountRequestDto.getInitialCredit() > 0) {
            transferNewTransaction(accountRequestDto, account);
        }
        return account;
    }

    @LoggableAction(action = "Transfer new transaction", layer = "Service", method = "transferNewTransaction")
    private void transferNewTransaction(AccountRequestDto accountRequestDto, Account account) throws Exception, AccountExceptions.openNewAccountExceptions {
        Set<Transaction> transactions = new HashSet<Transaction>();
        try {
            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setAmount(accountRequestDto.getInitialCredit());
            transaction.setTransactionDate(new Date().toString());
            transactions.add(transaction);
            account.setTransaction(transactions);
        }catch (Exception e){
            throw new AccountExceptions.openNewAccountExceptions("Unable to add new transaction: "+e.getMessage());
        }
    }

}
