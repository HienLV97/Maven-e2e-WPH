package WPH.OrderDetails.Details.pages;

import Calculator.Calculator;
import AcaWriting.Keywords.WebUI;
import WPH.OrderForm.pages.OrderFormPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static AcaWriting.Support.Initialization.Init.formatPrice;

public class DetailsPage {
	private String type;
	private String urgent;
	private String acalevelNumb;
	private int pages;
	private int slides;
	private String spacing;
	boolean isAbsPrice;
	boolean isPreWriter;
	private String disCode;
	private String writerLvl;

	private WebDriver driver;
	private WebDriverWait wait;

	public DetailsPage(WebDriver driver) {
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
	}

	public By h1Element= By.xpath("//div[@class='order-id']");
	public By writerPrice = By.xpath("//*[normalize-space()='Writer Category']//following-sibling::*");
	public By preWriterPrice = By.xpath("//*[normalize-space()='Previous Writer']//following-sibling::*");
	public By abstractPrice = By.xpath("//*[normalize-space()='One-page Abstract']//following-sibling::*");
	public By DicountPrice = By.xpath("//*[normalize-space()='Discount']//following-sibling::*");
	public By PaidPrice = By.xpath("//*[normalize-space()='YOU PAID']//following-sibling::*");
	public By YouSavedPrice = By.xpath("//*[normalize-space()='You saved']//following-sibling::*");
	public void verifyh1(String id, String orderType) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(h1Element));
		driver.findElement(h1Element);
		String h1Text = driver.findElement(h1Element).getText();

		System.out.println("h1Text: "+ h1Text);

		// Kiểm tra xem văn bản có chứa "00088984" và "writing"
		boolean containsOrderId = h1Text.contains(id);
		boolean containsWriting = h1Text.contains(orderType);

		// Sử dụng assert để kiểm tra
		Assert.assertTrue(containsOrderId,"Thẻ h1 phải chứa "+id);
		Assert.assertTrue(containsWriting,"Thẻ h1 phải chứa "+orderType);
	}
	public void verifyWPrice(By by,String value){
		WebUI.waitForElementVisible(by);
		String price = WebUI.getElementText(by);
		System.out.println(by);
		WebUI.assertEquals(price,value);
	}
    public void verifyPriceDetails () {
		verifyWPrice(writerPrice,  "$" + Calculator.getWriterLevelPriceRound());
		if (isPreWriter){
			verifyWPrice(preWriterPrice, "$" + Calculator.getPreWriterRound());
		}
		if (isAbsPrice){
			verifyWPrice(abstractPrice, "$" + formatPrice(Calculator.getAbsPrice()));
		}
		if (disCode!=null){
			verifyWPrice(DicountPrice, "$" + Calculator.getDiscountRound());
			verifyWPrice(YouSavedPrice, "$" + Calculator.getDiscountRound());
		}
		verifyWPrice(PaidPrice,  "$" + Calculator.getGrandTotal());

	}

}
