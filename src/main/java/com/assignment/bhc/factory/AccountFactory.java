package com.assignment.bhc.factory;

import com.assignment.bhc.dto.enums.UseCase;
import com.assignment.bhc.service.IAccountService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AccountFactory {

    private IAccountService accountService;

    public AccountFactory(@Qualifier("accountService") IAccountService accountService){
        this.accountService=accountService;
    }

    public IAccountService getClient(String useCase) {

        if (UseCase.OPEN_NEW_ACCOUNT.getName().equalsIgnoreCase(useCase)) {
            return accountService;
        }
        return null;
    }
}
