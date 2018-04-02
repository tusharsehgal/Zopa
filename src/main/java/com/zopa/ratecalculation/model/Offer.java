package com.zopa.ratecalculation.model;

public class Offer implements Comparable<Offer> {

    /**
     * Lender Name
     */
    private String lender;

    /**
     * Rate.
     */
    private double rate;

    /**
     * Amount available with lenders.
     */
    private int availableAmount;

    public Offer(String lender, double rate, int availableAmount) {
        this.lender = lender;
        this.rate = rate;
        this.availableAmount = availableAmount;
    }

    /**
     * Get the Rate.
     *
     * @return rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * Get Available Amount.
     *
     * @return Available Amount
     */
    public int getAvailableAmount() {
        return availableAmount;
    }

    @Override
    public int compareTo(Offer offer) {
        if (this.getRate() == offer.getRate())
            return 0;
        else {
            return this.getRate() > offer.getRate() ? 1 : -1;
        }
    }
}
