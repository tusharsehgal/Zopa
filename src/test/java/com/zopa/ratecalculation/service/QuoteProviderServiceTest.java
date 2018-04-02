package com.zopa.ratecalculation.service;

import com.zopa.ratecalculation.exception.InsufficientLoanAmountException;
import com.zopa.ratecalculation.model.Offer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuoteProviderServiceTest {

    QuoteProviderService quoteService = new QuoteProviderService();

    @Test
    public void assertAverageRateIfTwoOffersAreApplicable() {
        //Given
        List<Offer> offerList = new ArrayList<Offer>();
        offerList.add(new Offer("tushar", 1, 400));
        offerList.add(new Offer("sehgal", 2, 600));
        int loanAmt = 1000;

        //When
        double rate = quoteService.getQuote(loanAmt, offerList).getRate();

        //Then
        assertEquals(1.5, rate, 0);
    }

    @Test
    public void assertAverageRateIfMultipleOffersAreApplicable() {
        //Given
        List<Offer> offerList = new ArrayList<Offer>();
        offerList.add(new Offer("tushar", 1, 400));
        offerList.add(new Offer("sehgal", 2, 300));
        offerList.add(new Offer("sehgal", 2, 600));
        int loanAmt = 1000;

        //When
        double rate = quoteService.getQuote(loanAmt, offerList).getRate();

        //Then
        assertEquals(1.7, rate, 0);

    }

    @Test
    public void assertRateIfOneOfferIsApplicable() {
        //Given
        List<Offer> offerList = new ArrayList<Offer>();
        offerList.add(new Offer("tushar", 1, 1000));
        int loanAmt = 200;

        //When
        double rate = quoteService.getQuote(loanAmt, offerList).getRate();

        //Then
        assertEquals(1.0, rate, 0);
    }

    @Test
    public void assertTotalPaymentForQuote() {
        //Given
        List<Offer> offerList = new ArrayList<Offer>();
        offerList.add(new Offer("tushar", 1, 1000));
        int loanAmt = 200;

        //When
        double totalPymnt = quoteService.getQuote(loanAmt, offerList).getTotalRepayment();

        //Then
        assertEquals(206.08, totalPymnt, 0.05);

    }

    @Test
    public void assertMonthlyPaymentForQuote() {
        //Given
        List<Offer> offerList = new ArrayList<Offer>();
        offerList.add(new Offer("tushar", 1, 1000));
        int loanAmt = 200;

        //When
        double totalPymnt = quoteService.getQuote(loanAmt, offerList).getMonthlyRepayment();

        //Then
        assertEquals(5.72, totalPymnt, 0.05);

    }

    @Test(expected = InsufficientLoanAmountException.class)
    public void testInsufficientLoanAmount() {
        //Given
        List<Offer> offerList = new ArrayList<Offer>();
        offerList.add(new Offer("tushar", 4, 400));
        int loanAmt = 1000;
        //When
        quoteService.getQuote(loanAmt, offerList);
        // Then
        //Throws InsufficientLoanAmountException
    }

}