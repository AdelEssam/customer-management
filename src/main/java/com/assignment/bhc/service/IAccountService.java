package com.assignment.bhc.service;

import com.assignment.bhc.dto.AccountRequestDto;
import com.assignment.bhc.exception.AccountExceptions;

public interface IAccountService {
    void openNewAccount(AccountRequestDto accountRequestDto) throws AccountExceptions, Exception;
}
