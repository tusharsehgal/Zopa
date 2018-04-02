package com.zopa.ratecalculation;

import com.zopa.ratecalculation.exception.InsufficientLoanAmountException;
import com.zopa.ratecalculation.exception.NoOffersAvailableException;
import com.zopa.ratecalculation.exception.RequestedAmountNotMultipleOfHundredException;
import com.zopa.ratecalculation.exception.RequestedAmountOutOfAllowedLimits;
import com.zopa.ratecalculation.model.Offer;
import com.zopa.ratecalculation.model.Quote;
import com.zopa.ratecalculation.service.CsvFileReaderService;
import com.zopa.ratecalculation.service.LoanAmountValidationService;
import com.zopa.ratecalculation.service.QuoteProviderService;

import java.io.IOException;
import java.util.List;

/**
 * Rate Calculation Application Start up
 */
public class RateCalculationApp {

    static LoanAmountValidationService loanAmountValidationService = new LoanAmountValidationService();
    static QuoteProviderService quoteService = new QuoteProviderService();
    static CsvFileReaderService fileReaderService = new CsvFileReaderService();

    public static void main(String[] args) {

        if (!(args.length == 2 && loanAmountValidationService.isLoanAmountValid(args[1]))) {
            System.out.println("Parameters passed should be in proper format");
            System.exit(1);
        }
        try {

            loanAmountValidationService.validateLoanAmount(args[1]);
            List<Offer> availableOffers = fileReaderService.readAndProcessFile(args[0]);
            Quote finalQuote = quoteService.getQuote(Integer.parseInt(args[1]), availableOffers);
            displayQuotes(finalQuote);
        } catch (RequestedAmountNotMultipleOfHundredException | RequestedAmountOutOfAllowedLimits |
                InsufficientLoanAmountException | NoOffersAvailableException exception) {
            System.out.println(exception.getMessage());
        } catch (IOException e) {
            System.out.println("Failed to read csv file");
        }
    }

    /**
     * Display the final quote
     *
     * @param finalQuote
     */
    private static void displayQuotes(final Quote finalQuote) {
        System.out.println("Requested amount: £" + finalQuote.getRequestedAmount());
        System.out.println("Rate:" + finalQuote.getRate() + "%");
        System.out.println("Monthly repayment £" + finalQuote.getMonthlyRepayment());
        System.out.println("Total repayment: £" + finalQuote.getTotalRepayment());
    }
}

