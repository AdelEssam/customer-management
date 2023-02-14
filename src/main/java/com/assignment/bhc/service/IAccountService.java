package com.assignment.bhc.service;

import com.assignment.bhc.dto.AccountDto;
import com.assignment.bhc.dto.AccountRequestDto;
import com.assignment.bhc.exception.AccountExceptions;

public interface IAccountService {
    AccountDto newAccountRequest(AccountRequestDto accountRequestDto) throws AccountExceptions, Exception;
}
