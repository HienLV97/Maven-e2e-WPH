import Support.Initialization.Init;
import Support.WPH.Routers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import org.openqa.selenium.Keys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class testUrl extends Init {

	@Test
	public void Start() throws InterruptedException {
		driver.navigate().to(Routers.SIGN_IN);
//		driver.merge(capabilities);

//		WebElement SubmitInBTN = Constants.getBtn("Yes, I understood");
//		SubmitInBTN.click();
		Thread.sleep(1000);

		WebElement SignInBTN = driver.findElement(By.xpath("//button[contains(text(),'Continue with email')]"));
//		WebElement SignInBTN = Constants.getBtn("Sign in");

		SignInBTN.click();
		sleep(4);
//        WebElement h1Tag = driver.findElement(By.className("hero__anchor"));

//        assertEquals(h1Tag.getText(),"IB WRITING SERVICE");
//        assertTrue(h1Tag.getText().contains("IB"));
	}
	@Test(enabled = false)
	public void TestAction() {

		driver.get("https://www.google.com/");
		Actions action = new Actions(driver);

		// specify the locator of the search box
		WebElement element = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));
		// pass the product name that has to be searched in the website
		action.sendKeys(element, "Anh Tester")
				.build().perform();
		action.sendKeys(Keys.ENTER)
				.build().perform();
		driver.findElement(By.xpath("//a[@href='https://anhtester.com/']//h3[@class='LC20lb MBeuO DKV0Md'][normalize-space()='Anh Tester Automation Testing']"))
				.click();
		WebElement title = driver.findElement(By.xpath("//div[@class='col-lg-7']//h2[@class='section__title'][contains(text(),'Anh Tester')]"));
		action.doubleClick(title).build().perform();

		WebElement allCourseBTN = driver.findElement(By.xpath("//a[contains(text(),'Tất Cả Khóa Học')]"));
		action.moveToElement(allCourseBTN).build().perform();
	}

	@Test(enabled = false)
	public void DrapAndDrop() throws AWTException {
		Actions action = new Actions(driver);
		Robot robot = new Robot();
		driver.get("http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");
		WebElement From = driver.findElement(By.xpath("//div[@id='box3']"));
		WebElement To = driver.findElement(By.xpath("//div[@id='box103']"));
		action.dragAndDrop(From, To).build().perform();
	}

	@Test(enabled = false)
	public void Robot() throws AWTException {
		Actions action = new Actions(driver);
		driver.get("https://anhtester.com");

		sleep(5);
		WebElement inputCourseElement = driver.findElement(By.name("key"));
		inputCourseElement.click();
		inputCourseElement.sendKeys("a");
		action.keyDown(inputCourseElement, Keys.SHIFT).sendKeys("hihi").build().perform();

		Robot robot = new Robot();
		sleep(5);

		// Nhập từ khóa selenium
		inputCourseElement.click();
		sleep(2);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyPress(KeyEvent.VK_L);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyPress(KeyEvent.VK_I);
		robot.keyPress(KeyEvent.VK_U);
		robot.keyRelease(KeyEvent.VK_M);
		sleep(1);
		robot.keyPress(KeyEvent.VK_ENTER);
		sleep(2);
		action.keyDown(Keys.CONTROL)
				.sendKeys("p")
				.keyUp(Keys.CONTROL)
				.keyUp("p")
				.build().perform();
		sleep(2);
	}
	@Test(enabled = false)
	public void mouseMove() throws AWTException {
		Actions action = new Actions(driver);
		driver.get("https://anhtester.com");

		sleep(5);
		Robot robot = new Robot();
		robot.mouseMove(1440+2100,-300);
		robot.delay(1000);
		robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
		sleep(1);
		robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
	}
	@Test(enabled = true)
	public void screenShot() throws AWTException, IOException {
		Actions action = new Actions(driver);
		driver.get("https://anhtester.com");

		sleep(5);
		Robot robot = new Robot();
//       Left screen
		int XScreen2 = -1440 ;
		int YScreen2 = -338 ;
		int WidthScreen =1440;
		int HeightScreen = 2560;
		Rectangle screenRectangle = new Rectangle( XScreen2,YScreen2,WidthScreen,HeightScreen);
//        Rectangle screenRectangle = new Rectangle();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		File file = new File("TestPC-"+XScreen2+"-"+YScreen2+"-"+WidthScreen+"-"+HeightScreen+".png");
		ImageIO.write(image,"png",file);

	}
}
