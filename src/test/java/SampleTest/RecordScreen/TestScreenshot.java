package SampleTest.RecordScreen;
import AcaWriting.Support.Initialization.Init;
import AcaWriting.drivers.DriverManager;
import helpers.CaptureHelper;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestScreenshot extends Init {

	@Test
	public void testHomePage1(Method method) throws IOException {
		CaptureHelper.startRecord(method.getName());

		DriverManager.getDriver().get("https://anhtester.com");
		Assert.assertEquals(DriverManager.getDriver().getTitle(), "Anh Tester Automation Testing");
	}

	@Test
	public void testHomePage2(Method method) throws IOException {
		CaptureHelper.startRecord(method.getName());

		DriverManager.getDriver().get("https://anhtester.com");
		Assert.assertEquals(DriverManager.getDriver().getTitle(), "Anh Tester Automation Test");
	}

	@AfterMethod
	public void takeScreenshot(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			CaptureHelper.captureScreenshot(result.getName());
		}

		CaptureHelper.stopRecord();
	}
}