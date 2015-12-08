package org.orangekit.finance;

/**
 *  ���ʱ�ÿһ��ʱ���ڣ�����1-6�£�1�꣬5�꣩
 * @author Kyle
 *
 */
public class Rate {
    private String period; //��������
    private String commercialRate; //��ҵ����
    private String providentRate; //����������

    /**
     * ���ʱ�
     * @param period ��������
     * @param commercialRate ��ҵ����
     * @param providentRate ����������
     */
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