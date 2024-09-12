package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
                //new cooments added for demopurpose 
  String productname="ZARA COAT 3";
  WebDriverManager.chromedriver().setup();
  WebDriver driver = new ChromeDriver();
  driver.manage().window().maximize();
  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  driver.get("https://rahulshettyacademy.com/client/");
  //creating object for the landing page
  LandingPage landingPage = new LandingPage(driver);//sending the argument driver to this class
  driver.findElement(By.id("userEmail")).sendKeys("test5123@gmail.com");
  driver.findElement(By.id("userPassword")).sendKeys("Scooby123@");
  driver.findElement(By.id("login")).click();
  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));//explicit wait
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));
  List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));
  
  //Using Java Streams to iterate
  WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b"))
		  .getText().equals(productname)).findFirst().orElse(null);
  
  //Parent to child traverse and selecting the last/second button since there are 2 buttons in each product
  prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
  

  //wait till the toast is displayed
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toast-container']")));
  //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-animating")));//from developer point
  Thread.sleep(5000);
  driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
  
  //Parent to child traverse to identify 2 elements in the cart example zara coat and addidas shoes
  //.cartSection h3---css
  //or //*[@class='cartSection']/h3--xpath
  List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
  
  //iterate
  Boolean match = cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));
  Assert.assertTrue(match);
  driver.findElement(By.cssSelector(".totalRow button")).click();
  
  Actions a = new Actions(driver);
  a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();

   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
  //.ta-item:nth-of-type(2)--css
  //(//button[contains(@class.'ta-item')])[2]--xpath
   
  WebElement ee = driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)"));
  JavascriptExecutor js=(JavascriptExecutor) driver;
  js.executeScript("arguments[0].click();",ee);
 
  
  

  WebElement ad = driver.findElement(By.cssSelector(".action__submit"));
  JavascriptExecutor js1=(JavascriptExecutor) driver;
  js1.executeScript("arguments[0].click();",ad);
  
  String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
  Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
  driver.quit();
		
		
		
	}

}
