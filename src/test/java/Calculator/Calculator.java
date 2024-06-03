package Calculator;


import API.GetAPI.DashboardGraphQL.Categories;
import API.GetAPI.DashboardGraphQL.Price;
import API.GetAPI.NextProxy.Auth.Auth;
import API.GetAPI.NextProxy.SignIn.SignIn;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Calculator {
	private double pagePrice;
	private double writerPrice;
	private double preWriter;
	private double abstractPrice;
	private double discount;
	private double balance;
	public double PagePrice(String type, String urgent, String level, int pages, int slides, String spacing) {
//	
		double singlePagePrice = 0;
		if (Objects.equals(type, "writing")) {
			singlePagePrice = Price.GetPrice(urgent, level);
			System.out.println(" singlePagePrice: "+ singlePagePrice);
		}
		if (Objects.equals(type, "editing")) {
			singlePagePrice = Price.GetPrice(urgent, level) / 2;
		}
//		System.out.println(" singlePagePrice: "+ singlePagePrice);
		double numberofPagePrice = singlePagePrice * pages;
		double slidePrice = slides * singlePagePrice / 2;
		double spacingPrice = 0;
		if (Objects.equals(spacing, "Double")) {
			spacingPrice = 0;
		}
		if (Objects.equals(spacing, "Single")) {
			spacingPrice = numberofPagePrice;
		}
		BigDecimal bd = BigDecimal.valueOf(numberofPagePrice + slidePrice + spacingPrice);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		System.out.println("PagePrice: " + roundedValue.doubleValue());

		this.pagePrice = roundedValue.doubleValue();
		return roundedValue.doubleValue();
	}

	public double Discount(int code) {
		BigDecimal bd = new BigDecimal(code * this.pagePrice / 100);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		this.discount = roundedValue.doubleValue();
		return roundedValue.doubleValue();
	}

	public double WriterLevelPrice(String value) {

		int percent = Integer.parseInt(Categories.GetCategoryData(value, "percent"));
		BigDecimal bd = new BigDecimal(percent * this.pagePrice / 100);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		System.out.println("roundedValue: "+roundedValue);
		this.writerPrice = roundedValue.doubleValue();
		return roundedValue.doubleValue();
	}

	public double abstractPrice(boolean value) {
		if (value) {
			this.abstractPrice = 22.00;
			return 22.00;
		} else {
			return 0.00;
		}
	}

	public double preWriter(boolean value) {
		if (value) {
			BigDecimal bd = new BigDecimal(5 * this.pagePrice / 100);
			BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
			this.preWriter = roundedValue.doubleValue();
			return roundedValue.doubleValue();
		} else {
			return 0.00;
		}
	}

	public double ExtrasTotal() {
		double extraTotal = (this.writerPrice + this.abstractPrice + this.preWriter);
		return extraTotal;
	}

	public double balance(String token) {
		this.balance =  Double.parseDouble(Auth.getauth(token, "balance"));
		return Double.parseDouble(Auth.getauth(token, "balance"));
	}

	public double GrandTotal() {
		BigDecimal bd = new BigDecimal(this.pagePrice - this.discount + ExtrasTotal() - balance);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		double youPay = roundedValue.doubleValue();
		if (youPay <= 0) {
			return 0.00;
		} else {
			return youPay;
		}
	}

}
