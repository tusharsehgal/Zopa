package com.zopa.ratecalculation.contract;

public interface AmountValidationService {
    /**
     * Validate amount for a loan
     *
     * @param requestedAmount
     */
    void validateLoanAmount(String requestedAmount);

    /**
     * Validate amount entered
     *
     * @param reqAmount
     * @return
     */
    boolean isLoanAmountValid(String reqAmount);
}
