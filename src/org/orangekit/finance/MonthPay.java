package org.orangekit.finance;

public class MonthPay {
	private int month;
	private double total;
	private double interest;
	private double principal;
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