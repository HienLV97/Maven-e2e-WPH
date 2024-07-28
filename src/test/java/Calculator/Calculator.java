package Calculator;


import API.GetAPI.DashboardGraphQL.Categories;
import API.GetAPI.DashboardGraphQL.Price;
import API.GetAPI.NextProxy.Auth.Auth;
import Support.Initialization.Init;
import WPH.OrderForm.pages.OrderFormPage;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;


public class Calculator {
	double pagePrice;
	double writerPrice;
	double preWriter;
	public double absPrice;
	public static int preWriterPercent = 5;
	double discount;
	double balance;
	double singlePagePrice;
	double slidePrice;
	String type;
	String urgent;
	String acalevelNumb;
	int pages;
	int slides;
	String spacing;
	static double youPay;
	public static int writerRate = 30;
	static double writerFee;
	private static WebDriver driver;
	boolean isAbsPrice;
	boolean isPreWriter;
	public Calculator() {
	}
	public void setValuesFromOrderForm(OrderFormPage orderFormPage) {
		this.type = orderFormPage.orderType;
		this.urgent = orderFormPage.urgentTXT;
		this.acalevelNumb = orderFormPage.acalevelTXT;
		this.pages = orderFormPage.pages;
		this.slides = orderFormPage.slides;
		this.spacing = orderFormPage.spacing;
		this.isAbsPrice = orderFormPage.isAbsPrice;
		this.isPreWriter = orderFormPage.isPreWriter;
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
		System.out.println("this.urgentTXT: "+urgent);
		System.out.println("urgentTXT: "+urgent);
		System.out.println("urgentTXT: "+acalevelNumb);
		this.singlePagePrice = Price.GetPrice(urgent, acalevelNumb);

		if (Objects.equals(type, "writing")) {
			System.out.println("singlePagePrice: " + singlePagePrice);
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
		System.out.println("percent: "+percent);
		System.out.println("pagePrice: "+pagePrice);
		BigDecimal bd = new BigDecimal(percent * pagePrice / 100);
		BigDecimal newBD = bd.setScale(4, RoundingMode.HALF_UP);
		BigDecimal roundedValue = newBD.setScale(2, RoundingMode.HALF_UP);
		System.out.println("roundedValue: " + roundedValue);
		this.writerPrice = roundedValue.doubleValue();
		return roundedValue.doubleValue();
	}

	public double abstractPrice() {
		if (isAbsPrice) {
			return this.absPrice = 22.00;
		} else {
			return 0.00;
		}
	}

	public double preWriter() {
		if (isPreWriter) {
			BigDecimal bd = new BigDecimal(preWriterPercent * pagePrice / 100);
			BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
			return	this.preWriter = roundedValue.doubleValue();
//			return roundedValue.doubleValue();
		} else {
			return 0.00;
		}
	}

	public double extrasTotal() {
		System.out.println("writerPrice: "+writerPrice);
		System.out.println("absPrice: "+absPrice);
		System.out.println("preWriter: "+preWriter);
		BigDecimal extraTotal = BigDecimal.valueOf(writerPrice + absPrice + preWriter);
		return extraTotal.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	public double balance(String token) {
		this.balance = Double.parseDouble(Auth.getAuth(token, "balance"));
		return Double.parseDouble(Auth.getAuth(token, "balance"));
	}

	public double grandTotal() {
		BigDecimal bd = new BigDecimal(pagePrice - discount + extrasTotal() - balance);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		double youPay = roundedValue.doubleValue();
		if (youPay <= 0) {
			return youPay = 0.00;
		} else {
			return this.youPay = youPay;
		}
	}

	public static double writerFee() {
		return writerFee = roundToTwoDecimalPlaces(youPay * writerRate/100);
	}
}
