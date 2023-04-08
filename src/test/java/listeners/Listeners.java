package listeners;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listeners extends TestListenerAdapter{
	
	public void OnTestStart(ITestResult tr)
	{
		System.out.println("Test Started");
	}
	
	public void OnTestSuccess(ITestResult tr)
	{
		System.out.println("Test passed");
	}
	
	public void OnTestFailure(ITestResult tr)
	{
		System.out.println("Test Failed");
	}
	
	public void OnTestSkipped(ITestResult tr)
	{
		System.out.println("Test skipped");
	}

}
