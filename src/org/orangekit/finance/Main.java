package org.orangekit.finance;

import java.util.List;

public class Main {
	private static float[] providentRates = {2.75f,3.25f};
	private static float[] commercialRates = {4.35f,4.75f,4.90f};
	private static float providentRateDiscount = 1.1f;
	private static float commercialRateDiscount = 0.7f;
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
