package com.zopa.ratecalculation.model;

public class Quote {

    /**
     * Requested Amount.
     */
    private int requestedAmount;

    /**
     * rate.
     */
    private double rate;

    /**
     * Monthly repayment.
     */
    private double monthlyRepayment;

    /**
     * Total Repayment.
     */
    private double totalRepayment;

    /**
     * Constructor
     *
     * @param requestedAmount
     */
    public Quote(int requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    /**
     * Get Requested Amount.
     *
     * @return requested amount
     */
    public int getRequestedAmount() {
        return requestedAmount;
    }

    /**
     * Set Requested Amount
     *
     * @param requestedAmount
     */
    public void setRequestedAmount(final int requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    /**
     * Get Rate.
     *
     * @return rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * Set Rate
     *
     * @param rate
     */
    public void setRate(final double rate) {
        this.rate = rate;
    }

    /**
     * Get Monthly Repayment
     *
     * @return Monthly Repayment
     */
    public double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    /**
     * Set Monthly Repayment.
     *
     * @param monthlyRepayment
     */
    public void setMonthlyRepayment(final double monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
    }

    /**
     * Get Total Repayment.
     *
     * @return total repayment
     */
    public double getTotalRepayment() {
        return totalRepayment;
    }

    /**
     * Set Total Repayment
     *
     * @param totalRepayment
     */
    public void setTotalRepayment(final double totalRepayment) {
        this.totalRepayment = totalRepayment;
    }
}
