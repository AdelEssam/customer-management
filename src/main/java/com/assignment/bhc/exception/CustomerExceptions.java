package com.assignment.bhc.exception;

public class CustomerExceptions extends Throwable{
    public CustomerExceptions(String message) {
        super(message);
    }

    public static class NotFoundCustomerExceptions extends CustomerExceptions {
        public NotFoundCustomerExceptions(String message) {
            super(message);
        }
    }
}
