package com.zopa.ratecalculation.exception;

public class InsufficientLoanAmountException extends RuntimeException {
    public InsufficientLoanAmountException(String message) {
        super(message);
    }
}
