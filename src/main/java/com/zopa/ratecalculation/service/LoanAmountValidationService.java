package com.zopa.ratecalculation.service;

import com.zopa.ratecalculation.contract.AmountValidationService;
import com.zopa.ratecalculation.exception.RequestedAmountNotMultipleOfHundredException;
import com.zopa.ratecalculation.exception.RequestedAmountOutOfAllowedLimits;
import com.zopa.ratecalculation.util.PropertyUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * Service to validate the Loan amount.
 */
public class LoanAmountValidationService implements AmountValidationService {

    public static final int MIN_AMOUNT = PropertyUtil.getValue("rate.minimum.amount");
    public static final int Max_AMOUNT = PropertyUtil.getValue("rate.maximum.amount");

    /**
     * Check whether Loan amount is multiple of 100
     *
     * @param requestedAmount
     * @return boolean
     */
    private boolean isLoanAmountMultipleOfHundred(final int requestedAmount) {
        if (!(requestedAmount % 100 == 0)) {
            throw new RequestedAmountNotMultipleOfHundredException("Requested amount should be multiple of 100");
        }
        return true;
    }

    /**
     * Check whether loan amount is within allowed limit(1000 - 15000)
     *
     * @param requestedAmount
     * @return boolean
     */
    private boolean isLoanAmountWithinAllowedLimit(final int requestedAmount) {
        if (!(requestedAmount >= MIN_AMOUNT &&
                requestedAmount <= Max_AMOUNT)) {
            throw new RequestedAmountOutOfAllowedLimits("Requested amount should be between " +
                    MIN_AMOUNT + " and " + Max_AMOUNT + " both inclusive");
        }
        return true;
    }

    /**
     * Validate loan amount.
     *
     * @param requestedAmount
     */
    @Override
    public void validateLoanAmount(final String requestedAmount) {
        int requestedAmt = Integer.parseInt(requestedAmount);
        isLoanAmountWithinAllowedLimit(requestedAmt);
        isLoanAmountMultipleOfHundred(requestedAmt);
    }

    /**
     * Check whether loan amount is Number or not.
     *
     * @param reqAmount
     * @return boolean
     */
    @Override
    public boolean isLoanAmountValid(final String reqAmount) {
        return StringUtils.isNotEmpty(reqAmount)
                && !reqAmount.contains(".")
                && NumberUtils.isNumber(reqAmount);
    }
}
