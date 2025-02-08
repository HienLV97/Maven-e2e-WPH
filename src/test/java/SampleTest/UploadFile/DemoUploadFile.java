package SampleTest.UploadFile;

import Initialization.Init;
import helpers.drivers.DriverManager;
import helpers.CaptureHelper;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.lang.reflect.Method;


import java.awt.*;
import java.io.IOException;

public class DemoUploadFile extends Init {
	@Test
	public void testHomePage1(Method method) throws IOException, AWTException {
		CaptureHelper.startRecord("test2");
		DriverManager.getDriver().get("https://anhtester.com");
		screenShot("hihi");
		Assert.assertEquals(DriverManager.getDriver().getTitle(), "Anh Tester Automation Testing");
		sleep(10);
		CaptureHelper.stopRecord();
	}

//	@Test
	public void testHomePage2(Method method) {
		DriverManager.getDriver().get("https://anhtester.com");
		Assert.assertEquals(DriverManager.getDriver().getTitle(), "Anh Tester Automation Test");
	}

}
