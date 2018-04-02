package com.zopa.ratecalculation.exception;

public class RequestedAmountOutOfAllowedLimits extends RuntimeException {
    static final long serialVersionUID = -7034897190745766639L;

    public RequestedAmountOutOfAllowedLimits(String message) {
        super(message);
    }
}
