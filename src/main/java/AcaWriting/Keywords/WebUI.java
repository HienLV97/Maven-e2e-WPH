package AcaWriting.Keywords;


import helpers.drivers.DriverManager;
import logs.LogUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;


public class WebUI {

	private static int TIMEOUT = 10; //Chờ đợi của WebDriverWait
	private static double STEP_TIME = 0.5; //Chờ đợi của hàm sleep
	private static int PAGE_LOAD_TIMEOUT = 40; //Chờ đợi trang load

	public static void sleep(double second) {
		try {
			Thread.sleep((long) (1000 * second));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void logConsole(Object message) {
		LogUtils.info(message);
	}

	public  static WebDriver getDriver() {
		return DriverManager.getDriver();
	}

	public static WebElement getWebElement(By by) {
		return DriverManager.getDriver().findElement(by);
	}

	public static List<WebElement> getWebElements(By by) {
		return DriverManager.getDriver().findElements(by);
	}

	public static boolean checkElementExist(By by) {
		List<WebElement> listElement = getWebElements(by);

		if (listElement.size() > 0) {
			LogUtils.info("Element " + by + " existing.");
			return true;
		} else {
			LogUtils.info("Element " + by + " NOT exist.");
			return false;
		}
	}

	public static boolean checkElementDisplayed(By by) {
		waitForElementVisible(by);
		boolean check = getWebElement(by).isDisplayed();
		return check;
	}

	public static void openURL(String url) {
		getDriver().get(url);
		logConsole("Open URL: " + url);
	}

	public static void clickElement(By by) {
		waitForElementClickable(by);
		getWebElement(by).click();
		logConsole("Click on element " + by);
	}
	public static void clickWEBElement(WebElement webElement) {
		webElement.isDisplayed();
		scrollToElement(webElement);
		webElement.click();
		logConsole("Click on element " + webElement);
	}
	public static void clickElement(By by, int second) {
		waitForElementClickable(by, second);
		getWebElement(by).click();
		logConsole("Click on element " + by + " with timeout is " + second + " (second)");
	}

	public static void setText(By by, String text) {
		waitForElementVisible(by);
		getWebElement(by).sendKeys(text);
		logConsole("Set text " + text + " on input " + by);
	}

	public static void setText(By by, String text, int second) {
		waitForElementVisible(by, second);
		getWebElement(by).sendKeys(text);
		logConsole("Set text " + text + " on input " + by + " with timeout is " + second + " (second)");
	}
	public static void setText(WebElement webElement, String text) {
		webElement.sendKeys(text);
		logConsole("Set text " + text + " on input " + webElement);
	}
	public static String getText(WebElement webElement){
		logConsole(webElement+ ": "+webElement.getText());
		return webElement.getText();
	}
	public static String getValue(WebElement webElement){
		LogUtils.info(webElement+ ": "+webElement.getAttribute("value"));
		return webElement.getAttribute("value");
	}
	public static String getElementText(By by) {
		waitForElementVisible(by);
		String text = DriverManager.getDriver().findElement(by).getText();
		logConsole("Get text of element " + by + " is: " + text);
		return text;
	}

	public static String getWebElementText(WebElement element) {
		String text = element.getText();
		logConsole("Get text of element " + element + " is: " + text);
		return text;
	}

	public static String getElementAttribute(By by, String attributeName) {
		waitForElementVisible(by);
		String value = DriverManager.getDriver().findElement(by).getAttribute(attributeName);
		logConsole("Get attribute value of element " + by + " is: " + value);
		return value;
	}

	//Chờ đợi trang load xong mới thao tác
	public static void waitForPageLoaded() {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

		// Wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) DriverManager.getDriver())
						.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		// Check JS is Ready
		boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

		// Wait Javascript until it is Ready!
		if (!jsReady) {
			LogUtils.info("Javascript is NOT Ready.");
			// Wait for Javascript to load
			try {
				wait.until(jsLoad);
			} catch (Throwable error) {
				error.printStackTrace();
				Assert.fail("FAILED. Timeout waiting for page load.");
			}
		}
	}

	public static void captureScreenImage(String imageName) {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}

		//Get size screen browser
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		LogUtils.info(screenSize);
		//Khởi tạo kích thước khung hình với kích cỡ trên
		Rectangle screenRectangle = new Rectangle(screenSize);
		//Tạo hình chụp với độ lớn khung đã tạo trên
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		//Lưu hình vào dạng file với dạng png
		File file = new File("src/test/resources/screenshots/" + imageName + ".png");
		try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	//Wait for Element

	public static void waitForElementVisible(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for the element Visible. " + by.toString());
			logConsole("Timeout waiting for the element Visible. " + by.toString());
		}
	}
	public static void waitForWebElementVisible(WebElement webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
			wait.until(ExpectedConditions.visibilityOf(webElement));
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for the element Visible. " + webElement.toString());
			logConsole("Timeout waiting for the element Visible. " + webElement.toString());
		}
	}

	public static void waitForElementVisible(By by, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for the element Visible. " + by.toString());
			logConsole("Timeout waiting for the element Visible. " + by.toString());
		}
	}

	public static void waitForElementPresent(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Throwable error) {
			Assert.fail("Element not exist. " + by.toString());
			logConsole("Element not exist. " + by.toString());
		}
	}

	public static void waitForElementPresent(By by, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Throwable error) {
			Assert.fail("Element not exist. " + by.toString());
			logConsole("Element not exist. " + by.toString());
		}
	}

	public static void waitForElementClickable(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
			wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
			logConsole("Timeout waiting for the element ready to click. " + by.toString());
		}
	}
//	public static void waitForElementClickable(By by) {
//		try {
//			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
//			wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
//		} catch (Throwable error) {
//			Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
//			logConsole("Timeout waiting for the element ready to click. " + by.toString());
//		}
//	}
	public static void waitForElementClickable(By by, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
			wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
			logConsole("Timeout waiting for the element ready to click. " + by.toString());
		}
	}

	public static void waitForElementNotVisible(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for the element NOT visible. " + by.toString());
			logConsole("Timeout waiting for the element NOT visible. " + by.toString());
		}
	}

	public static void waitForElementNotVisible(By by, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for the element NOT visible. " + by.toString());
			logConsole("Timeout waiting for the element NOT visible. " + by.toString());
		}
	}

	public static void setKey(By by, Keys key) {
		waitForPageLoaded();
		getWebElement(by).sendKeys(key);
		LogUtils.info("Set key: " + key.toString() + " on element " + by);
	}

	public static void setTextAndKey(By by, String value, Keys key) {
		waitForPageLoaded();
		getWebElement(by).sendKeys(value, key);
		LogUtils.info("Set text: " + value + " on element " + by);
	}

	public static void scrollToElement(By element) {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(element));
		LogUtils.info("Scroll to "+element);
	}

	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollToPosition(int X, int Y) {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("window.scrollTo(" + X + "," + Y + ");");
	}

	public static boolean moveToElement(By toElement) {
		try {
			Actions action = new Actions(DriverManager.getDriver());
			action.moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
			return true;
		} catch (Exception e) {
			logConsole(e.getMessage());
			return false;
		}
	}

	public static boolean moveToOffset(int X, int Y) {
		try {
			Actions action = new Actions(DriverManager.getDriver());
			action.moveByOffset(X, Y).build().perform();
			return true;
		} catch (Exception e) {
			logConsole(e.getMessage());
			return false;
		}
	}

	public static boolean hoverElement(By by) {
		try {
			Actions action = new Actions(DriverManager.getDriver());
			action.moveToElement(getWebElement(by)).perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean mouseHover(By by) {
		try {
			Actions action = new Actions(DriverManager.getDriver());
			action.moveToElement(getWebElement(by)).perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean dragAndDrop(By fromElement, By toElement) {
		try {
			Actions action = new Actions(DriverManager.getDriver());
			action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
			//action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
			return true;
		} catch (Exception e) {
			logConsole(e.getMessage());
			return false;
		}
	}

	public static boolean dragAndDropElement(By fromElement, By toElement) {
		try {
			Actions action = new Actions(DriverManager.getDriver());
			action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
			return true;
		} catch (Exception e) {
			logConsole(e.getMessage());
			return false;
		}
	}

	public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
		try {
			Actions action = new Actions(DriverManager.getDriver());
			//Tính từ vị trí click chuột đầu tiên (clickAndHold)
			action.clickAndHold(getWebElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
			return true;
		} catch (Exception e) {
			logConsole(e.getMessage());
			return false;
		}
	}

	public static boolean pressENTER() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean pressESC() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean pressF11() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_F11);
			robot.keyRelease(KeyEvent.VK_F11);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param by truyền vào đối tượng element dạng By
	 * @return Tô màu viền đỏ cho Element trên website
	 */
	public static WebElement highLightElement(By by) {
		// Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
		if (DriverManager.getDriver() instanceof JavascriptExecutor) {
			((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
			sleep(1);
		}
		return getWebElement(by);
	}

	public static boolean verifyEquals(Object actual, Object expected) {
		waitForPageLoaded();
		LogUtils.info("Verify equals: " + actual + " and " + expected);
		boolean check = actual.equals(expected);
		return check;
	}

	public static void assertEquals(Object actual, Object expected) {
		LogUtils.info("Assert equals: " + actual );
		Assert.assertEquals(actual, expected,"not equals");
	}

	public static boolean verifyContains(String actual, String expected) {
//		waitForPageLoaded();
		LogUtils.info("Verify contains: " + actual + " and " + expected);
		boolean check = actual.contains(expected);
		return check;
	}

	public static void assertContains(String actual, String expected, String message) {
//		waitForPageLoaded();
		LogUtils.info("Assert contains: " + actual + " and " + expected);
		boolean check = actual.contains(expected);
		Assert.assertTrue(check, message);
	}

	public static void clickMultiElement(By by, int value) {
		waitForElementClickable(by);
		for (int i = 0; i < value; i++) {
			clickElement(by);
		}
	}
	public static void clickMultiElement(WebElement webElement , int value) {
		for (int i = 0; i < value; i++) {
			clickWEBElement(webElement);
		}
	}
	public static void doubleClickElement(WebElement webElement) {
		// Tạo một instance của Actions class
		Actions actions = new Actions(DriverManager.getDriver());

		// Thực hiện thao tác double click
		actions.doubleClick(webElement).perform();
	}

}
