package Calculator;


import API.GetAPI.DashboardGraphQL.Categories;
import API.GetAPI.DashboardGraphQL.Price;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Calculator {
	public static double PagePrice(String type, String urgent, String level, int pages, int slides, String spacing) {
		double pagePrice = 0;
		double singlePagePrice = 0;
		System.out.println("a");
		if (Objects.equals(type, null)) {

		}
		if (Objects.equals(type, "writing")) {
			singlePagePrice = Price.GetPrice(urgent, level);
			;
		}
		if (Objects.equals(type, "editing")) {
			singlePagePrice = Price.GetPrice(urgent, level) / 2;
		}
		double numberofPagePrice = singlePagePrice * pages;
		double slidePrice = slides * singlePagePrice / 2;
		double spacingPrice = 0;
		if (Objects.equals(spacing, "Double")) {
			spacingPrice = 0;
		}
		if (Objects.equals(spacing, "Single")) {
			spacingPrice = numberofPagePrice;
		}
		BigDecimal bd = new BigDecimal((numberofPagePrice + slidePrice + spacingPrice));
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
//		System.out.println("slidePrice :" + slidePrice);
//		System.out.println("spacingPrice :" + spacingPrice);
//		System.out.println("numberofPagePrice :" + numberofPagePrice);
		System.out.println("PagePrice: "+roundedValue.doubleValue());
		return roundedValue.doubleValue();
	}

	public static double Discount(int code, double pagePrice) {
		BigDecimal bd = new BigDecimal(code * pagePrice / 100);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		return roundedValue.doubleValue();
	}

	public static double WriterLevelPrice(String value,double pagePrice) {
		int percent = Integer.parseInt(Categories.GetCategoryData(value, "percent"));
		BigDecimal bd = new BigDecimal(percent * pagePrice / 100);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		return roundedValue.doubleValue();
	}
	public static double abstractPrice(Boolean value){
		if (value){
			return 22.00;
		}else {
			return 0.00;
		}
	}
	public static double preWriter(boolean value,double pagePrice){
		if (value){
			BigDecimal bd = new BigDecimal(5* pagePrice / 100);
			BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
			return roundedValue.doubleValue();
		}else {
			return 0.00;
		}
	}
	public static double ExtrasTotal(double writerCate, double absP,double preWriter){
		double extraTotal = (writerCate+absP+preWriter);
		return extraTotal;
	}

//	public static double Balance(int value){
//
//	};

	public static double GrandTotal(double pagePrice, double discount, double extras){
		BigDecimal bd = new BigDecimal(pagePrice-discount+extras);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		return roundedValue.doubleValue();
	}

}
