package org.orangekit.finance;

import java.util.ArrayList;
import java.util.List;

/**
 * 平衡表.
 *
 * @author Kyle
 */
public class Balance {
	
	/** The credit provident amount. */
	private double creditProvidentAmount; //公积金贷款额度
	
	/** The credit commercial amount. */
	private double creditCommercialAmount;//商业贷款额度
	
	/** The credit year. */
	private int creditYear;				//贷款年数
	
	/** The provident rates. */
	private float[] providentRates = {2.75f,3.25f};//公积金利率表
	
	/** The commercial rates. */
	private float[] commercialRates = {4.35f,4.75f,4.90f};//商业利率表
	
	/** The provident rate. */
	private float providentRate; //公积金利率
	
	/** The commercial rate. */
	private float commercialRate;//商业利率
	
	/** The provident rate discount. */
	private float providentRateDiscount;//公积金利率折扣
	
	/** The commercial rate discount. */
	private float commercialRateDiscount;//商业利率折扣

	/**
	 * 构造.
	 *
	 * @param creditProvidentAmount 公积金数额
	 * @param creditCommercialAmount 商贷数额
	 * @param creditYear 年限
	 * @param pRates 公积金利率
	 * @param cRates 商贷利率
	 * @param providentRateDiscount 公积金折扣
	 * @param commercialRateDiscount 商贷折扣
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
     * 公积金利率.
     *
     * @return 公积金贷款年利率
     */
	public float getProvidentRate() {
		return providentRate*1200;
	}

    /**
     * 商业利率.
     *
     * @return 商业贷款年利率
     */
	public float getCommercialRate() {
		return commercialRate*1200;
	}

	
	/**
	 * 公积金利率，根据年限区分.
	 *
	 * @param yy 年限
	 * @return 利率
	 */
	private float getProvidentRate(int yy){
		if(yy<=5){
			return providentRates[0];
		}else{
			return providentRates[1];
		}
	}
	
	/**
	 * 商贷利率，根据年限区分.
	 *
	 * @param yy 年限
	 * @return 利率
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
	 * 计算等额本金还款方式.
	 *
	 * @return 所有月份的还款额MonthPay
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
	 * 计算等额本息还款方式.
	 *
	 * @return 所有月份的还款额MonthPay
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
	 * 计算等额本金还款额.
	 *
	 * @param amount 金额
	 * @param yy 年限
	 * @param rate 月利率
	 * @return 所有月份的还款额MonthPay
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
	 * 计算等额本息还款额，[贷款本金×月利率×（1+月利率）^还款月数]÷[（1+月利率）^还款月数－1].
	 *
	 * @param amount 金额
	 * @param yy 年限
	 * @param rate 月利率
	 * @return 所有月份的还款额MonthPay
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
	 * 设置公积金贷款金额.
	 *
	 * @param creditProvidentAmount 公积金贷款金额
	 */
	public void setCreditProvidentAmount(double creditProvidentAmount) {
		this.creditProvidentAmount = creditProvidentAmount;
	}

	/**
	 * 设置商业贷款金额.
	 *
	 * @param creditCommercialAmount 商业贷款金额
	 */
	public void setCreditCommercialAmount(double creditCommercialAmount) {
		this.creditCommercialAmount = creditCommercialAmount;
	}

	/**
	 * 设置还款年限.
	 *
	 * @param creditYear 年限
	 */
	public void setCreditYear(int creditYear) {
		this.creditYear = creditYear;
		providentRate = getProvidentRate(creditYear)*providentRateDiscount/1200;
		commercialRate = getCommercialRate(creditYear)*commercialRateDiscount/1200;
	}

	/**
	 * 设置公积金折扣.
	 *
	 * @param providentRateDiscount 折扣
	 */
	public void setProvidentRateDiscount(float providentRateDiscount) {
		this.providentRateDiscount = providentRateDiscount;
		providentRate = getProvidentRate(creditYear)*providentRateDiscount/1200;
	}

	/**
	 * 设置商业贷款折扣.
	 *
	 * @param commercialRateDiscount 折扣
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
