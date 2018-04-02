package com.zopa.ratecalculation.exception;

public class NoOffersAvailableException extends RuntimeException {
    static final long serialVersionUID = -7034897190745766639L;

    public NoOffersAvailableException(String message) {
        super(message);
    }
}
