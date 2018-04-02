package com.zopa.ratecalculation.contract;

import com.zopa.ratecalculation.model.Offer;
import com.zopa.ratecalculation.model.Quote;
import java.util.List;

public interface QuoteService {
    /**
     * Provide quotes
     *
     * @param loanAmt
     * @param availableOffers
     * @return
     */
    Quote getQuote(int loanAmt, List<Offer> availableOffers);
}
