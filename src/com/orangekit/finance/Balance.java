package com.orangekit.finance;

import java.util.ArrayList;
import java.util.List;

public class Balance {
	private double creditProvidentAmount;
	private double creditCommercialAmount;
	private int creditYear;
	private List<MonthPay> list;
	private float[] providentRates = {2.75f,3.25f};
	private float[] commercialRates = {4.35f,4.75f,4.90f};
	private float providentRate;
	private float commercialRate;
	
	
	public Balance(double creditProvidentAmount, double creditCommercialAmount, int creditYear,
			float[] pRates, float[] cRates) {
		this.creditProvidentAmount = creditProvidentAmount;
		this.creditCommercialAmount = creditCommercialAmount;
		this.creditYear = creditYear;
		providentRates = new float[pRates.length];
		for(int i=0; i<pRates.length; i++){
			providentRates[i] = pRates[i];
		}
		
		commercialRates = new float[cRates.length];
		for(int i=0; i<cRates.length; i++){
			commercialRates[i] = cRates[i];
		}
		
		providentRate = getProvidentRate(creditYear);
		commercialRate = getCommercialRate(creditYear);
	}
	
	private float getProvidentRate(int yy){
		if(yy<5){
			return providentRates[0]/12;
		}else{
			return providentRates[1]/12;
		}
	}
	
	private float getCommercialRate(int yy){
		if(yy<=1){
			return commercialRates[0]/12;
		}else if(1<yy && yy<=5){
			return commercialRates[1]/12;
		}else{
			return commercialRates[2]/12;
		}
	}
	
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
	
}
