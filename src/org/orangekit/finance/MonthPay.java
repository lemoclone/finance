package org.orangekit.finance;

/**
 * 每个月的还款明细
 * @author Kyle
 *
 */
public class MonthPay {
	private int month; //月份
	private double total; //该月还款总额
	private double interest; //该月还款利息
	private double principal; //该月还款本金
	
	/**
	 * ABC 构造
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
	public int getMonth() {
		return month;
	}
	public double getTotal() {
		return total;
	}
	public double getInterest() {
		return interest;
	}
	public double getPrincipal() {
		return principal;
	}
	@Override
	public String toString() {
		return "MonthPay [month=" + month + ", total=" + total + ", interest=" + interest + ", principal=" + principal
				+ "]";
	}
	
}