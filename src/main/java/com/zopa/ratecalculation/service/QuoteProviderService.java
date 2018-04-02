package com.zopa.ratecalculation.service;

import com.zopa.ratecalculation.contract.QuoteService;
import com.zopa.ratecalculation.exception.InsufficientLoanAmountException;
import com.zopa.ratecalculation.model.Offer;
import com.zopa.ratecalculation.model.Quote;
import com.zopa.ratecalculation.util.PropertyUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to provide the quote.
 */
public class QuoteProviderService implements QuoteService {
    public static final String DOUBLE_DIGIT_PATTERN = ".##";
    public static final String SINGLE_DIGIT_PATTERN = ".#";
    public static final int DURATION = PropertyUtil.getValue("rate.time");

    /**
     * Get the final quote.
     *
     * @param loanAmt
     * @param availableOffers list of availableOffers
     * @return quote
     */
    @Override
    public Quote getQuote(final int loanAmt, final List<Offer> availableOffers) {
        List<Offer> listOfOffers;
        Quote quote = new Quote(loanAmt);
        listOfOffers = listOfOffersApplied(availableOffers, quote);
        quote.setRate(getAverageRate(listOfOffers));
        double totalPayment = calculateTotalPaymentWithCompoundInterest(quote);
        quote.setTotalRepayment(totalPayment);
        quote.setMonthlyRepayment(getFormattedValue(totalPayment / (12 * DURATION)));
        return quote;
    }

    /**
     * Get the offers valid for the requested loan amount.
     *
     * @param offers list of available offers
     * @param quote  quote
     * @return list of valid offers.
     */
    private List<Offer> listOfOffersApplied(final List<Offer> offers, final Quote quote) {
        int amount = quote.getRequestedAmount();
        List<Offer> offersApplied = new ArrayList<>();
        if (offers != null) {
            if (amount > offers.stream().mapToInt(offer -> offer.getAvailableAmount()).sum()) {
                throw new InsufficientLoanAmountException("No quote can be provided at this time due to insufficient amount");
            }
            for (Offer offer : offers) {
                if (amount < offer.getAvailableAmount()) {
                    offersApplied.add(offer);
                    break;
                } else {
                    amount = amount - offer.getAvailableAmount();
                    offersApplied.add(offer);
                }
            }
        }
        return offersApplied;
    }

    /**
     * Calculate total repayment with interest.
     *
     * @param quote quote
     * @return total repayment with interest
     */
    private double calculateTotalPaymentWithCompoundInterest(final Quote quote) {
        double totalPayment = quote.getRequestedAmount() * Math.pow(1 + quote.getRate() / 1200.0,
                12 * DURATION);
        return getFormattedValue(totalPayment);
    }

    /**
     * Get Average rate.
     *
     * @param listOfOffersApplied
     * @return Average rate
     */
    private double getAverageRate(final List<Offer> listOfOffersApplied) {
        double rate = listOfOffersApplied.stream()
                .mapToDouble(offer -> offer.getRate())
                .sum() / listOfOffersApplied.size();
        return Double.parseDouble(new DecimalFormat(SINGLE_DIGIT_PATTERN).format(rate));
    }

    /**
     * Format the input value to double with decimal up to 2 digits.
     *
     * @param value
     * @return
     */
    private double getFormattedValue(final double value) {
        return Double.parseDouble(new DecimalFormat(DOUBLE_DIGIT_PATTERN).format(value));
    }
}
