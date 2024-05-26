package WPH.OrderForm.testcases;

import API.GetAPI.NextProxy.Auth.Auth;
import API.GetAPI.NextProxy.SignIn.SignIn;
import Calculator.Calculator;
import Keywords.WebUI;
import Support.Constants;
import Support.Initialization.Init;
import Support.Routers;
import WPH.OrderDetails.Details.pages.DetailsPage;
import WPH.OrderForm.pages.OrderFormPage;
import WPH.SignIn.pages.SignInPage;
import WPH.payment.CreditCard.pages.CreditCardPage;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

//import static Support.Initialization.Init.driver;

public class OrderFormTest extends Init {
	@Test(enabled = false, description = "Checkout successfully")
	public void checkout() {
		Authenticate();
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

	@Test(enabled = false)
	public void checkoutSuccess() throws IOException, AWTException {
		OrderFormPage orderForm = new OrderFormPage(driver);
		CreditCardPage creditCardPage = new CreditCardPage(driver);
		SignInPage signInPage = new SignInPage(driver);
		DetailsPage detailsPage = new DetailsPage(driver);

		Authenticate();
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


	@Test(enabled = true, description = "Order form price display correct")
	public void testVerifyPrice() {

		SignInPage signInPage = new SignInPage(driver);
		OrderFormPage orderForm = new OrderFormPage(driver);
		CreditCardPage creditCardPage = new CreditCardPage(driver);
		DetailsPage detailsPage = new DetailsPage(driver);
		Authenticate();
		String tokenName = "token";
		String tokenValue = SignIn.getToken(Constants.emailAccount, Constants.passAccount);
		signInPage.signInWithToken(tokenName, tokenValue);

		sleep(5);
		driver.get(Routers.ORDER);
		orderForm.setStep1();
		orderForm.setStep2();
		orderForm.setStep3();
		orderForm.setStep4();
		orderForm.setDiscountTB("paper15");
		orderForm.clickApply();
		sleep(3);
		String expectedYouPay = "$203.19";
		orderForm.verifyYouPay(expectedYouPay);
		String expectedTotal = "$144.95";
		orderForm.verifyTotal(expectedTotal);
		String expectedExtra = "$79.98";
		orderForm.verifyExtra(expectedExtra);
		String expectedDiscount = "$21.74";
		orderForm.verifyDiscount(expectedDiscount);

		orderForm.clickCreditBTN();
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
		detailsPage.verifyWPrice(detailsPage.writerPrice, expectedTotal);
		detailsPage.verifyWPrice(detailsPage.preWriterPrice, "$55.98");
		detailsPage.verifyWPrice(detailsPage.abstractPrice, "$22.00");
		detailsPage.verifyWPrice(detailsPage.DicountPrice, expectedDiscount);
		detailsPage.verifyWPrice(detailsPage.PaidPrice, expectedYouPay);
		detailsPage.verifyWPrice(detailsPage.YouSavedPrice, expectedDiscount);
	}
}
