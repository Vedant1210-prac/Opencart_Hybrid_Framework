package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
//import org.testng.ITestResult;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	ExtentSparkReporter sparkReport;
	ExtentReports extent;
	ExtentTest test;
	String repName;
	BaseClass BC = new BaseClass();
	
	
	public void onStart(ITestContext testContext) {


		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  //time stamp
		
		repName = "Test-Report" + timeStamp + ".html";    //dynamic name for report
		
		sparkReport = new ExtentSparkReporter(".//reports//"+repName);   //Dynamic location for saving the report
		
		sparkReport.config().setDocumentTitle("OpenCart-Test");    //Title of Report
		sparkReport.config().setReportName("Automation_OpenCart");   // name of the Report
		sparkReport.config().setTheme(Theme.DARK);					// Managing the theme
		
		extent = new ExtentReports();
		
		extent.attachReporter(sparkReport);
		extent.setSystemInfo("Application", "openCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub module", "Customer");
		extent.setSystemInfo("UserName", System.getenv("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);    // value of os capturing from the testng xml file
		
		String Browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", Browser);
		
		List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty()) {
			
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
		
	
	}
	
	
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + "Test case passed");
		
		try {
			String imgPath1 = BC.CaptureScreentShot(result.getName());
			test.addScreenCaptureFromPath(imgPath1);
			//String imgPath = new BaseClass().CaptureScreentShot(result.getName());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		    }
	public void onTestFailure(ITestResult result) {
	    
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName() + "test failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		
		try {
			String imgPath2 = BC.CaptureScreentShot(result.getName());
			test.addScreenCaptureFromPath(imgPath2);
		}catch(Exception e) {
			e.printStackTrace();
		}
	  }
	
	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	  }
	
	public void onFinish(ITestContext testContext) {
	    
		extent.flush();
		
		String PathOfExtentReport = System.getProperty("user.dir")+"//reports//"+repName;
		File extentReport = new File(PathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
	  }

}
