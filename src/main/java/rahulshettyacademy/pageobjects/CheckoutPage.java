package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;
    
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".action__submit")
	WebElement submit;
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	By results = By.cssSelector(".ta-item:nth-of-type(2)");
	
	public void selectCountry(String countryName) throws InterruptedException
	{
		Actions a = new Actions(driver);
		  a.sendKeys(country, countryName).build().perform();
          Thread.sleep(5000);
		  // waitForElementToAppear(By.cssSelector(".ta-results"));
		  //.ta-item:nth-of-type(2)--css
		  //(//button[contains(@class.'ta-item')])[2]--xpath
 		  selectCountry.click();		
		
	}
	public ConfirmationPage submitOrder()
	{
		  JavascriptExecutor jse = (JavascriptExecutor)driver;
		  WebElement ele = submit;
		  jse.executeScript("arguments[0].click()", ele);
		//submit.click();
		return new ConfirmationPage(driver);
		
	}
	
}
