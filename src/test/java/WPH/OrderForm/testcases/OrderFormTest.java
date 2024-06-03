package WPH.OrderForm.testcases;

import API.GetAPI.NextProxy.SignIn.SignIn;
import Calculator.Calculator;
import Keywords.WebUI;
import Support.Constants;
import Support.Initialization.Init;
import Support.WPH.Routers;
import WPH.OrderDetails.Details.pages.DetailsPage;
import WPH.OrderForm.pages.OrderFormPage;
import WPH.SignIn.pages.SignInPage;
import WPH.payment.CreditCard.pages.CreditCardPage;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

//import static Support.Initialization.Init.driver;

public class OrderFormTest extends Init {
	@Test(enabled = false, description = "Checkout successfully")
	public void checkout() {
		Authenticate("WPH");
		OrderFormPage orderForm = new OrderFormPage(driver);
		CreditCardPage creditCardPage = new CreditCardPage(driver);
		SignInPage signInPage = new SignInPage(driver);
		DetailsPage detailsPage = new DetailsPage(driver);

		signInPage.Login(Constants.emailAccount, Constants.passAccount);
		sleep(4);

		//step1
		driver.get(Routers.ORDER);
		waitForPageLoaded();
		orderForm.SetDocumentDRL("Admission Essay");
		sleep(2);
		orderForm.AcalevelOptBTN(2);

		orderForm.setDisciplineDRL("Accounting");
		sleep(2);
		orderForm.AcalevelOptBTN(2);
		orderForm.clickNextButton();

		//step 2

		orderForm.setTitleTXT("test");
		orderForm.setInstructionTXT("test");
		orderForm.clickNextButton();

		//step3

		orderForm.clickWriterCB();
		orderForm.verifyWriterCB();
		orderForm.clickDeadLine(1);
		orderForm.clickPageInc();
		orderForm.clickDoubleBTN();
		orderForm.clickSlideInc();
		orderForm.clickNextButton();
		sleep(5);

		//step4
		orderForm.clickWriterLevelBTN(1);
		orderForm.verifyAbstractCB();
		orderForm.verifyPrevWriterCB();
		sleep(5);
//		orderForm.clickNextButton();
//
//		//step5
//		orderForm.clickCreditBTN();
//		orderForm.clickCheckOutBTN();
//		waitForPageLoaded();
//
//		creditCardPage.getCheckout();
//
//		sleep(5);
//		waitForNavigatePage(null);
//		waitForPageLoaded();
//		String orderID = orderForm.getID();
//		orderForm.clickViewOrderBTN();
//
//		sleep(10);
//		waitForPageLoaded();
//		detailsPage.verifyh1(orderID, "writing");
	}
/*
	@Test(enabled = false)
	public void checkoutSuccess() throws IOException, AWTException {
		OrderFormPage orderForm = new OrderFormPage(driver);
		CreditCardPage creditCardPage = new CreditCardPage(driver);
		SignInPage signInPage = new SignInPage(driver);
		DetailsPage detailsPage = new DetailsPage(driver);

		Authenticate("WPH");
		signInPage.Login(Constants.emailAccount, Constants.passAccount);
		screenShot("TestScreen3");

		driver.get(Routers.ORDER);
		waitForNavigatePage(Routers.ORDER);
		//step1
		orderForm.setStep1();
		orderForm.clickNextButton();

		//step 2
		orderForm.setStep2();
		orderForm.clickNextButton();

		//step3
		WebUI.clickMultiElement(orderForm.SourceIncBTN, 3);
		orderForm.clickDeadLine(1);
		WebUI.clickMultiElement(orderForm.PageIncBTN, 3);
		orderForm.clickSingleBTN();
		WebUI.clickMultiElement(orderForm.SlideIncBTN, 2);
		orderForm.clickNextButton();

		//step4
		orderForm.clickWriterLevelBTN(2);
		orderForm.clickAbstractBTN();
		sleep(1);
		orderForm.setPrevWriterDRL();
		orderForm.clickNextButton();

		//step5
		orderForm.clickCreditBTN();
		orderForm.setDiscountTB("paper15");
		WebUI.clickElement(orderForm.ApllyBTN);
		sleep(2);
		screenShot("ApllyTBN");
		sleep(2);
		waitForPageLoaded();

		orderForm.clickCheckOutBTN();
		creditCardPage.getCheckout();

		sleep(5);
		waitForNavigatePage("NaN");
		waitForPageLoaded();
		String orderID = orderForm.getID();
		orderForm.clickViewOrderBTN();

		// orderDetail
		sleep(2);
		waitForPageLoaded();
		detailsPage.verifyh1(orderID, "writing");
		detailsPage.verifyWPrice(detailsPage.writerPrice, "$152.92");
		detailsPage.verifyWPrice(detailsPage.preWriterPrice, "$21.85");
		detailsPage.verifyWPrice(detailsPage.abstractPrice, "$22.00");
		detailsPage.verifyWPrice(detailsPage.DicountPrice, "$65.54");
		detailsPage.verifyWPrice(detailsPage.PaidPrice, "$568.11");
		detailsPage.verifyWPrice(detailsPage.YouSavedPrice, "$65.54");
	}
*/

	@Test(enabled = true, description = "Order form price display correct")
	public void testVerifyPrice() throws IOException, AWTException {

		SignInPage signInPage = new SignInPage(driver);
		OrderFormPage orderForm = new OrderFormPage(driver);
		CreditCardPage creditCardPage = new CreditCardPage(driver);
		DetailsPage detailsPage = new DetailsPage(driver);
		Calculator calculator = new Calculator();

		Authenticate("WPH");
		String tokenName = "token";
		String email = Constants.emailAccount;
		String password = Constants.passAccount;
		String tokenValue = SignIn.getToken(email,password);
		signInPage.signInWithToken(tokenName, tokenValue);
		calculator.balance(tokenValue);

		sleep(5);
		driver.get(Routers.ORDER);

		//set value
		String type = "writing";
		String document = "Admission Essay";
		int acalevelNumb = 2;
		String acalevelTXT = orderForm.academicLevel.get(acalevelNumb).replace("\"","");
		String discipline = "Accounting";
		//step2
		String title = "test";
		String instruction = "test";
		//step 3
		int deadlineNumb = 3;
		String deadlineTXT = orderForm.deadLineLevel.get(deadlineNumb).replace("\"","");
		int pages = 2;
		int source = 2;
		int slides = 2;
		int writerLevelNumb = 2;
		String spacing = "Double";
		//step4
		boolean absPrice = true;
		boolean preWriter = true;


		String writerLevelTXT = orderForm.writerLevel.get(writerLevelNumb).replace("\"","");

		orderForm.setStep1(document,acalevelNumb,discipline );
		orderForm.setStep2(title,instruction);
		orderForm.setStep3(source,pages,deadlineNumb,slides,spacing);
		orderForm.setStep4(writerLevelNumb);
		orderForm.setDiscountTB("paper15");
		orderForm.clickApply();
		sleep(3);
		System.out.println(type+"   "+deadlineTXT+"   "+acalevelTXT+"   "+pages+"   "+slides+"   "+spacing);

		double expectedTotalNumb = calculator.PagePrice(type,deadlineTXT,acalevelTXT,pages,slides,spacing);
		String expectedTotalTXT = "$"+expectedTotalNumb;
		orderForm.verifyTotal(expectedTotalTXT);

		double expectedDiscountNumb = calculator.Discount(15);
		String expectedDiscountTXT = "$"+expectedDiscountNumb;
		orderForm.verifyDiscount(expectedDiscountTXT);

		System.out.println("writerLevelTXT: "+writerLevelTXT);
		double expectedWriterPriceNumb = calculator.WriterLevelPrice(writerLevelTXT);
		String expectedWriterPriceTXT = "$"+expectedWriterPriceNumb;

		double expectedPreWriterNumb = calculator.preWriter(preWriter);
		String expectedPreWriterTXT = "$"+expectedPreWriterNumb;

		double expectedAbstractPriceNumb = calculator.abstractPrice(absPrice);
		String expectedAbstractPriceTXT = "$"+formatPrice(expectedAbstractPriceNumb);

		String expectedExtraTXT = "$"+calculator.ExtrasTotal();
//		String expectedExtra = "$79.98";

		orderForm.verifyExtra(expectedExtraTXT);

		String expectedYouPay = "$"+ calculator.GrandTotal();
		orderForm.verifyYouPay(expectedYouPay);

		orderForm.clickCreditBTN();
		orderForm.clickCheckOutBTN();
		sleep(10);
		creditCardPage.getCheckout();

		sleep(5);
		waitForNavigatePage("NaN");
		waitForPageLoaded();
		String orderID = orderForm.getID();
		orderForm.clickViewOrderBTN();

		// orderDetail

		sleep(2);
		waitForPageLoaded();
		driver.get("https://writersperhour.dev/order/" + orderID + "/details");
		detailsPage.verifyh1(orderID, "writing");
		detailsPage.verifyWPrice(detailsPage.writerPrice, expectedWriterPriceTXT);
		detailsPage.verifyWPrice(detailsPage.preWriterPrice, expectedPreWriterTXT);
		detailsPage.verifyWPrice(detailsPage.abstractPrice, expectedAbstractPriceTXT);
		detailsPage.verifyWPrice(detailsPage.DicountPrice, expectedDiscountTXT);
		detailsPage.verifyWPrice(detailsPage.PaidPrice, expectedYouPay);
		detailsPage.verifyWPrice(detailsPage.YouSavedPrice, expectedDiscountTXT);
		screenShot("Order detail");


	}

	@Test(enabled = false)
	public void test() {
		SignInPage signInPage = new SignInPage(driver);
		OrderFormPage orderForm = new OrderFormPage(driver);
		CreditCardPage creditCardPage = new CreditCardPage(driver);
		DetailsPage detailsPage = new DetailsPage(driver);
		Authenticate("WPH");
		String tokenName = "token";
		String tokenValue = "2b7ee556b0005feb7dca1b9d54a658f5";
		signInPage.signInWithToken(tokenName, tokenValue);
		String orderID = "88849";

		String expectedYouPay = "$203.19";
		String expectedTotal = "$144.95";
		String expectedExtra = "$79.98";
		String expectedDiscount = "$21.74";
		String expectedWriterPrice = "$50.73";
		String expectedPreWriterPrice = "$7.25";
		String expectedAbstractPrice = "$22.00";

		int acalevelNumb = 2;
		String acalevelText = orderForm.academicLevel.get(acalevelNumb);
		System.out.println("test: " + acalevelText);

//		driver.get("https://writersperhour.dev/order/" + orderID + "/details");
//		detailsPage.verifyh1(orderID, "writing");
//		detailsPage.verifyWPrice(detailsPage.writerPrice, expectedWriterPrice);
//		detailsPage.verifyWPrice(detailsPage.preWriterPrice, expectedPreWriterPrice);
//		detailsPage.verifyWPrice(detailsPage.abstractPrice, expectedAbstractPrice);
//		detailsPage.verifyWPrice(detailsPage.DicountPrice, expectedDiscount);
//		detailsPage.verifyWPrice(detailsPage.PaidPrice, expectedYouPay);
//		detailsPage.verifyWPrice(detailsPage.YouSavedPrice, expectedDiscount);
//
//		//Check Dashboard
//		DashBoard.SignIn.pages.SignInPage signInPageDB = new DashBoard.SignIn.pages.SignInPage(driver);
//		Authenticate("DashBoard");
//		signInPageDB.Login(Constants.email, Constants.passAccount);
//		sleep(5);
//		driver.get(Support.DashBoard.Routers.ORDERS_DETAILS+ orderID);

	}
}
