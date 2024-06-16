package WPH.OrderForm.testcases;

import API.GetAPI.NextProxy.Citation.Citation;
import API.GetAPI.NextProxy.SignIn.SignIn;
import Calculator.Calculator;
import DashBoard.OrderDetail.pages.OrderDetailPage;
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
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

//import static Support.Initialization.Init.driver;

public class OrderFormTest extends Init {
//	@Test(enabled = false, description = "Checkout successfully")
//	public void checkout() {
//		Authenticate("WPH");
//		OrderFormPage orderForm = new OrderFormPage(driver);
//		CreditCardPage creditCardPage = new CreditCardPage(driver);
//		SignInPage signInPage = new SignInPage(driver);
//		DetailsPage detailsPage = new DetailsPage(driver);
//
//		signInPage.Login(Constants.emailAccount, Constants.passAccount);
//		sleep(4);
//
//		//step1
//		driver.get(Routers.ORDER);
//		waitForPageLoaded();
//		orderForm.SetDocumentDRL("Admission Essay");
//		sleep(2);
//		orderForm.AcalevelOptBTN(2);
//
//		orderForm.setDisciplineDRL("Accounting");
//		sleep(2);
//		orderForm.AcalevelOptBTN(2);
//		orderForm.clickNextButton();
//
//		//step 2
//
//		orderForm.setTitleTXT("test");
//		orderForm.setInstructionTXT("test");
//		orderForm.clickNextButton();
//
//		//step3
//
//		orderForm.clickWriterCB();
//		orderForm.verifyWriterCB();
//		orderForm.clickDeadLine(1);
//		orderForm.clickPageInc();
//		orderForm.clickDoubleBTN();
//		orderForm.clickSlideInc();
//		orderForm.clickNextButton();
//		sleep(5);
//
//		//step4
//		orderForm.clickWriterLevelBTN(1);
//		orderForm.verifyAbstractCB();
//		orderForm.verifyPrevWriterCB();
//		sleep(5);
////		orderForm.clickNextButton();
////
////		//step5
////		orderForm.clickCreditBTN();
////		orderForm.clickCheckOutBTN();
////		waitForPageLoaded();
////
////		creditCardPage.getCheckout();
////
////		sleep(5);
////		waitForNavigatePage(null);
////		waitForPageLoaded();
////		String orderID = orderForm.getID();
////		orderForm.clickViewOrderBTN();
////
////		sleep(10);
////		waitForPageLoaded();
////		detailsPage.verifyh1(orderID, "writing");
//	}

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

		//set value
		String type = "writing";
		String document = "Admission Essay";
		int acalevelNumb = 2;
		String acalevelTXT = orderForm.academicLevel.get(acalevelNumb).replace("\"","");
		String discipline = "Accounting";
		int paperFormat = 2;
//		String paperFormatTXT = orderForm.p
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

		//step1
		orderForm.setStep1(document,acalevelNumb,discipline );
		orderForm.clickNextButton();

		//step 2
		orderForm.setStep2(title,instruction);
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


	@Test(enabled = false, description = "Order form price display correct")
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

		//set value step1
		String type = "writing";
		String document = "Admission Essay";
		int acalevelNumb = 2;
		String acalevelTXT = orderForm.academicLevel.get(acalevelNumb).replace("\"","");
		String discipline = "Accounting";
		String paperFormat = Citation.getCitation(1);

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
//		driver.get("https://writersperhour.dev/order/" + orderID + "/details");
		detailsPage.verifyh1(orderID, "writing");
		detailsPage.verifyWPrice(detailsPage.writerPrice, expectedWriterPriceTXT);
		detailsPage.verifyWPrice(detailsPage.preWriterPrice, expectedPreWriterTXT);
		detailsPage.verifyWPrice(detailsPage.abstractPrice, expectedAbstractPriceTXT);
		detailsPage.verifyWPrice(detailsPage.DicountPrice, expectedDiscountTXT);
		detailsPage.verifyWPrice(detailsPage.PaidPrice, expectedYouPay);
		detailsPage.verifyWPrice(detailsPage.YouSavedPrice, expectedDiscountTXT);
		screenShot("Order detail");


	}

	@Test(enabled = true)
	public void test() throws IOException, AWTException {

		SignInPage signInPage = new SignInPage(driver);
		OrderFormPage orderForm = new OrderFormPage(driver);
		CreditCardPage creditCardPage = new CreditCardPage(driver);
		DetailsPage detailsPage = new DetailsPage(driver);
		Calculator calculator = new Calculator();

		//set value
		String type = "writing";
		String document = "Admission Essay";
		int acalevelNumb = 2;
		String acalevelTXT = orderForm.academicLevel.get(acalevelNumb).replace("\"","");
		String discipline = "Accounting";
		int paperFormat = 2;

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
		String spacing = "double";
		//step4
		boolean absPrice = true;
		boolean preWriter = true;

		Authenticate("WPH");

		String tokenName = "token";
		String email = Constants.emailAccount;
		String password = Constants.passAccount;
		String tokenValue = SignIn.getToken(email,password);
//		System.out.println("token: "+ tokenValue);
		signInPage.signInWithToken(tokenName, tokenValue);
		String orderID = "88852";

		String writerLevelTXT = orderForm.writerLevel.get(writerLevelNumb).replace("\"","");
		double expectedTotalNumb = calculator.PagePrice(type,deadlineTXT,acalevelTXT,pages,slides,spacing);
		String expectedTotalTXT = "$"+expectedTotalNumb;

		double expectedDiscountNumb = calculator.Discount(15);
		String expectedDiscountTXT = "$"+expectedDiscountNumb;
//		orderForm.verifyDiscount(expectedDiscountTXT);

		System.out.println("writerLevelTXT: "+writerLevelTXT);
		double expectedWriterPriceNumb = calculator.WriterLevelPrice(writerLevelTXT);
		String expectedWriterPriceTXT = "$"+expectedWriterPriceNumb;

		double expectedPreWriterNumb = calculator.preWriter(preWriter);
		String expectedPreWriterTXT = "$"+expectedPreWriterNumb;

		double expectedAbstractPriceNumb = calculator.abstractPrice(absPrice);
		String expectedAbstractPriceTXT = "$"+formatPrice(expectedAbstractPriceNumb);

		String expectedExtraTXT = "$"+calculator.ExtrasTotal();
//		orderForm.verifyExtra(expectedExtraTXT);

		String expectedYouPay = "$"+ calculator.GrandTotal();
//		orderForm.verifyYouPay(expectedYouPay);


		driver.get("https://writersperhour.dev/order/" + orderID + "/details");
		detailsPage.verifyh1(orderID, "writing");
		detailsPage.verifyWPrice(detailsPage.writerPrice, expectedWriterPriceTXT);
		detailsPage.verifyWPrice(detailsPage.preWriterPrice, expectedPreWriterTXT);
		detailsPage.verifyWPrice(detailsPage.abstractPrice, expectedAbstractPriceTXT);
		detailsPage.verifyWPrice(detailsPage.DicountPrice, expectedDiscountTXT);
		detailsPage.verifyWPrice(detailsPage.PaidPrice, expectedYouPay);
		detailsPage.verifyWPrice(detailsPage.YouSavedPrice, expectedDiscountTXT);

		//Check Dashboard
		DashBoard.SignIn.pages.SignInPage signInPageDB = new DashBoard.SignIn.pages.SignInPage(driver);
		DashBoard.OrderDetail.pages.OrderDetailPage orderDetailDB = new OrderDetailPage(driver);
		Authenticate("DashBoard");
		signInPageDB.Login(Constants.email, Constants.passAccount);
		sleep(5);
		driver.get(Support.DashBoard.Routers.ORDERS_DETAILS+ orderID);
		orderDetailDB.verifyTopic(title);
		orderDetailDB.verifyDis(discipline);
		orderDetailDB.verifyDoc(document);
		orderDetailDB.verifySpacing(spacing);
//		orderDetailDB.verifyFormat(f);
		orderDetailDB.verifyDoc(document);
		orderDetailDB.verifyDoc(document);


	}

	@Test(enabled = false)
	public void simpletest(){
		SignInPage signInPage = new SignInPage(driver);
		OrderFormPage orderForm = new OrderFormPage(driver);
		CreditCardPage creditCardPage = new CreditCardPage(driver);
		DetailsPage detailsPage = new DetailsPage(driver);
		Calculator calculator = new Calculator();

		//set value
		String type = "writing";
		String document = "Admission Essay";
		int acalevelNumb = 2;
		String acalevelTXT = orderForm.academicLevel.get(acalevelNumb).replace("\"","");
		String discipline = "Accounting";
		String paperFormat = Citation.getCitation(5);
		System.out.println(paperFormat);
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
		String spacing = "double";
		//step4
		boolean absPrice = true;
		boolean preWriter = true;

		Authenticate("WPH");

		String tokenName = "token";
		String email = Constants.emailAccount;
		String password = Constants.passAccount;
		String tokenValue = SignIn.getToken(email,password);
//		System.out.println("token: "+ tokenValue);
		signInPage.signInWithToken(tokenName, tokenValue);
		String orderID = "88844";

		driver.get(Routers.ORDER);
		orderForm.formatOptBTN(paperFormat);
		sleep(10);
	}
}
