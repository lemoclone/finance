package org.orangekit.finance;

/**
 *  利率表（每一段时期内，比如1-6月，1年，5年）.
 *
 * @author Kyle
 */
public class Rate {
    
    /** The period. */
    private String period; //贷款期限
    
    /** The commercial rate. */
    private String commercialRate; //商业利率
    
    /** The provident rate. */
    private String providentRate; //公积金利率

    /**
     * 利率表.
     *
     * @param period 贷款期限
     * @param commercialRate 商业利率
     * @param providentRate 公积金利率
     */
    public Rate(String period, String commercialRate, String providentRate) {
        this.period = period;
        this.commercialRate = commercialRate;
        this.providentRate = providentRate;
    }

    /**
     * Gets the period.
     *
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Gets the commercial rate.
     *
     * @return the commercial rate
     */
    public String getCommercialRate() {
        return commercialRate;
    }

    /**
     * Gets the provident rate.
     *
     * @return the provident rate
     */
    public String getProvidentRate() {
        return providentRate;
    }
}