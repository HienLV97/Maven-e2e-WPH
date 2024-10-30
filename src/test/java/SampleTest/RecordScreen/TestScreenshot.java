package SampleTest.RecordScreen;

import AcaWriting.Support.Initialization.Init;
import AcaWriting.drivers.DriverManager;
import helpers.CaptureHelper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
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