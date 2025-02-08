package SampleTest.RecordScreen;

import Initialization.Init;
import helpers.drivers.DriverManager;
import helpers.CaptureHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;


public class TestScreenshot extends Init {

	@Test
	public void testHomePage1(Method method) throws IOException {
//		CaptureHelper.startRecord(method.getName());

		DriverManager.getDriver().get("https://anhtester.com");
		Assert.assertEquals(DriverManager.getDriver().getTitle(), "Anh Tester Automation Testing");
	}

	@Test
	public void testHomePageFaile2(Method method) throws IOException {
//		CaptureHelper.startRecord(method.getName());

		DriverManager.getDriver().get("https://anhtester.com");
		Assert.assertEquals(DriverManager.getDriver().getTitle(), "Anh Tester Automation Test");
	}
	@Test
	public void testHomePageGenaral(){
//		CaptureHelper.startRecord(method.getName());

		DriverManager.getDriver().get("https://anhtester.com");
		CaptureHelper.captureScreenshot("hihi");
		Assert.assertEquals(DriverManager.getDriver().getTitle(), "Anh Tester Automation Test");
	}
}