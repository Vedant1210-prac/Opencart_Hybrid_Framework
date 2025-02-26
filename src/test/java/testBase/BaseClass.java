package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;  //logger
	public Properties p;
	
	
	@BeforeClass(groups = {"Regression", "Master", "Sanity", "dataprovider"})
	@Parameters({"os","browser"})
	public void setup(String os , String br) throws IOException {
		//Loding Config.properties file
		p = new Properties();
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p.load(file);
		
		
	
		logger = LogManager.getLogger(this.getClass());
		
		
		//Run the framework on selenium grid or remote environment
		
		if(p.getProperty("execution_env").equals("remote")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			
			//for hardcode
			/*cap.setPlatform(Platform.WIN11);
			cap.setBrowserName("chrome");   but we are passing the value from xml file*/   
			
			
			//OS
			if(os.equalsIgnoreCase("windows")) {
				
				cap.setPlatform(Platform.WIN11);
			}else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}else {
				
				System.out.println("Unknown OS");
			}
			
			
			//browser
			switch(br.toLowerCase()) {
			case "chrome" : cap.setBrowserName("chrome");
			break;
			case "edge" : cap.setBrowserName("MicrosoftEdge");
			break;
			default : System.out.println("No matching browser");
			return;
			}
			
			driver = new RemoteWebDriver(new URL("http://192.168.31.167:4444/wd/hub"), cap);
			
			
		}
		
		
		if(p.getProperty("execution_env").equals("local")) {
			
		
		switch(br.toLowerCase())
		{
		case "chrome":driver = new ChromeDriver();
		break;
		case "edge" : driver = new EdgeDriver();
		break;
		default: System.out.println("Invalid browser ");
		return;
		}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//reading url from properties file
		//driver.get("https://demo.opencart.com/");
		driver.get(p.getProperty("AppURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups = {"Regression", "Master", "Sanity", "dataprovider"})
	public void tearDown() {
		//driver.quit();
		    if (driver != null) {
		        driver.quit();
		        driver = null; // Avoid Stale WebDriver issues
		    }
		}

	
	
public String randomString() {
		
		String RandomString = RandomStringUtils.randomAlphabetic(5);
		return RandomString;
	}
	
	public String randomPassword() {
		
		String AlphanumPassword = RandomStringUtils.randomAlphanumeric(3);
		String numeric  = RandomStringUtils.randomNumeric(3);
		
		return (AlphanumPassword+"@"+numeric);
		
	}
	
	public String CaptureScreentShot(String tName) {
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-DD-HH.mm.ss").format(new Date());
		
		TakesScreenshot Ts = (TakesScreenshot)driver;
		
		File sourceFile = Ts.getScreenshotAs(OutputType.FILE);
		
		String targetPath = System.getProperty("user.dir")+"//screenshots//"+tName+"-"+timeStamp+".png";
		
		File targetFile = new File(targetPath);
		
		sourceFile.renameTo(targetFile);
		
		return targetPath;
		
		
		
		
	}

}
