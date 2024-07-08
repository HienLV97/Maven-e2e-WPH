package Calculator;


import API.GetAPI.DashboardGraphQL.Categories;
import API.GetAPI.DashboardGraphQL.Price;
import API.GetAPI.NextProxy.Auth.Auth;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Calculator {
	double pagePrice;
	double writerPrice;
	double preWriter;
	public static double absPrice = 22.00;
	public static int preWriterPercent = 5;
	double discount;
	double balance;
	double singlePagePrice;
	double slidePrice;
	String type;
	String urgent;
	String level;
	int pages;
	int slides;
	String spacing;
	static double youPay;
	public static int writerRate = 30;
	static double writerFee;

	public Calculator(String type, String urgent, String level, int pages, int slides, String spacing) {
		this.type = type;
		this.urgent = urgent;
		this.level = level;
		this.pages = pages;
		this.slides = slides;
		this.spacing = spacing;
	}

	public double slidePrice() {
		return this.slidePrice = slides * singlePagePrice / 2;
	}

	public double slideCost() {
		return this.slidePrice = roundToTwoDecimalPlaces(singlePagePrice / 2);
	}

	public static double roundToTwoDecimalPlaces(double value) {
		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public double spacingPrice() {
		if (Objects.equals(spacing, "Double")) {
			return 0;
		}
		if (Objects.equals(spacing, "Single")) {
			return this.singlePagePrice * 2;
		}
		return 0; // Thêm return 0 ở đây để đảm bảo mọi trường hợp đều có giá trị trả về
	}

	public double pagePrice() {
		this.singlePagePrice = Price.GetPrice(urgent, level);

		if (Objects.equals(type, "writing")) {
			System.out.println("singlePagePrice: " + this.singlePagePrice);
		} else if (Objects.equals(type, "editing")) {
			this.singlePagePrice /= 2;
		}

		double numberofPagePrice = this.singlePagePrice * pages;
		System.out.println("numberofPagePrice: " + numberofPagePrice);

		double slidePrice = slidePrice();
		System.out.println("slidePrice: " + slidePrice);

		double spacingPrice = spacingPrice();
		System.out.println("spacingPrice: " + spacingPrice);

		double total = numberofPagePrice + slidePrice + spacingPrice;
		double roundedValue = roundToTwoDecimalPlaces(total);
		System.out.println("PagePrice: " + roundedValue);

		this.pagePrice = roundedValue;
		return roundedValue;
	}

	public double discount(int code) {
		BigDecimal bd = new BigDecimal(code * this.pagePrice / 100);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		this.discount = roundedValue.doubleValue();
		return roundedValue.doubleValue();
	}

	public double writerLevelPrice(String value) {

		int percent = Integer.parseInt(Categories.GetCategoryData(value, "percent"));
		BigDecimal bd = new BigDecimal(percent * this.pagePrice / 100);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		System.out.println("roundedValue: " + roundedValue);
		this.writerPrice = roundedValue.doubleValue();
		return roundedValue.doubleValue();
	}

	public double abstractPrice(boolean value) {
		if (value) {
			return absPrice;
		} else {
			return 0.00;
		}
	}

	public double preWriter(boolean value) {
		if (value) {
			BigDecimal bd = new BigDecimal(preWriterPercent * this.pagePrice / 100);
			BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
			this.preWriter = roundedValue.doubleValue();
			return roundedValue.doubleValue();
		} else {
			return 0.00;
		}
	}

	public double extrasTotal() {
		BigDecimal extraTotal = BigDecimal.valueOf(this.writerPrice + absPrice + this.preWriter);
		return extraTotal.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	public double balance(String token) {
		this.balance = Double.parseDouble(Auth.getauth(token, "balance"));
		return Double.parseDouble(Auth.getauth(token, "balance"));
	}

	public double grandTotal() {
		BigDecimal bd = new BigDecimal(this.pagePrice - this.discount + extrasTotal() - balance);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		double youPay = roundedValue.doubleValue();
		if (youPay <= 0) {
			return this.youPay = 0.00;
		} else {
			return this.youPay = youPay;
		}
	}

	public static double writerFee() {
		return writerFee = roundToTwoDecimalPlaces(youPay * writerRate/100);
	}
}
