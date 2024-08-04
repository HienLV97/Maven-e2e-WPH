package WPH.OrderForm.testcases;

import API.GetAPI.DashboardGraphQL.Price;
import API.GetAPI.NextProxy.Citation.Citation;
import API.GetAPI.NextProxy.SignIn.SignIn;
import Calculator.Calculator;
import DashBoard.OrderDetail.pages.OrderDetailPage;
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
	String order_ID;
	DetailsPage detailsPage;
	@Test(enabled = false)
	public void checkoutSuccess() throws IOException, AWTException {
		OrderFormPage orderForm = new OrderFormPage(driver);
		CreditCardPage creditCardPage = new CreditCardPage(driver);
		SignInPage signInPage = new SignInPage(driver);
		DetailsPage detailsPage = new DetailsPage(driver);

		authenticate("WPH");
		signInPage.Login(Constants.EMAIL, Constants.COMMON_PASSWORD);
		screenShot("TestScreen3");

		driver.get(Routers.ORDER);
		waitForNavigatePage(Routers.ORDER);

		orderForm.Step1Data("writing", "Admission Essay", 2, "Accounting", Citation.getCitation(0), orderForm.academicLevel);
		orderForm.Step2Data("test", "test");
		orderForm.Step3Data(1, 3, 3, 2,  "Single", orderForm.deadLineLevel);
		orderForm.Step4Data(1, false, true, orderForm.writerLevel);
		orderForm.Step5Data("paper15", 1);


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


//	@Test(enabled = true,groups = {"verifyPrice"}, priority = 1, description = "Order form price display correct")
	@Test(groups = {"verifyPrice"})
	public void testVerifyPrice() throws IOException, AWTException {

		SignInPage signInPage = new SignInPage(driver);
		OrderFormPage orderForm = new OrderFormPage(driver);
		CreditCardPage creditCardPage = new CreditCardPage(driver);


		orderForm.Step1Data("writing", "Admission Essay", 2, "Accounting", Citation.getCitation(0), orderForm.academicLevel);
		orderForm.Step2Data("test", "test");
		orderForm.Step3Data(3, 2, 2, 0, "Double", orderForm.deadLineLevel);
		orderForm.Step4Data(1, false, true, orderForm.writerLevel);
		orderForm.Step5Data("paper15", 1);
		Calculator calculator = new Calculator();
//		signInPage.Login("abc","abc");
//		driver.get(Routers.ORDER);
		authenticate("WPH");
		String tokenName = "token";
		String email = Constants.EMAIL;
		String password = Constants.COMMON_PASSWORD;
		String tokenValue = SignIn.getToken(email, password);
		signInPage.signInWithToken(tokenName, tokenValue);
		calculator.balance(tokenValue);

		sleep(5);
		driver.get(Routers.ORDER);


		orderForm.setStep1();
		orderForm.setStep2();
		orderForm.setStep3();
		orderForm.setStep4();
		orderForm.setDiscountTB("paper15");
		orderForm.clickApply();

		sleep(10);

		calculator.setValuesFromOrderForm(orderForm);
		double pagePrice = calculator.pagePrice();
		String pagePriceTxt = "$" + pagePrice;
		orderForm.verifyTotal(pagePriceTxt);

		double discount = calculator.discount(15);
		String discountTxt = "$" + discount;
		orderForm.verifyDiscount(discountTxt);

		System.out.println("writerLvlTxt : " + orderForm.writerLvlTxt);
		double writerPrice = calculator.writerLevelPrice(orderForm.writerLvlTxt);
		String writerPriceTxt = "$" + writerPrice;

		double preWriterPrice = calculator.preWriter();
		String preWriterTxt = "$" + preWriterPrice;

		double absPrice = calculator.abstractPrice();
		String absPriceTxt = "$" + formatPrice(absPrice);

		orderForm.verifyExtra("$" + calculator.extraTotalRound);

		String totalPayTxt = "$" + calculator.grandTotal();
		orderForm.verifyYouPay(totalPayTxt);

		detailsPage = creditCardPage.getCheckout();

		sleep(5);
		waitForNavigatePage("NaN");
		waitForPageLoaded();
		sleep(3);
		String orderID = orderForm.getID();
		this.order_ID = orderID;
		orderForm.clickViewOrderBTN();

		// orderDetail

		sleep(5);
		waitForPageLoaded();
//		driver.get("https://writersperhour.dev/order/" + orderID + "/details");
		detailsPage.verifyh1(orderID, "writing");
		detailsPage.verifyWPrice(detailsPage.writerPrice, writerPriceTxt);
		detailsPage.verifyWPrice(detailsPage.preWriterPrice, preWriterTxt);
		detailsPage.verifyWPrice(detailsPage.abstractPrice, absPriceTxt);
		detailsPage.verifyWPrice(detailsPage.DicountPrice, discountTxt);
		detailsPage.verifyWPrice(detailsPage.PaidPrice, totalPayTxt);
		detailsPage.verifyWPrice(detailsPage.YouSavedPrice, discountTxt);
		screenShot("Order detail");


	}

	@Test(enabled = true, priority = 2)
	public void test() throws IOException, AWTException {

		SignInPage signInPage = new SignInPage(driver);
		OrderFormPage orderForm = new OrderFormPage(driver);
		CreditCardPage creditCardPage = new CreditCardPage(driver);
		DetailsPage detailsPage = new DetailsPage(driver);
		//set value step1
		String orderType = "writing";
		String document = "Admission Essay";
		int acalevelNumb = 2;
		String acalevelTXT = orderForm.academicLevel.get(acalevelNumb).replace("\"", "");
		String discipline = "Accounting";
		String paperFormat = Citation.getCitation(0);

		//step2
		String title = "test";
		String instruction = "test";
		//step 3
		int deadlineNumb = 3;
		String deadlineTXT = orderForm.deadLineLevel.get(deadlineNumb).replace("\"", "");
		int pages = 2;
		int source = 2;
		int slides = 2;
		int writerLevelNumb = 2;
		String spacing = "Single";
		//step4
		boolean isAbsPrice = true;
		boolean isPreWriter = true;
		String idPreOrder = "#00091172";
		//step5
		String disCode = "paper15";
		String costPageText = "$" + Price.GetPrice(deadlineTXT, acalevelTXT);

		Calculator calculator = new Calculator();

		authenticate("WPH");

		String tokenName = "token";
		String email = Constants.EMAIL;
		String password = Constants.COMMON_PASSWORD;
		String tokenValue = SignIn.getToken(email, password);
//		System.out.println("token: "+ tokenValue);
		signInPage.signInWithToken(tokenName, tokenValue);
//		String orderID = "91254";
		String orderID = order_ID;

		String writerLvlTxt = orderForm.writerLevel.get(writerLevelNumb).replace("\"", "");
		double pagePrice = calculator.pagePrice();
		String pagePriceTxt = "$" + pagePrice;

		double discount = calculator.discount(15);
		String discountTxt = "$" + discount;
//		orderForm.verifyDiscount(discountTxt);

		System.out.println("writerLvlTxt : " + writerLvlTxt);
		double writerPrice = calculator.writerLevelPrice(writerLvlTxt);
		String writerPriceTxt = "$" + writerPrice;

		double preWriterPrice = calculator.preWriter();
		String preWriterTxt = "$" + preWriterPrice;

		double absPriceVal = calculator.abstractPrice();
		String absPriceTxt = "$" + formatPrice(absPriceVal);

		String extrasTxt = "$" + calculator.extraTotal;
//		orderForm.verifyExtra(extrasTxt
//	);

		String totalPayTxt = "$" + calculator.grandTotal();
//		orderForm.verifyYouPay(totalPayTxt
//	);


		driver.get("https://writersperhour.dev/order/" + orderID + "/details");
		detailsPage.verifyh1(orderID, "writing");
		detailsPage.verifyWPrice(detailsPage.writerPrice, writerPriceTxt);
		detailsPage.verifyWPrice(detailsPage.preWriterPrice, preWriterTxt);
		detailsPage.verifyWPrice(detailsPage.abstractPrice, absPriceTxt);
		detailsPage.verifyWPrice(detailsPage.DicountPrice, discountTxt);
		detailsPage.verifyWPrice(detailsPage.PaidPrice, totalPayTxt);
		detailsPage.verifyWPrice(detailsPage.YouSavedPrice, discountTxt);


		//Check Dashboard
		DashBoard.SignIn.pages.SignInPage signInPageDB = new DashBoard.SignIn.pages.SignInPage(driver);
		DashBoard.OrderDetail.pages.OrderDetailPage orderDetailDB = new OrderDetailPage(driver);
		authenticate("DashBoard");
		signInPageDB.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		sleep(5);
		sleep(60);
		driver.get(Support.DashBoard.Routers.ORDERS_DETAILS + orderID);
		//DETAIL
		orderDetailDB.verifyTopic(title);
		orderDetailDB.verifyDis(discipline);
		orderDetailDB.verifyDoc(document);
		orderDetailDB.verifySpacing(spacing);
		orderDetailDB.verifyFormat(paperFormat);
		orderDetailDB.verifyReferences(String.valueOf(source));
		orderDetailDB.verifyPages(String.valueOf(pages));
		orderDetailDB.verifyWPP(spacing);
		orderDetailDB.verifyUrgency(deadlineTXT);
		//ORDER COST
		orderDetailDB.verifyPerPage(costPageText);
		orderDetailDB.verifyCostPages(String.valueOf(pages));
		String slideCost = "$" + (String.format("%.2f", calculator.slideCost()));
		orderDetailDB.verifyPerSlide(slideCost);
		orderDetailDB.verifySilde(String.valueOf(slides));
//		orderDetailDB.verifyTotal(pagePriceTxt);
		orderDetailDB.verifyAdd(extrasTxt);
		orderDetailDB.verifyPaid(totalPayTxt);
		orderDetailDB.verifyRate();
		orderDetailDB.verifyWriterFee();
		//INSTRUCTIONS
		orderDetailDB.verifyIns(instruction);
		//DISCOUNT
		orderDetailDB.verifyCode(disCode);
		orderDetailDB.verifyPercent();
		//EXTRAS
		orderDetailDB.verifywriterCate(writerLvlTxt);
		orderDetailDB.verifyPreWriter(idPreOrder);
		orderDetailDB.verifyAbsPrice(calculator.absPrice);
		//ORDER EVENT

		orderDetailDB.clickAppBTN();
		orderDetailDB.setAssDDL(Constants.WRITER_EMAIL);
		orderDetailDB.clickSaveBTN();
		sleep(20);
		//WRITER SITE
//		Writer.SignIn.pages.SignInPage writerSignIn = new Writer.SignIn.pages.SignInPage(driver);
//		Authenticate("Writer");
//		writerSignIn.login(Constants.WRITER_EMAIL, Constants.COMMON_PASSWORD);

	}

	@Test(enabled = false, description = "Create multi orders")
	public void simpletest() {
		SignInPage signInPage = new SignInPage(driver);
		OrderFormPage orderForm = new OrderFormPage(driver);
		CreditCardPage creditCardPage = new CreditCardPage(driver);
		DetailsPage detailsPage = new DetailsPage(driver);

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
		String deadlineTXT = orderForm.deadLineLevel.get(deadlineNumb).replace("\"", "");
		int pages = 2;
		int source = 2;
		int slides = 2;
		int writerLevelNumb = 2;
		String spacing = "Single";
		//set value step4
		boolean isAbsPrice = true;
		boolean isPreWriter = true;
		//set value step5
		String disCode = "paper15";

		String writerLvlTxt = orderForm.writerLevel.get(writerLevelNumb).replace("\"", "");

		Calculator calculator = new Calculator();

		authenticate("WPH");
		String tokenName = "token";
		String email = Constants.EMAIL;
		String password = Constants.COMMON_PASSWORD;
		String tokenValue = SignIn.getToken(email, password);
		signInPage.signInWithToken(tokenName, tokenValue);
		calculator.balance(tokenValue);

		sleep(5);
		for (int i = 1; i < 20; i++) {
			driver.get(Routers.ORDER);
			createOneOrder();

			sleep(3);
			System.out.println(orderType + "   " + deadlineTXT + "   " + acalevelTXT + "   " + pages + "   " + slides + "   " + spacing);

			orderForm.clickCreditBTN();
			orderForm.clickCheckOutBTN();

			sleep(5);
			waitForNavigatePage("NaN");
			waitForPageLoaded();
			orderForm.clickViewOrderBTN();

		}

	}

	@Test(enabled = false, description = "Create 1 order")
	public void createOneOrder() {
		SignInPage signInPage = new SignInPage(driver);
		OrderFormPage orderForm = new OrderFormPage(driver);
		CreditCardPage creditCardPage = new CreditCardPage(driver);
		DetailsPage detailsPage = new DetailsPage(driver);



		Calculator calculator = new Calculator();

		authenticate("WPH");
		String tokenName = "token";
		String email = Constants.EMAIL;
		String password = Constants.COMMON_PASSWORD;
		String tokenValue = SignIn.getToken(email, password);
		signInPage.signInWithToken(tokenName, tokenValue);
		calculator.balance(tokenValue);

		driver.get(Routers.ORDER);
		orderForm.createOrder();
		orderForm.clickCreditBTN();
		orderForm.clickCheckOutBTN();

		sleep(5);
		waitForNavigatePage("NaN");
		waitForPageLoaded();
		orderForm.clickViewOrderBTN();

	}

	@Test(enabled = true, priority = 3, description = "Writer site")
	public void testWriterSite() {
		Writer.SignIn.pages.SignInPage signInWriter = new Writer.SignIn.pages.SignInPage(driver);
		OrderFormPage orderForm = new OrderFormPage(driver);

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
				driver, orderType, acalevelTXT, document, discipline, paperFormat,
				title, instruction, urgentTXT, source, pages, slides, spacing);

		authenticate("Writer");
		waitForPageLoaded();
		signInWriter.login(Constants.WRITER_EMAIL, Constants.COMMON_PASSWORD);
		waitForNavigatePage(Support.Writer.Routers.BaseURL);
		String ID_ORDER =order_ID;
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

	@Test(enabled = false, description = "simple Test")
	public void simpleTest() {
		SignInPage signInPage = new SignInPage(driver);
		OrderFormPage orderForm = new OrderFormPage(driver);
		CreditCardPage creditCardPage = new CreditCardPage(driver);

		orderForm.Step1Data("writing", "Admission Essay", 2, "Accounting", Citation.getCitation(0), orderForm.academicLevel);
		orderForm.Step2Data("test", "test");
		orderForm.Step3Data(3, 2, 2, 0, "Double", orderForm.deadLineLevel);
		orderForm.Step4Data(1, false, true, orderForm.writerLevel);
		orderForm.Step5Data("paper15", 1);
		orderForm.createOrder();

		detailsPage = creditCardPage.getCheckout();

	}
}
