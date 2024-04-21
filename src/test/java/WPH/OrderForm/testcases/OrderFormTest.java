package WPH.OrderForm.testcases;

import Support.Constants;
import Support.Initialization.Init;
import Support.Routers;
import WPH.OrderForm.pages.OrderFormPage;
import WPH.SignIn.pages.SignInPage;
import org.testng.annotations.Test;

//import static Support.Initialization.Init.driver;

public class OrderFormTest extends Init {

	@Test(description = "bug cre")
	public void checkout() {
		Authenticate();
		OrderFormPage orderForm = new OrderFormPage(driver);
		SignInPage signInPage = new SignInPage(driver);

		signInPage.Login(Constants.emailAccount, Constants.passAccount);
		sleep(4);
		// Order form step1
		driver.get(Routers.ORDER);
		waitForPageLoaded();
		orderForm.SetDocumentDRL("Admission Essay");
		sleep(2);
		orderForm.AcalevelOptBTN(2);

		orderForm.setDisciplineDRL("Accounting");
		sleep(2);
		orderForm.AcalevelOptBTN(2);
		orderForm.clickNextButton();

		//Order form step 2

		orderForm.setTitleTXT("test");
		orderForm.setInstructionTXT("test");
		orderForm.clickNextButton();

		//Order form step3

		orderForm.clickWriterCB();
		orderForm.verifyCB();
	}


}