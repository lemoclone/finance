package com.orangekit.finance;

/**
 * Created by hudafei on 12/6/15.
 */
public class Rate {
    private String period;
    private String commercialRate;
    private String providentRate;

    public Rate(String period, String commercialRate, String providentRate) {
        this.period = period;
        this.commercialRate = commercialRate;
        this.providentRate = providentRate;
    }

    public String getPeriod() {
        return period;
    }

    public String getCommercialRate() {
        return commercialRate;
    }

    public String getProvidentRate() {
        return providentRate;
    }
}