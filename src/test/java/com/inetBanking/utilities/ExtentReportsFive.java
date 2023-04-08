package com.inetBanking.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsFive {
	
	@Test
	public void extentTest() throws IOException
	{
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("index.html"); //html file will be generated
		
		
		final File CONF = new File("extent-config.xml");
		spark.loadXMLConfig(CONF);
		
		extent.attachReporter(spark);
		
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("ExtentReportsDemo");
		
		ExtentTest test=extent.createTest("Login Test").assignAuthor("Pravin Wakle").assignCategory("Smoke").assignDevice("Chrome 84");
		test.pass("Login Test Started Successfully");
		test.info("url loaded");
		test.info("Values entered");
		test.pass("Login Test completed Successfully");
		
		ExtentTest test1=extent.createTest("HomePage Test").assignAuthor("Latika").assignCategory("Regression").assignDevice("Edge 23");
		test1.pass("HomePage Test Started Successfully");
		test1.info("url loaded");
		test1.info("Values entered");
		test1.fail("HomePage Test completed Successfully");
		
		extent.flush();// Unless you call this method your will not be written with logs
		Desktop.getDesktop().browse(new File("index.html").toURI());
		
	}
	
	

}
