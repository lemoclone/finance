package org.orangekit.finance;

import java.util.ArrayList;
import java.util.List;

/**
 * ƽ���.
 *
 * @author Kyle
 */
public class Balance {
	
	/** The credit provident amount. */
	private double creditProvidentAmount; //�����������
	
	/** The credit commercial amount. */
	private double creditCommercialAmount;//��ҵ������
	
	/** The credit year. */
	private int creditYear;				//��������
	
	/** The provident rates. */
	private float[] providentRates = {2.75f,3.25f};//���������ʱ�
	
	/** The commercial rates. */
	private float[] commercialRates = {4.35f,4.75f,4.90f};//��ҵ���ʱ�
	
	/** The provident rate. */
	private float providentRate; //����������
	
	/** The commercial rate. */
	private float commercialRate;//��ҵ����
	
	/** The provident rate discount. */
	private float providentRateDiscount;//�����������ۿ�
	
	/** The commercial rate discount. */
	private float commercialRateDiscount;//��ҵ�����ۿ�

	/**
	 * ����.
	 *
	 * @param creditProvidentAmount ����������
	 * @param creditCommercialAmount �̴�����
	 * @param creditYear ����
	 * @param pRates ����������
	 * @param cRates �̴�����
	 * @param providentRateDiscount �������ۿ�
	 * @param commercialRateDiscount �̴��ۿ�
	 */
	public Balance(double creditProvidentAmount, double creditCommercialAmount, int creditYear,
			float[] pRates, float[] cRates,float providentRateDiscount,float commercialRateDiscount) {
		this.creditProvidentAmount = creditProvidentAmount;
		this.creditCommercialAmount = creditCommercialAmount;
		this.creditYear = creditYear;
		this.providentRateDiscount = providentRateDiscount;
		this.commercialRateDiscount = commercialRateDiscount;
		providentRates = new float[pRates.length];
		for(int i=0; i<pRates.length; i++){
			providentRates[i] = pRates[i];
		}
		
		commercialRates = new float[cRates.length];
		for(int i=0; i<cRates.length; i++){
			commercialRates[i] = cRates[i];
		}
		
		providentRate = getProvidentRate(creditYear)*providentRateDiscount/1200;
		commercialRate = getCommercialRate(creditYear)*commercialRateDiscount/1200;
	}
	
    /**
     * ����������.
     *
     * @return ���������������
     */
	public float getProvidentRate() {
		return providentRate*1200;
	}

    /**
     * ��ҵ����.
     *
     * @return ��ҵ����������
     */
	public float getCommercialRate() {
		return commercialRate*1200;
	}

	
	/**
	 * ���������ʣ�������������.
	 *
	 * @param yy ����
	 * @return ����
	 */
	private float getProvidentRate(int yy){
		if(yy<=5){
			return providentRates[0];
		}else{
			return providentRates[1];
		}
	}
	
	/**
	 * �̴����ʣ�������������.
	 *
	 * @param yy ����
	 * @return ����
	 */
	private float getCommercialRate(int yy){
		if(yy<=1){
			return commercialRates[0];
		}else if(1<yy && yy<=5){
			return commercialRates[1];
		}else{
			return commercialRates[2];
		}
	}
	
	/**
	 * ����ȶ�𻹿ʽ.
	 *
	 * @return �����·ݵĻ����MonthPay
	 */
	public List<MonthPay> getEqualPrincipal(){
		List<MonthPay> pList = calEqualPrincipal(creditProvidentAmount,creditYear,providentRate);
		List<MonthPay> cList = calEqualPrincipal(creditCommercialAmount,creditYear,commercialRate);
		if(pList.isEmpty())
			return cList;
		if(cList.isEmpty())
			return pList;
		if(pList.isEmpty() && cList.isEmpty())
			return null;
		List<MonthPay> res = new ArrayList<MonthPay>();
		for(int i=0; i<pList.size() && i<cList.size(); i++){
			res.add(new MonthPay(i,pList.get(i).getInterest()+cList.get(i).getInterest(),
					pList.get(i).getPrincipal()+cList.get(i).getPrincipal()));
		}
		return res;
	}
	
	/**
	 * ����ȶϢ���ʽ.
	 *
	 * @return �����·ݵĻ����MonthPay
	 */
	public List<MonthPay> getEqualPrincipalAndInterest(){
		List<MonthPay> pList = calEqualPrincipalAndInterest(creditProvidentAmount,creditYear,providentRate);
		List<MonthPay> cList = calEqualPrincipalAndInterest(creditCommercialAmount,creditYear,commercialRate);
		if(pList.isEmpty())
			return cList;
		if(cList.isEmpty())
			return pList;
		if(pList.isEmpty() && cList.isEmpty())
			return null;
		List<MonthPay> res = new ArrayList<MonthPay>();
		for(int i=0; i<pList.size() && i<cList.size(); i++){
			res.add(new MonthPay(i,pList.get(i).getInterest()+cList.get(i).getInterest(),
					pList.get(i).getPrincipal()+cList.get(i).getPrincipal()));
		}
		return res;
	}
	
	/**
	 * ����ȶ�𻹿��.
	 *
	 * @param amount ���
	 * @param yy ����
	 * @param rate ������
	 * @return �����·ݵĻ����MonthPay
	 */
	private List<MonthPay> calEqualPrincipal(double amount,int yy,float rate){
		List<MonthPay> res = new ArrayList<MonthPay>();
		double unPay = amount;
		double principal = amount/yy/12;
		for(int i=0; i<yy*12; i++){
			double interest = unPay*rate;
			MonthPay monthPay = new MonthPay(i,interest,principal);
			res.add(monthPay);
			unPay -= principal;
		}	
		return res;
	}
	
	/**
	 * ����ȶϢ����[�����������ʡ���1+�����ʣ�^��������]��[��1+�����ʣ�^����������1].
	 *
	 * @param amount ���
	 * @param yy ����
	 * @param rate ������
	 * @return �����·ݵĻ����MonthPay
	 */
	private List<MonthPay> calEqualPrincipalAndInterest(double amount,int yy,float rate){
		List<MonthPay> res = new ArrayList<MonthPay>();
		double unPay = amount;
		double tmp = Math.pow(1+rate, yy*12);
		double monthPayAmount = amount*(rate)*(tmp/(tmp-1));
		for(int i=0; i<yy*12; i++){
			double interest = unPay*rate;
			double principal = monthPayAmount - interest;
			MonthPay monthPay = new MonthPay(i,interest,principal);
			res.add(monthPay);
			unPay -= principal;
		}
		return res;
	}

	/**
	 * ���ù����������.
	 *
	 * @param creditProvidentAmount �����������
	 */
	public void setCreditProvidentAmount(double creditProvidentAmount) {
		this.creditProvidentAmount = creditProvidentAmount;
	}

	/**
	 * ������ҵ������.
	 *
	 * @param creditCommercialAmount ��ҵ������
	 */
	public void setCreditCommercialAmount(double creditCommercialAmount) {
		this.creditCommercialAmount = creditCommercialAmount;
	}

	/**
	 * ���û�������.
	 *
	 * @param creditYear ����
	 */
	public void setCreditYear(int creditYear) {
		this.creditYear = creditYear;
		providentRate = getProvidentRate(creditYear)*providentRateDiscount/1200;
		commercialRate = getCommercialRate(creditYear)*commercialRateDiscount/1200;
	}

	/**
	 * ���ù������ۿ�.
	 *
	 * @param providentRateDiscount �ۿ�
	 */
	public void setProvidentRateDiscount(float providentRateDiscount) {
		this.providentRateDiscount = providentRateDiscount;
		providentRate = getProvidentRate(creditYear)*providentRateDiscount/1200;
	}

	/**
	 * ������ҵ�����ۿ�.
	 *
	 * @param commercialRateDiscount �ۿ�
	 */
	public void setCommercialRateDiscount(float commercialRateDiscount) {
		this.commercialRateDiscount = commercialRateDiscount;
		commercialRate = getCommercialRate(creditYear)*commercialRateDiscount/1200;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Balance{" +
				"creditProvidentAmount=" + creditProvidentAmount +
				", creditCommercialAmount=" + creditCommercialAmount +
				", providentRate=" + providentRate +
				", commercialRate=" + commercialRate +
				", creditYear=" + creditYear +
				", providentRateDiscount=" + providentRateDiscount +
				", commercialRateDiscount=" + commercialRateDiscount +
				'}';
	}
}
