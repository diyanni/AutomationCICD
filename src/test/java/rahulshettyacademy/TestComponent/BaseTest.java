package rahulshettyacademy.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	//globally declared
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver intializeDriver() throws IOException
	{
		//setting up Global properties -there is a class in Java called Properties.That properties class 
		//can read the global properties.
		
		//creating global properties
		Properties prop = new Properties();
		//converting it into Input Stream object 
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//rahulshettyacademy//resources//GlobalData.properties");//send the path og GlobalData.properties by right click Properties
		//instead of giving the full path we dynamically give and use // for the remaining path so that Java recognizes the path
		prop.load(fis);//expect inputStreamObject as argument
		//to get browser property
		
		//maven cmd for setting the browser
		//String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
				//helps to read system level variable values
		
		String browserName = prop.getProperty("browser");
		if(browserName.contains("chrome"))
		//if(browserName.equals("chrome"))
		
		{
			
			//to run in headless mode only when declared in the cmd maven command as headless
			
		//to make the browser work in headless mode
			ChromeOptions options = new ChromeOptions();
			//driver.manage().window().setSize(new Dimension(1440,990));//to open maximized mode and to prevent error in the API -fullscreen mode
				
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless"))
		{   
			
			System.out.println("Running in headless mode");
			options.addArguments("headless");//headless chrome browser
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,990));
		}
		
			
		
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1440,990));
		
		}
		
		else if (browserName.equals("firefox"))
		{
			//Firefox code
		}
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    return driver;
		}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
				
		//convert String to HashMap-Jackson Databind
		//go to mvn repository and get it and paste it in pon.xml
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
		
		
		}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		//casting the driver to screenshot mode
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" +testCaseName +".png");
		//to store the file into local work space
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//" +testCaseName +".png";
		
	}
	
	@BeforeMethod(alwaysRun=true)	//method executes first//to avoid failures while using grouping
	public LandingPage launchApplication() throws IOException
	{
		
		driver = intializeDriver();
		  //creating object for the landing page
		  landingPage = new LandingPage(driver);//sending the argument driver to this class
		  landingPage.goTo();
		  return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)//to avoid failures while using grouping
	public void tearDown()
	{
		driver.quit();
	}
	}

