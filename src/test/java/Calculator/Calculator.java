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
		System.out.println("code: "+code);
		System.out.println("pagePrice: "+pagePrice);
		BigDecimal bd = new BigDecimal(code * pagePrice / 100);
		System.out.println("BD: "+bd);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		System.out.println("Discount: "+roundedValue.doubleValue());
		return roundedValue.doubleValue();
	}

	public static double WriterLevelPrice(String value,double pagePrice) {
		int percent = Integer.parseInt(Categories.GetCategoryData(value, "percent"));
//		double writerPrice =
		BigDecimal bd = new BigDecimal(percent * pagePrice / 100);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		System.out.println("writer: "+roundedValue.doubleValue());
		return roundedValue.doubleValue();
	}
	public static double GrandTotal(){
		double valuePagePrice = PagePrice("editing","24 hours","High School",6,4,"Single");
		double valueDiscout =  Discount(15,valuePagePrice);
		double valueWriterLevelPrice = WriterLevelPrice("2",valuePagePrice);


		BigDecimal bd = new BigDecimal(valuePagePrice-valueDiscout+valueWriterLevelPrice);
		BigDecimal roundedValue = bd.setScale(2, RoundingMode.HALF_UP);
		return roundedValue.doubleValue();
	}
}
