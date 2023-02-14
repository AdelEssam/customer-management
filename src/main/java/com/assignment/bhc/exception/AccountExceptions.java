package com.assignment.bhc.exception;

public class AccountExceptions extends Throwable {
    public AccountExceptions(String message) {
        super(message);
    }

    public static class openNewAccountExceptions extends AccountExceptions {
        public openNewAccountExceptions(String message) {
            super(message);
        }
    }
    }
