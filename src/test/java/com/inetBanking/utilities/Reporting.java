package com.inetBanking.utilities;

//Listener class used to generate extent report
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {

	public WebDriver driver;

	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent; // Specify location of report
	public ExtentTest logger; // what details should be populated in the report

	public void onStart(ITestContext testContext){
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  //time stamp
		String repName = "Test-Report-" + timestamp + ".html";
		
		extent = new ExtentReports();
		sparkreporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName);
		
		try {
			sparkreporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sparkreporter.config().setDocumentTitle("inetBanking Test Project"); //title of the report
		sparkreporter.config().setReportName("Functional Test Automation Report"); //name of the report
		sparkreporter.config().setTheme(Theme.DARK);

		
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "pravin");
	}

	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName());  //create new entry on the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
		
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		System.out.println(screenshotPath);
		
		File f=new File(screenshotPath);
		
		if(f.exists()) 
		{
			try {
					logger.fail("screenshot is below:"+logger.addScreenCaptureFromPath(screenshotPath));
				}
			catch(Exception e){
					e.printStackTrace();
				  }
		}
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}

}
