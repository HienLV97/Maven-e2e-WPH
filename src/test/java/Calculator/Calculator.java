package Calculator;


import API.GetAPI.DashboardGraphQL.Categories;
import API.GetAPI.DashboardGraphQL.Price;
import API.GetAPI.DashboardGraphQL.Discounts;
import API.GetAPI.NextProxy.Auth.Auth;
import WPH.OrderForm.pages.OrderFormPage;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.text.DecimalFormat;


public class Calculator {

	private double getPagePrice;
	private double writerPrice;
	private double preWriter;
	private double preWriterRound;
	private double extraTotalRound;
	private double absPrice;
	private static int preWriterPercent = 5;
	private double discount;
	private double discountRound;
	private double balance;
	private double singlePagePrice;
	private double slidePrice;
	private double slideCost;
	private String writerLevelPriceRound;
	private double extraTotal;
	private String type;
	private String urgent;
	private String acalevelNumb;
	private int pages;
	private int slides;
	private String spacing;
	private double youPay;
	private static int writerRate = 30;
	private static double writerFee;
	private static WebDriver driver;
	private boolean isAbsPrice;
	private boolean isPreWriter;
	private String disCode;
	private double grandTotal;
	private String writerLvl;

	public Calculator() {
	}

	// Getters
	public double getGetPagePrice() {
		pagePrice();
		return getPagePrice;
	}

	public double getWriterPrice() {
		writerLevelPrice();
		return writerPrice;
	}

	public double getPreWriter() {
		preWriter();
		return preWriter;
	}

	public double getPreWriterRound() {
		preWriter();
		return preWriterRound;
	}

	public double getExtraTotalRound() {
		extrasTotal();
		return extraTotalRound;
	}

	public double getAbsPrice() {
		abstractPrice();
		return absPrice;
	}

	public static int getPreWriterPercent() {
		return preWriterPercent;
	}

	public double getDiscount() {
		discount();
		return discountRound;
	}

	public double getDiscountRound() {
		return discountRound;
	}

	public double getBalance() {
		return balance;
	}

	public double getSinglePagePrice() {
		return singlePagePrice;
	}

	public double getSlidePrice() {
		return slidePrice;
	}
	public double getSlideCost() {
		return slideCost;
	}

	public double getWriterLevelPriceRound() {
		return writerLevelPriceRound;
	}

	public double getExtraTotal() {
		return extraTotal;
	}

	public String getType() {
		return type;
	}

	public String getUrgent() {
		return urgent;
	}

	public String getAcaLevelNumb() {
		return acalevelNumb;
	}

	public int getPages() {
		return pages;
	}

	public int getSlides() {
		return slides;
	}

	public String getSpacing() {
		return spacing;
	}

	public double getYouPay() {
		return youPay;
	}

	public static int getWriterRate() {
		return writerRate;
	}

	public static double getWriterFee() {
		return writerFee;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public boolean isAbsPrice() {
		return isAbsPrice;
	}

	public boolean isPreWriter() {
		return isPreWriter;
	}

	public String getDisCode() {
		return disCode;
	}

	public double getGrandTotal() {
		grandTotal();
		return grandTotal;
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
		this.disCode = orderFormPage.disCode;
		this.writerLvl = orderFormPage.writerLvlTxt;
		slidePrice();
		slideCost();
		writerLevelPrice();
		spacingPrice();
		pagePrice();
		discount();
		abstractPrice();
		preWriter();
		extrasTotal();
		writerFee();
	}

	public double slidePrice() {
		return slidePrice = slides * singlePagePrice / 2;
	}

	public double slideCost() {
		return slideCost = roundToTwoDecimalPlaces(singlePagePrice / 2);
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
			return singlePagePrice * 2;
		}
		return 0; // Thêm return 0 ở đây để đảm bảo mọi trường hợp đều có giá trị trả về
	}

	void pagePrice() {
		this.singlePagePrice = Price.GetPrice(urgent, acalevelNumb);

		if (Objects.equals(type, "writing")) {
			System.out.println("singlePagePrice: " + singlePagePrice);
		} else if (Objects.equals(type, "editing")) {
			this.singlePagePrice /= 2;
		}

		double numberofPagePrice = this.singlePagePrice * pages;

		double slidePrice = getSlidePrice();

		double spacingPrice = spacingPrice();

		double total = numberofPagePrice + slidePrice + spacingPrice;

		this.getPagePrice = roundToTwoDecimalPlaces(total);
		System.out.println("getPagePrice: "+getPagePrice);
	}

	void discount() {
		// int percent = Integer.parseInt(GetDiscount(getDisCode(), "percent"));
		int percent = Discounts.GetDiscount(getDisCode());
		BigDecimal bd = new BigDecimal(percent * getPagePrice / 100);
		BigDecimal roundedValue2 = bd.setScale(2, RoundingMode.HALF_UP);
		this.discountRound = roundedValue2.doubleValue();
		this.discount = bd.doubleValue();
	}

	public void writerLevelPrice() {
		
		int percent = Integer.parseInt(Categories.GetCategoryData(writerLvl, "percent"));
		BigDecimal bd = new BigDecimal(percent * getPagePrice / 100);
		BigDecimal newBD = bd.setScale(4, RoundingMode.HALF_UP);
		BigDecimal roundedValue = newBD.setScale(3, RoundingMode.HALF_UP);
		BigDecimal roundedValue2 = newBD.setScale(2, RoundingMode.HALF_UP);
		System.out.println("roundedValue: " + roundedValue);
		System.out.println("roundedValue2: " + roundedValue2);
		DecimalFormat df = new DecimalFormat("#.00");
        String formattedNumber = df.format(roundedValue2);
		this.writerLevelPriceRound = formattedNumber;
		this.writerPrice = roundedValue.doubleValue();
		System.out.println("roundedValue2.doubleValue(): " + roundedValue2.doubleValue());
		System.out.println("bigde roundedValue2: " + roundedValue2);

		// return this.writerPrice = roundedValue.doubleValue();
	}

	public void abstractPrice() {
		if (isAbsPrice) {
			this.absPrice = 22.00;
		}
	}

	public void preWriter() {
		if (isPreWriter) {
			BigDecimal bd = new BigDecimal(preWriterPercent * getPagePrice / 100);
			BigDecimal roundedValue2 = bd.setScale(2, RoundingMode.HALF_UP);
			BigDecimal roundedValue3 = bd.setScale(3, RoundingMode.HALF_UP);
			this.preWriterRound = roundedValue2.doubleValue();
			this.preWriter = roundedValue3.doubleValue();
//			return roundedValue.doubleValue();
		}
	}

	public void extrasTotal() {

		System.out.println("writerPrice: " + getWriterPrice());
		System.out.println("absPrice: " + getAbsPrice());
		System.out.println("preWriter: " + getPreWriter());
		BigDecimal bd = BigDecimal.valueOf(getWriterPrice() + getAbsPrice() + getPreWriter());
		BigDecimal roundedValue2 = bd.setScale(2, RoundingMode.HALF_UP);
		BigDecimal roundedValue3 = bd.setScale(3, RoundingMode.HALF_UP);
		this.extraTotalRound = roundedValue2.doubleValue();
		System.out.println("extraTotalRound: " + extraTotalRound);
		this.extraTotal = roundedValue3.doubleValue();
	}

	public void balance(String token) {
		this.balance = Double.parseDouble(Auth.getAuth(token, "balance"));
		// Double.parseDouble(Auth.getAuth(token, "balance")); // Dòng này không cần thiết
	}


	public double grandTotal() {
		System.out.println("-------");
		System.out.println("getPagePrice: "+ getPagePrice);
		System.out.println("discount: "+ discount);
		System.out.println("extraTotal: "+ extraTotal);
		System.out.println("balance: "+ balance);
		BigDecimal bd = new BigDecimal(getPagePrice - discount + extraTotal - balance);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		this.grandTotal = roundedValue.doubleValue();
		if (youPay <= 0) {
			return youPay = 0.00;
		} else {
			return youPay;
		}
	}

	public void writerFee() {
		writerFee = roundToTwoDecimalPlaces(this.youPay * writerRate / 100);
	}
}
