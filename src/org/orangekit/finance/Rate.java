package org.orangekit.finance;

/**
 *  ���ʱ�ÿһ��ʱ���ڣ�����1-6�£�1�꣬5�꣩.
 *
 * @author Kyle
 */
public class Rate {
    
    /** The period. */
    private String period; //��������
    
    /** The commercial rate. */
    private String commercialRate; //��ҵ����
    
    /** The provident rate. */
    private String providentRate; //����������

    /**
     * ���ʱ�.
     *
     * @param period ��������
     * @param commercialRate ��ҵ����
     * @param providentRate ����������
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