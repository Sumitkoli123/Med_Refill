package com.medevantage.medrefill.utility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.medevantage.medrefill.base.BaseClass;

public class ListenerTest extends BaseClass implements ITestListener {

	ExtentReports report = ExtentReporter.extentReportGenerator();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {

		System.out.println(("*** Running test method : " + result.getMethod().getMethodName() + "..."));
		test = report.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		System.out.println("*** Executed : " + result.getMethod().getMethodName() + " test successfully...");
		//test.log(Status.PASS, "Test passed");
		test.pass(MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {

		System.out.println(("*** Test Execution : " + result.getName() + " Failed ***"));
		test.log(Status.FAIL,
				MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
		test.fail(result.getThrowable());
		try {
			test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(), driver),
					result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		System.out.println("*** Test : " + result.getMethod().getMethodName() + " skipped...");
		//test.log(Status.SKIP, "Test Skipped");
		test.skip(MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		System.out.println("*** Test failed but within percentage %  : " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

		test.fail(MarkupHelper.createLabel("Test Failed With Timeout", ExtentColor.BLUE));
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("===============================================");
		System.out.println("*** Test Suite : " + context.getName() + " started ***");
	}

	@Override
	public void onFinish(ITestContext context) {

		System.out.println(("*** Test Suite : " + context.getName() + " Completed ***"));
		report.flush();
		System.out.println("===============================================");
	}

}
