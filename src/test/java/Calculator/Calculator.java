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

	private static double getPagePrice;
	private static double writerPrice;
	private static double preWriter;
	private static String preWriterRound;
	private static String extraTotalRound;
	private static double absPrice;
	private static final int preWriterPercent = 5;
	private static double discount;
	private static String discountRound;
	private static double balance;
	private static double singlePagePrice;
	private static double slidePrice;
	private static double slideCost;
	private static String writerLevelPriceRound;
	private static double extraTotal;
	private static String type;
	private static String urgent;
	private static String acalevelNumb;
	private static int pages;
	private static int slides;
	private static String spacing;
	private static double youPay;
	private static final int writerRate = 30;
	private static double writerFee;
	private static WebDriver driver;
	private static boolean isAbsPrice;
	private static boolean isPreWriter;
	private static String disCode;
	private static double grandTotal;
	private static String writerLvl;

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

	public static String getPreWriterRound() {
		preWriter();
		return preWriterRound;
	}

	public String getExtraTotalRound() {
		extrasTotal();
		return extraTotalRound;
	}

	public static double getAbsPrice() {
		abstractPrice();
		return absPrice;
	}

	public static int getPreWriterPercent() {
		return preWriterPercent;
	}

	public double getDiscount() {
		discount();
		return discount;
	}

	public static String getDiscountRound() {
		discount();
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

	public static String getWriterLevelPriceRound() {
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

	public static String getDisCode() {
		return disCode;
	}

	public static double getGrandTotal() {
		grandTotal();
		return grandTotal;
	}

	public void setValuesFromOrderForm(OrderFormPage orderFormPage) {
		type = orderFormPage.orderType;
		urgent = orderFormPage.urgentTXT;
		acalevelNumb = orderFormPage.acalevelTXT;
		pages = orderFormPage.pages;
		slides = orderFormPage.slides;
		spacing = orderFormPage.spacing;
		isAbsPrice = orderFormPage.isAbsPrice;
		isPreWriter = orderFormPage.isPreWriter;
		disCode = orderFormPage.disCode;
		writerLvl = orderFormPage.writerLvlTxt;
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

	public void slidePrice() {
		slidePrice = slides * singlePagePrice / 2;
	}

	public void slideCost() {
		slideCost = roundToTwoDecimalPlaces(singlePagePrice / 2);
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
		singlePagePrice = Price.GetPrice(urgent, acalevelNumb);

		if (Objects.equals(type, "writing")) {
			System.out.println("singlePagePrice: " + singlePagePrice);
		} else if (Objects.equals(type, "editing")) {
			singlePagePrice /= 2;
		}

		double numberofPagePrice = singlePagePrice * pages;

		double slidePrice = getSlidePrice();

		double spacingPrice = spacingPrice();

		double total = numberofPagePrice + slidePrice + spacingPrice;

		getPagePrice = roundToTwoDecimalPlaces(total);
		System.out.println("getPagePrice: "+getPagePrice);
	}

	static void discount() {
		// int percent = Integer.parseInt(GetDiscount(getDisCode(), "percent"));
		int percent = Discounts.GetDiscount(getDisCode());
		BigDecimal bd = new BigDecimal(percent * getPagePrice / 100);
		BigDecimal roundedValue2 = bd.setScale(2, RoundingMode.HALF_UP);
		DecimalFormat df = new DecimalFormat("#.00");
		discountRound = df.format(roundedValue2);
		discount = bd.doubleValue();
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
		writerLevelPriceRound = df.format(roundedValue2);
		writerPrice = roundedValue.doubleValue();
		System.out.println("roundedValue2.doubleValue(): " + roundedValue2.doubleValue());
		System.out.println("bigde roundedValue2: " + roundedValue2);

		// return this.writerPrice = roundedValue.doubleValue();
	}

	public static void abstractPrice() {
		if (isAbsPrice) {
			absPrice = 22.00;
		}
	}

	public static void preWriter() {
		if (isPreWriter) {
			BigDecimal bd = new BigDecimal(preWriterPercent * getPagePrice / 100);
			BigDecimal roundedValue2 = bd.setScale(2, RoundingMode.HALF_UP);
			BigDecimal roundedValue3 = bd.setScale(3, RoundingMode.HALF_UP);
			DecimalFormat df = new DecimalFormat("#.00");
			String formattedNumber = df.format(roundedValue2);
			System.out.println("formattedNumber: "+formattedNumber);
			preWriterRound = formattedNumber;
			preWriter = roundedValue3.doubleValue();
		}
	}

	public void extrasTotal() {

		System.out.println("writerPrice: " + getWriterPrice());
		System.out.println("absPrice: " + getAbsPrice());
		System.out.println("preWriter: " + getPreWriter());
		BigDecimal bd = BigDecimal.valueOf(getWriterPrice() + getAbsPrice() + getPreWriter());
		BigDecimal roundedValue2 = bd.setScale(2, RoundingMode.HALF_UP);
		BigDecimal roundedValue3 = bd.setScale(3, RoundingMode.HALF_UP);
		DecimalFormat df = new DecimalFormat("#.00");
		String formattedNumber = df.format(roundedValue2);
		System.out.println("formattedNumber: "+formattedNumber);
		extraTotalRound =formattedNumber;
		System.out.println("extraTotalRound: " + extraTotalRound);
		extraTotal = roundedValue3.doubleValue();
	}

	public void balance(String token) {
		balance = Double.parseDouble(Auth.getAuth(token, "balance"));
		// Double.parseDouble(Auth.getAuth(token, "balance")); // Dòng này không cần thiết
	}


	public static double grandTotal() {
		System.out.println("-------");
		System.out.println("getPagePrice: "+ getPagePrice);
		System.out.println("discount: "+ discount);
		System.out.println("extraTotal: "+ extraTotal);
		System.out.println("balance: "+ balance);
		BigDecimal bd = new BigDecimal(getPagePrice - discount + extraTotal - balance);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		grandTotal = roundedValue.doubleValue();
		if (youPay <= 0) {
			return youPay = 0.00;
		} else {
			return youPay;
		}
	}

	public void writerFee() {
		writerFee = roundToTwoDecimalPlaces(youPay * writerRate / 100);
	}
}
