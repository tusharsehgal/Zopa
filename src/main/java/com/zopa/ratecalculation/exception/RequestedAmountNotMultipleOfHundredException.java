package com.zopa.ratecalculation.exception;

public class RequestedAmountNotMultipleOfHundredException extends RuntimeException {
    static final long serialVersionUID = -7034897190745766937L;

    public RequestedAmountNotMultipleOfHundredException(String message) {
        super(message);
    }
}
