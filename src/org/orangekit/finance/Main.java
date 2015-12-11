package org.orangekit.finance;

import java.util.List;

/**
 * The Class Main.
 */
//test
public class Main {
	
	/** The provident rates. */
	private static float[] providentRates = {2.75f,3.25f};
	
	/** The commercial rates. */
	private static float[] commercialRates = {4.35f,4.75f,4.90f};
	
	/** The provident rate discount. */
	private static float providentRateDiscount = 1.1f;
	
	/** The commercial rate discount. */
	private static float commercialRateDiscount = 0.7f;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		//for test
		Balance balance = new Balance(1000000,500000,5,providentRates,commercialRates,
				providentRateDiscount,commercialRateDiscount);
		List<MonthPay> list = balance.getEqualPrincipalAndInterest();
		for(MonthPay pay:list){
			System.out.println(pay);
		}
	}
}
