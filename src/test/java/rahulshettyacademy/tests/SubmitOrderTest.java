package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {
	String productname="ZARA COAT 3";//to get the scope outside
@Test(dataProvider="getData",groups= {"Purchase"})
public void submitorder(HashMap<String,String> input) throws IOException, InterruptedException
{
	//public void submitorder(String email, String password, String productname) throws IOException, InterruptedException
	//public void submitorder(HashMap<Object,Object> input) throws IOException, InterruptedException
  
  //ProductCatalog productCatalog = landingPage.loginApplication("test5123@gmail.com", "Scooby123@");
  //ProductCatalog productCatalog = landingPage.loginApplication(email, password);
  ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
  List<WebElement>products = productCatalog.getProductList();
  //productCatalog.addProductToCart(productname);
  productCatalog.addProductToCart(input.get("product"));
  CartPage cartPage = productCatalog.goToCartPage();
  
  //Boolean match = cartPage.VerifyProductDisplay(productname);
  Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
  Assert.assertTrue(match);
  CheckoutPage checkoutPage = cartPage.goToCheckout();
  checkoutPage.selectCountry("India");

  ConfirmationPage confirmationPage = checkoutPage.submitOrder();
  String confirmMessage = confirmationPage.getConfirmationMessage();
  Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
  
				
	}

//to verify ZARA COAT 3 is displaying in orders page
	@Test(dependsOnMethods= {"submitorder"})
	public void OrderHistoryTest()//runs only after the previous one is executed
	{
		//"ZARA COAT 3"
		//login into your application
		ProductCatalog productCatalog = landingPage.loginApplication("test5123@gmail.com", "Scooby123@");
		//then click on orders
		OrderPage orderPage = productCatalog.goToOrderPage();//returns object of orderPage
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productname));
		
	}
	//to run the tests in parallel go the testng.xml and at suite level <suite parallel = "tests" name ="Suite">
    //Similarly we can run methods also parallely
	// <suite parallel = "methods" name ="Suite">
	//thread count =5 means it will allow max of five methods to run parallely from the java class file
	//to run selective tests we use grouping -testng
	//done after the suite level
	//<suite  parallel = "tests" name="Suite">
	//<groups>
	//<run>
	//<include name ="ErrorHandling"/>
	//</run>
	//</groups>
	//Jason is used to drive the data from external sources instead of csv or excel-it is lightweight and it is in current trend.


//Extent Reports-this utility helps with html reports	

@DataProvider //helps to drive the data and pass the multiple data sets
public Object[][] getData() throws IOException
{
	//HashMap<Object,Object> map = new HashMap<Object,Object>();
	//HashMap<String,String> map = new HashMap<String,String>();
	//map.put("email", "test5123@gmail.com");
	//map.put("password","Scooby123@");
	//map.put("productname", "ZARA COAT 3");
	//map.put("product", "ZARA COAT 3");
	
	//HashMap<String,String> map1 = new HashMap<String,String>();
	//map1.put("email", "test6123@gmail.com");
	//map1.put("password","Scooby123@@");
	//map1.put("product", "ADIDAS ORIGINAL");
	
	//say you want the run the test with 2 different data sets- so we create a two dimensional array which accepts multiple sets
	//return new Object[][] {{"test5123@gmail.com","Scooby123@","ZARA COAT 3"}, {"test6123@gmail.com","Scooby123@@","ADIDAS ORIGINAL"}};//2 dimensional syntax as it contains 2 curly brackets , for 3 include 3 curly brackets
	
	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test///java//rahulshettyacademy//data//PurchaseOrder.json");
	
	//return new Object[][] {{map}, {map1}};//2 dimensional syntax as it contains 2 curly brackets , for 3 include 3 curly brackets
	return new Object[][] {{data.get(0)},{data.get(1)} };
	//object refers to any data kind of data type
}

//dataprovider also helps to return hashmap, instead of sending the data , we put in keyvalue pairs and directly
//send it in hashmap








}
