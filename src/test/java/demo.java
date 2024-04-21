import API.GetAPI.Dashboard.OrderForm.OrderForm;
import Support.Initialization.Init;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.AddHasCasting;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

//import static Support.Initialization.Init.driver;

public class demo extends Init {

	@Test
	public void t()  {
//		String filePathAcademicLevel = OrderForm.filePath(OrderForm.academicLevel);
		List<String> academicLevel = OrderForm.handleData(OrderForm.academicLevel);
		System.out.println(academicLevel);
	}
}
