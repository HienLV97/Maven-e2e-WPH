import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

public class test {
	@Test (description= "Print the first test method")
	public void nevigation(){
		driver.navigate().to(Routers.HOME);
		WebElement signInBtn = Constants.getBtn("Sign in");
		signInBtn.click();
		System.out.println("a'");
	}
}
