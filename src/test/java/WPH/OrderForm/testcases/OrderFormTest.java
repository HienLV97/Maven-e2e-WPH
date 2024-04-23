package WPH.OrderForm.testcases;

import Support.Constants;
import Support.Initialization.Init;
import Support.Routers;
import WPH.OrderForm.pages.OrderFormPage;
import WPH.SignIn.pages.SignInPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

//import static Support.Initialization.Init.driver;

public class OrderFormTest extends Init {

	@Test(description = "test")
	public void checkout() {
		Authenticate();
		OrderFormPage orderForm = new OrderFormPage(driver);
		SignInPage signInPage = new SignInPage(driver);

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

//		orderForm.setTitleTXT("test");
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
		orderForm.clickNextButton();

		//step5
		orderForm.clickCheckOutBTN();
		waitForPageLoaded();
		sleep(5);
		orderForm.clickViewOrderBTN();

		String url = driver.getCurrentUrl();
		System.out.println(url);
		driver.get(url);
		waitForPageLoaded();
		orderForm.clickViewOrderBTN();
		closeBrowser();
	}

//	@AfterTest
//	public void closeBrowser() {
//		closeBrowser();
//	}
}
