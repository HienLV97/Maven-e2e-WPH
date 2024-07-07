package WPH.OrderForm.testcases;

import API.GetAPI.DashboardGraphQL.Price;
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
		signInPage.Login(Constants.EMAIL, Constants.COMMON_PASSWORD);
		screenShot("TestScreen3");

		driver.get(Routers.ORDER);
		waitForNavigatePage(Routers.ORDER);

		//set value
		String type = "writing";
		String document = "Admission Essay";
		int acalevelNumb = 2;
		String acalevelTXT = orderForm.academicLevel.get(acalevelNumb).replace("\"", "");
		String discipline = "Accounting";
		int paperFormat = 2;
//		String paperFormatTXT = orderForm.p
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
		String spacing = "Double";
		//step4
		boolean isAbsPrice = true;
		boolean isPreWriter = true;
		//step 5
		String disCode = "paper15";

		//step1
		orderForm.setStep1(document, acalevelNumb, discipline);
		orderForm.clickNextButton();

		//step 2
		orderForm.setStep2(title, instruction);
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
		orderForm.setDiscountTB(disCode);
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

		//set value step1
		String type = "writing";
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

		Calculator calculator = new Calculator(type, deadlineTXT, acalevelTXT, pages, slides, spacing);

		Authenticate("WPH");
		String tokenName = "token";
		String email = Constants.EMAIL;
		String password = Constants.COMMON_PASSWORD;
		String tokenValue = SignIn.getToken(email, password);
		signInPage.signInWithToken(tokenName, tokenValue);
		calculator.balance(tokenValue);

		sleep(5);
		driver.get(Routers.ORDER);


		orderForm.setStep1(document, acalevelNumb, discipline);
		orderForm.setStep2(title, instruction);
		orderForm.setStep3(source, pages, deadlineNumb, slides, spacing);
		orderForm.setStep4(writerLevelNumb);
		orderForm.setDiscountTB(disCode);
		orderForm.clickApply();
		sleep(3);
		System.out.println(type + "   " + deadlineTXT + "   " + acalevelTXT + "   " + pages + "   " + slides + "   " + spacing);

		double pagePrice = calculator.pagePrice();
		String pagePriceTxt = "$" + pagePrice;
		orderForm.verifyTotal(pagePriceTxt);

		double discount = calculator.discount(15);
		String discountTxt = "$" + discount;
		orderForm.verifyDiscount(discountTxt);

		System.out.println("writerLvlTxt : " + writerLvlTxt);
		double writerPrice = calculator.writerLevelPrice(writerLvlTxt);
		String writerPriceTxt = "$" + writerPrice;

		double preWriterPrice = calculator.preWriter(isPreWriter);
		String preWriterTxt = "$" + preWriterPrice;

		double absPrice = calculator.abstractPrice(isAbsPrice);
		String absPriceTxt = "$" + formatPrice(absPrice);

		String extrasTxt = "$" + calculator.extrasTotal();
//		String expectedExtra = "$79.98";

		orderForm.verifyExtra(extrasTxt);

		String totalPayTxt = "$" + calculator.grandTotal();
		orderForm.verifyYouPay(totalPayTxt);

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
		detailsPage.verifyWPrice(detailsPage.writerPrice, writerPriceTxt);
		detailsPage.verifyWPrice(detailsPage.preWriterPrice, preWriterTxt);
		detailsPage.verifyWPrice(detailsPage.abstractPrice, absPriceTxt);
		detailsPage.verifyWPrice(detailsPage.DicountPrice, discountTxt);
		detailsPage.verifyWPrice(detailsPage.PaidPrice, totalPayTxt);
		detailsPage.verifyWPrice(detailsPage.YouSavedPrice, discountTxt);
		screenShot("Order detail");


	}

	@Test(enabled = true)
	public void test() throws IOException, AWTException {

		SignInPage signInPage = new SignInPage(driver);
		OrderFormPage orderForm = new OrderFormPage(driver);
		CreditCardPage creditCardPage = new CreditCardPage(driver);
		DetailsPage detailsPage = new DetailsPage(driver);
		//set value step1
		String type = "writing";
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
		//step5
		String disCode = "paper15";
		String costPageText = "$" + Price.GetPrice(deadlineTXT, acalevelTXT);

		Calculator calculator = new Calculator(type, deadlineTXT, acalevelTXT, pages, slides, spacing);

		Authenticate("WPH");

		String tokenName = "token";
		String email = Constants.EMAIL;
		String password = Constants.COMMON_PASSWORD;
		String tokenValue = SignIn.getToken(email, password);
//		System.out.println("token: "+ tokenValue);
		signInPage.signInWithToken(tokenName, tokenValue);
		String orderID = "91280";

		String writerLvlTxt = orderForm.writerLevel.get(writerLevelNumb).replace("\"", "");
		double pagePrice = calculator.pagePrice();
		String pagePriceTxt = "$" + pagePrice;

		double discount = calculator.discount(15);
		String discountTxt = "$" + discount;
//		orderForm.verifyDiscount(discountTxt);

		System.out.println("writerLvlTxt : " + writerLvlTxt);
		double writerPrice = calculator.writerLevelPrice(writerLvlTxt);
		String writerPriceTxt = "$" + writerPrice;

		double preWriterPrice = calculator.preWriter(isPreWriter);
		String preWriterTxt = "$" + preWriterPrice;

		double absPriceVal = calculator.abstractPrice(isAbsPrice);
		String absPriceTxt = "$" + formatPrice(absPriceVal);

		String extrasTxt = "$" + calculator.extrasTotal();
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
		Authenticate("DashBoard");
		signInPageDB.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		sleep(5);
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
		orderDetailDB.verifyAbsPrice();
	}

	@Test(enabled = false)
	public void simpletest() {

	}
}
