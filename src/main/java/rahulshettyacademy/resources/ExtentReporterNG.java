package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportOject()//no need to declare object if static
	{
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);//expects a path where your report  
		//should be created
		//Responsible to create html file and do some configurations.
		//to set your report name as web automation results
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle(" Test Results");
	    
		//this is the main class to drive all of the reporting execution
		ExtentReports extent = new ExtentReports();
		//attach the complete report to this main class
		extent.attachReporter(reporter);
	    //to give tester's name
		extent.setSystemInfo("Tester", "Tweety");
		return extent;
		
	}
}
