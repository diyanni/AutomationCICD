package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.TestComponent.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest {

@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)//flaky
public void LoginErrorValidation() throws IOException, InterruptedException
{
		
  String productname="ZARA COAT 3";
  landingPage.loginApplication("test5iS123@gmail.com", "Scooby1m23@");
  //giving wrong email id and pswrd
  //capturing the invalid email and pwrd locator or ask the developer
  //.ng-tns-c4-21.ng-star-inserted.ng-trigger,ng-trigger-flyInOut.ngx-toastr.toast-error
  Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
  //to fail it remove any word
 // Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
		
	}

@Test
public void ProductErrorValidation() throws IOException, InterruptedException
{
		
  String productname="ZARA COAT 3";
  ProductCatalog productCatalog = landingPage.loginApplication("test6123@gmail.com", "Scooby123@@");
  
  List<WebElement>products = productCatalog.getProductList();
  productCatalog.addProductToCart(productname);
  CartPage cartPage = productCatalog.goToCartPage();
  
  Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
  Assert.assertFalse(match);

}
}
