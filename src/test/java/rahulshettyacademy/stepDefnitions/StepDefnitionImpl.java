package rahulshettyacademy.stepDefnitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class StepDefnitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")//regular expression thats why caps and dollar sign--(.+) dynamic values
	public void Logged_in_with_username_and_password(String username, String password)
	{
		
		  productCatalog = landingPage.loginApplication(username,password);//data is obtained from feature file username and password
		
	}
	
	 @When("^I add the product (.+) to Cart$")
	 public void I_add_the_product_to_Cart(String productname) throws InterruptedException
	 {
		 
		  List<WebElement>products = productCatalog.getProductList();
		  productCatalog.addProductToCart(productname);
	 }
	 
	 //And Checkout <productname> and submit the order
	 @When("^Checkout (.+) and submit the order$") //we can use when or then based upon the scenario
	 public void Checkout_productname_and_submit_the_order(String productname) throws InterruptedException
	 {
		  cartPage = productCatalog.goToCartPage();
		  
		  Boolean match = cartPage.VerifyProductDisplay(productname);
		  Assert.assertTrue(match);
		  CheckoutPage checkoutPage = cartPage.goToCheckout();
		  checkoutPage.selectCountry("India");
		  confirmationPage = checkoutPage.submitOrder();
	 }
	 
	 //Then I verify "THANKYOU FOR THE ORDER." message is displayed on the ConfirmationPage
	 @Then("I verify {string} message is displayed on the ConfirmationPage") //expecting a dynamic string value at runtime
	 public void I_verify_message_is_displayed_in_the_ConfirmationPage(String string)
	 {
		 String confirmMessage = confirmationPage.getConfirmationMessage();
		  Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		  driver.quit();
	 }
	 
	 @Then("{string} message is displayed")
	 public void message_is_displayed(String string1)
	 {
		 
		 Assert.assertEquals(string1, landingPage.getErrorMessage());
		 driver.quit();
	 }
}
