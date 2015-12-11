package org.orangekit.finance;

// TODO: Auto-generated Javadoc
/**
 * ÿ���µĻ�����ϸ.
 *
 * @author Kyle
 */
public class MonthPay {
	
	/** The month. */
	private int month; //�·�
	
	/** The total. */
	private double total; //���»����ܶ�
	
	/** The interest. */
	private double interest; //���»�����Ϣ
	
	/** The principal. */
	private double principal; //���»����
	
	/**
	 * ABC ����.
	 *
	 * @param month �·�
	 * @param interest ������Ϣ
	 * @param principal ���±���
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