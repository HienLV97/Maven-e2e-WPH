package WPH.OrderForm.testcases;

import Locator.OrderForm;
import Support.Constants;
import Support.Initialization.Init;
import Support.Routers;
import WPH.OrderForm.pages.OrderFormPage;
import WPH.SignIn.pages.SignInPage;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

//import static Support.Initialization.Init.driver;

public class OrderFormTest extends Init {

	@Test(description = "bug cre")
	public void checkout() {
		Authenticate();
		OrderFormPage orderForm = new OrderFormPage(driver);
		SignInPage signInPage = new SignInPage(driver);

		signInPage.Login(Constants.emailAccount,Constants.passAccount);
		sleep(4);
		// Order form step1
		driver.get(Routers.ORDER);
		orderForm.DocumentDRL();

		sleep(2);
		orderForm.AcalevelOptBTN(2);

		sleep(2);
		driver.findElement(By.xpath("//input[@id='input-discipline']")).sendKeys("Accounting");
		orderForm.AcalevelOptBTN(2);
		orderForm.clickLoginButton();


	}



}
