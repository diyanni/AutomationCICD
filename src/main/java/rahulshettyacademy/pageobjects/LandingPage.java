package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
      WebDriver driver;
      
      //Creating constructor
      
      public LandingPage(WebDriver driver)
      {
    	  
    	  //main advantage -uses the same name as the class name and first this executes before anything 
    	  //happens, as well initialization happens here
    	  //Page Object Model: Say in our application we have the login page, product catalog page, 
    	  //checkout page, confirm page etc. wherein we create 4 Java classes instead of one single 
    	  //file, all the locators which belong to login class will go into login java class and so on.
    	  //Thus, we are reducing the size of the single file. So whenever the login id changes for a 
          // of time we directly go to the Login class and update it accordingly.
    	  
    	  //accessing the driver from child to parent
    	  super(driver);
    	  
    	  this.driver = driver; //assigning to the local class variable and scope is inside
    	  //initElements will trigger initializing all the elements-the construction of the page factory
    	  //method will be called once you call the below the method 
    	  PageFactory.initElements(driver, this);// it takes the driver as argument and uses the driver to 
    	  //initialize
      }
	
	 // WebElement userEmail = driver.findElement(By.id("userEmail"));
	 // 0r
     //PageFactory - is used to reduce the syntax in creation of the WebElement.
	  @FindBy(id="userEmail")
	  WebElement userEmail;
	 // driver.findElement(By.id("userPassword"))
	  @FindBy(id="userPassword")
	  WebElement passwordEle;
	  //driver.findElement(By.id("login"))
	  @FindBy(id="login")
	  WebElement submit;
	  
	  @FindBy(css="[class*='flyInOut']")
	  WebElement errorMessage;
	  
	  
	  //using actions for login
	  public ProductCatalog loginApplication(String email,String password)
	  {
		  userEmail.sendKeys(email);
		  passwordEle.sendKeys(password);
		  submit.click();
		  ProductCatalog productCatalog = new ProductCatalog(driver);
		  return productCatalog;
	  }
	  
	  public String getErrorMessage()
	  {
		  
		  waitForWebElementToAppear(errorMessage);
		  return errorMessage.getText();
		
	  }

	public void goTo() {
		
		 driver.get("https://rahulshettyacademy.com/client/");
	}
	  
}
