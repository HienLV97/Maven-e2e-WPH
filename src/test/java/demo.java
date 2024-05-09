import Support.Initialization.Init;
import Support.Routers;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

//import static Support.Initialization.Init.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class demo extends Init {

	@Test
	public void t()  {
		Authenticate();
		WebElement button = driver.findElement(By.xpath("//a[contains(text(),'Order Now')]")); // Thay bằng ID của một phần tử

	}
}
