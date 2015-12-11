package org.orangekit.finance;

// TODO: Auto-generated Javadoc
/**
 * 每个月的还款明细.
 *
 * @author Kyle
 */
public class MonthPay {
	
	/** The month. */
	private int month; //月份
	
	/** The total. */
	private double total; //该月还款总额
	
	/** The interest. */
	private double interest; //该月还款利息
	
	/** The principal. */
	private double principal; //该月还款本金
	
	/**
	 * ABC 构造.
	 *
	 * @param month 月份
	 * @param interest 该月利息
	 * @param principal 该月本金
	 */
	public MonthPay(int month, double interest, double principal) {
		this.month = month;
		this.interest = interest;
		this.principal = principal;
		this.total = interest + principal;
	}
	
	/**
	 * Gets the month.
	 *
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	
	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}
	
	/**
	 * Gets the interest.
	 *
	 * @return the interest
	 */
	public double getInterest() {
		return interest;
	}
	
	/**
	 * Gets the principal.
	 *
	 * @return the principal
	 */
	public double getPrincipal() {
		return principal;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MonthPay [month=" + month + ", total=" + total + ", interest=" + interest + ", principal=" + principal
				+ "]";
	}
	
}