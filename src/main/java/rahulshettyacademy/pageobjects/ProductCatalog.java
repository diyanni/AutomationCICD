package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
	
      WebDriver driver;
      
      //Creating constructor
      
      public ProductCatalog(WebDriver driver)
      {
    	super(driver);//for every child class 
    	  PageFactory.initElements(driver, this);
      }
      //List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));
	
      @FindBy(css="div.mb-3")
      List<WebElement> products;//for elements
      
      @FindBy(css=".ng-animating")
      WebElement spinner;
      
      By productsBy = By.cssSelector("div.mb-3");
      By addToCart = By.cssSelector(".card-body button:last-of-type");
      By toastMessage = By.xpath("//*[@id='toast-container']");
      
	  public List<WebElement> getProductList()//return type is list of products
	  {
		  waitForElementToAppear(productsBy);
		  return products;
	  }
	  
	  public WebElement getProductByName(String productname)
	  {
		  
		  //Using Java Streams to iterate
		  WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b"))
				  .getText().equals(productname)).findFirst().orElse(null);
		  return prod;
	  }
	  
	  public void addProductToCart(String productname) throws InterruptedException
	  {
		  WebElement prod = getProductByName(productname);
		  //Parent to child traverse and selecting the last/second button since there are 2 buttons in each product
		  prod.findElement(addToCart).click();
		  waitForElementToAppear(toastMessage);
		  waitForElementToDisappear(spinner);
		  
		  
	  }
	
}
