package SampleTest.Listeners;

import com.aventstack.extentreports.Status;
import helpers.CaptureHelper;
import helpers.PropertiesHelper;
import logs.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
//import reports.AllureManager;
//import reports.ExtentReportManager;
//import reports.ExtentTestManager;

public class TestListener implements ITestListener {

	public String getTestName(ITestResult result) {
		return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
	}

	public String getTestDescription(ITestResult result) {
		return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
	}

	@Override
	public void onStart(ITestContext result) {
		PropertiesHelper.loadAllFiles();
		//Khởi tạo report (Extent và Allure)
	}

	@Override
	public void onFinish(ITestContext result) {
		LogUtils.info("End testing " + result.getName());

		//Kết thúc và thực thi Extents Report
//		ExtentReportManager.getExtentReports().flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		LogUtils.info("Running test case " + result.getName());

		//Bắt đầu ghi 1 TCs mới vào Extent Report
//		ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		LogUtils.info("Test case " + result.getName() + " is passed.");

		//Extent Report
//		ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");
//		ExtentTestManager.addScreenshot(result.getName());
//		CaptureHelper.captureScreenshot(result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		LogUtils.error("Test case " + result.getName() + " is failed.");
		//Screenshot khi fail
		//CaptureHelper.captureScreenshot(result.getName());
		LogUtils.error(result.getThrowable().toString());

		//Extent Report
//		ExtentTestManager.addScreenshot(result.getName());
//		ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
//		ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");

		//Allure Report
//		AllureManager.saveTextLog(result.getThrowable().toString());
//		AllureManager.saveTextLog(result.getName() + " is failed.");
//		AllureManager.saveScreenshotPNG();
//		if (PropertiesHelper.getValue(""))
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		LogUtils.error("Test case " + result.getName() + " is skipped.");
		LogUtils.error(result.getThrowable().toString());

		//Extent Report
//		ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
	}

}