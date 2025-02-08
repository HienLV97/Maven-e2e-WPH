package WPH.OrderForm.testcases;

import API.GetAPI.NextProxy.Citation.Citation;
import API.GetAPI.NextProxy.SignIn.SignIn;
import AcaWriting.Support.Writer.Routers;
import helpers.drivers.DriverManager;
import Calculator.Calculator;
import AcaWriting.Keywords.WebUI;
import helpers.dataProvider.DataProviderFactory;
import helpers.Constants;
import Initialization.Init;
import WPH.OrderDetails.Details.pages.DetailsPage;
import WPH.OrderForm.pages.OrderFormPage;
import WPH.SignIn.pages.SignInPage;
import WPH.payment.CreditCard.pages.CreditCardPage;
import logs.LogUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Hashtable;

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
//		WebUI.waitForPageLoaded();
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
////		WebUI.waitForPageLoaded();
////
////		creditCardPage.getCheckout();
////
////		sleep(5);
////		waitForNavigatePage(null);
////		WebUI.waitForPageLoaded();
////		String orderID = orderForm.getID();
////		orderForm.clickViewOrderBTN();
////
////		sleep(10);
////		WebUI.waitForPageLoaded();
////		detailsPage.verifyh1(orderID, "writing");
//	}
	String order_ID;
	DetailsPage detailsPage;

	@Test(groups = {"1"})
	public void checkoutSuccess() throws IOException, AWTException {
		OrderFormPage orderForm = new OrderFormPage(DriverManager.getDriver());
		SignInPage signInPage = new SignInPage(DriverManager.getDriver());
		DetailsPage detailsPage = new DetailsPage(DriverManager.getDriver());

		authenticate("WPH");
		signInPage.Login(Constants.EMAIL, Constants.COMMON_PASSWORD);
		screenShot("TestScreen3");

		DriverManager.getDriver().get(AcaWriting.Support.WPH.Routers.ORDER);
		waitForNavigatePage(AcaWriting.Support.WPH.Routers.ORDER);

		orderForm.Step1Data("writing", "Admission Essay", 2, "Accounting", Citation.getCitation(0), orderForm.academicLevel);
		orderForm.Step2Data("test", "test");
		orderForm.Step3Data(1, 3, 3, 2, "Single", orderForm.deadLineLevel);
		orderForm.Step4Data(1, false, true, orderForm.writerLevel);
		orderForm.Step5Data("paper15", 1);


		sleep(5);
		waitForNavigatePage("NaN");
		WebUI.waitForPageLoaded();
		String orderID = orderForm.getID();
		orderForm.clickViewOrderBTN();

		// orderDetail.xml
		sleep(2);
		WebUI.waitForPageLoaded();
		detailsPage.verifyh1(orderID, "writing");
		detailsPage.verifyWPrice(detailsPage.writerPrice, "$152.92");
		detailsPage.verifyWPrice(detailsPage.preWriterPrice, "$21.85");
		detailsPage.verifyWPrice(detailsPage.abstractPrice, "$22.00");
		detailsPage.verifyWPrice(detailsPage.DicountPrice, "$65.54");
		detailsPage.verifyWPrice(detailsPage.PaidPrice, "$568.11");
		detailsPage.verifyWPrice(detailsPage.YouSavedPrice, "$65.54");
	}


	//	@Test(enabled = true,groups = {"verifyPrice"}, priority = 1, description = "Order form price display correct")
	@Test()
	public void testVerifyPrice() throws IOException, AWTException {

		SignInPage signInPage = new SignInPage(DriverManager.getDriver());
		OrderFormPage orderForm = new OrderFormPage(DriverManager.getDriver());
		CreditCardPage creditCardPage = new CreditCardPage(DriverManager.getDriver());


		orderForm.Step1Data("writing", "Admission Essay", 2, "Accounting", Citation.getCitation(0), orderForm.academicLevel);
		orderForm.Step2Data("test", "test");
		orderForm.Step3Data(3, 2, 2, 0, "Double", orderForm.deadLineLevel);
		orderForm.Step4Data(1, true, true, orderForm.writerLevel);
		orderForm.Step5Data("paper15", 1);
		Calculator calculator = new Calculator();

		authenticate("WPH");
		String tokenName = "token";
		String email = Constants.EMAIL;
		String password = Constants.COMMON_PASSWORD;
		String tokenValue = SignIn.getToken(email, password);
		signInPage.signInWithToken( tokenValue);
		calculator.balance(tokenValue);

		sleep(5);
		DriverManager.getDriver().get(AcaWriting.Support.WPH.Routers.ORDER);


		orderForm.setStep1();
		orderForm.setStep2();
		orderForm.setStep3();
		orderForm.setStep4();
		orderForm.setDiscountTB("paper15");
		orderForm.clickApply();

		sleep(10);

		calculator.setValuesFromOrderForm(orderForm);

		orderForm.verifyTotal("$" + calculator.getGetPagePrice());

		orderForm.verifyDiscount("$" + Calculator.getDiscountRound());

		orderForm.verifyExtra("$" + calculator.getExtraTotalRound());

		String totalPayTxt = "$" + Calculator.getGrandTotal();
		orderForm.verifyYouPay(totalPayTxt);

		orderForm.clickCreditBTN();
		orderForm.clickCheckOutBTN();

		detailsPage = creditCardPage.getCheckout();

		sleep(5);
		waitForNavigatePage("NaN");
		WebUI.waitForPageLoaded();
		sleep(3);
		String orderID = orderForm.getID();
		this.order_ID = orderID;
		detailsPage.setValuesFromOrderForm(orderForm);
		orderForm.clickViewOrderBTN();

		// orderDetail.xml

		sleep(5);
		WebUI.waitForPageLoaded();
		detailsPage.verifyh1(orderID, "writing");
		detailsPage.verifyPriceDetails();
		screenShot("Order detail");
	}

	@Test(description = "Create multi orders", dataProvider = "dataLogin" ,dataProviderClass = DataProviderFactory.class)
	@Parameters("orderQuantity")
	public void createMultiOrders(Hashtable<String, String> data) {
		sleep(5);
		int orderQuantity = Integer.parseInt(data.get("ORDER_QUANTITY"));
		for (int i = 1; i < orderQuantity; i++) {
			createOneOrder(data.get("EMAIL"), data.get("PASSWORD"));
			sleep(3);
			LogUtils.info("order number: "+ i);
		}

	}

	@Test(description = "Create 1 order")
	@Parameters({"email","password"})
	public void createOneOrder(String email,String password)  {
		SignInPage signInPage = new SignInPage(DriverManager.getDriver());
		OrderFormPage orderForm = new OrderFormPage(DriverManager.getDriver());

		authenticate("wph");

		String tokenValue = SignIn.getToken(email, password);
		signInPage.signInWithToken(tokenValue);

		orderForm.Step1Data("writing", "Admission Essay", 2, "Accounting", Citation.getCitation(0), orderForm.academicLevel);
		orderForm.Step2Data("test", "test");
		orderForm.Step3Data(3, 2, 2, 0, "Double", orderForm.deadLineLevel);
		orderForm.Step4Data(1, true, false, orderForm.writerLevel);
		orderForm.Step5Data("paper15", 1);

		sleep(5);
		orderForm.createOrder();
		sleep(3);

//		orderForm.clickCreditBTN();
//		orderForm.clickCheckOutBTN();

		sleep(5);
		waitForNavigatePage("NaN");
		WebUI.waitForPageLoaded();
		sleep(3);
	}

	@Test(priority = 3, description = "Writer site")
	public void testWriterSite() {
		Writer.SignIn.pages.SignInPage signInWriter = new Writer.SignIn.pages.SignInPage(DriverManager.getDriver());
		OrderFormPage orderForm = new OrderFormPage(DriverManager.getDriver());

		//set value step1
		String orderType = "writing";
		String document = "Admission Essay";
		int acalevelNumb = 2;
		String acalevelTXT = orderForm.academicLevel.get(acalevelNumb).replace("\"", "");
		String discipline = "Accounting";
		String paperFormat = Citation.getCitation(0);

		//set value step2
		String title = "test";
		String instruction = "test";
		//set value step3
		int deadlineNumb = 3;
		String urgentTXT = orderForm.deadLineLevel.get(deadlineNumb).replace("\"", "");
		int pages = 2;
		int source = 2;
		int slides = 0;
		int writerLevelNumb = 2;
		String spacing = "SINGLE";
		//set value step4
		boolean isAbsPrice = false;
		boolean isPreWriter = true;
		//set value step5
		String disCode = "paper15";
		Calculator calculator = new Calculator();

		Writer.OrderDetail.pages.OrderDetailPage orderDetailWriter = new Writer.OrderDetail.pages.OrderDetailPage(
				DriverManager.getDriver(), orderType, acalevelTXT, document, discipline, paperFormat,
				title, instruction, urgentTXT, source, pages, slides, spacing);

		authenticate("Writer");
		WebUI.waitForPageLoaded();
		signInWriter.login(Constants.WRITER_EMAIL, Constants.COMMON_PASSWORD);
		waitForNavigatePage(Routers.BaseURL);
		String ID_ORDER = order_ID;
		String PRE_ORDER = "91172";
		sleep(5);
		orderDetailWriter.goToOD(ID_ORDER);
		sleep(10);
//		orderDetailWriter.verifyPreWriter(PRE_ORDER);
		orderDetailWriter.verifyAbsPage(false);
		orderDetailWriter.verifyOrderID(ID_ORDER);
		orderDetailWriter.verifySpacing();
		orderDetailWriter.verifyFormat();
		orderDetailWriter.verifyWord();
		orderDetailWriter.verifySize();
		orderDetailWriter.verifyIns();
	}

	@Test(description = "simple Test",groups = {"2"})
	public void simpleTest() {
		SignInPage signInPage = new SignInPage(DriverManager.getDriver());
		OrderFormPage orderForm = new OrderFormPage(DriverManager.getDriver());
		CreditCardPage creditCardPage = new CreditCardPage(DriverManager.getDriver());
		authenticate("WPH");
		signInPage.Login("t1@g.c","123123");
		orderForm.Step1Data("writing", "Admission Essay", 2, "Accounting", Citation.getCitation(0), orderForm.academicLevel);
		orderForm.Step2Data("test", "test");
		orderForm.Step3Data(3, 2, 2, 0, "Double", orderForm.deadLineLevel);
		orderForm.Step4Data(1, false, false, orderForm.writerLevel);
		orderForm.Step5Data("paper15", 1);

		orderForm.createOrder();

		detailsPage = creditCardPage.getCheckout();

	}
}
