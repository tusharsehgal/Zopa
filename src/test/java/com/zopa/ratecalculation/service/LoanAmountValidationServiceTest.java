package com.zopa.ratecalculation.service;

import com.zopa.ratecalculation.exception.RequestedAmountNotMultipleOfHundredException;
import com.zopa.ratecalculation.exception.RequestedAmountOutOfAllowedLimits;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoanAmountValidationServiceTest {

    LoanAmountValidationService loanAmountValidationService = new LoanAmountValidationService();

    @Test(expected = RequestedAmountNotMultipleOfHundredException.class)
    public void returnTrueIfRequestedAmountIsMultipleOf100() {
        //Given
        String requestedAmount = "1001";

        //When
        loanAmountValidationService.validateLoanAmount(requestedAmount);

        //Then
        //Throws RequestedAmountNotMultipleOfHundredException
    }

    @Test(expected = RequestedAmountOutOfAllowedLimits.class)
    public void throwExceptionWhenRequestedAmountIsNotWithinAllowedLimit() {

        //Given
        String requestedAmount = "850";

        //When
        loanAmountValidationService.validateLoanAmount(requestedAmount);

        //Then
        //Should throw RequestedAmountOutOfAllowedLimits
    }

    @Test
    public void returnFalseIfLoanAmtIsADecimalNumber() {

        //Given
        String requestedAmount = "1000.1";

        //When
        boolean isNumberValid = loanAmountValidationService.isLoanAmountValid(requestedAmount);

        //Then
        assertFalse(isNumberValid);
    }

    @Test
    public void returnTrueIfLoanAmtIsANonDecimalNumber() {

        //Given
        String requestedAmount = "1000";

        //When
        boolean isNumberValid = loanAmountValidationService.isLoanAmountValid(requestedAmount);

        //Then
        assertTrue(isNumberValid);
    }

    @Test
    public void returnFalseIfLoanAmtIsNotValid() {

        //Given
        String requestedAmount = "100A";

        //When
        boolean isNumberValid = loanAmountValidationService.isLoanAmountValid(requestedAmount);

        //Then
        assertFalse(isNumberValid);
    }

    @Test
    public void returnFalseIfLoanAmtIsBlank() {

        //Given
        String requestedAmount = "";

        //When
        boolean isNumberValid = loanAmountValidationService.isLoanAmountValid(requestedAmount);

        //Then
        assertFalse(isNumberValid);
    }

    @Test
    public void returnTrueIfLoanAmtIsValid() {

        //Given
        String requestedAmount = "";

        //When
        boolean isNumberValid = loanAmountValidationService.isLoanAmountValid(requestedAmount);

        //Then
        assertFalse(isNumberValid);
    }
}