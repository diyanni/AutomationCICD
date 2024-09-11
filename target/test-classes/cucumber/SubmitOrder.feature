
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

 Background:
 Given I landed on Ecommerce Page
 
 
  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product <productname> to Cart
    And Checkout <productname> and submit the order
    Then I verify "THANKYOU FOR THE ORDER." message is displayed on the ConfirmationPage

    Examples: 
      | name              | password   | productname  |
      | test5123@gmail.com| Scooby123@ |  ZARA COAT 3 |
      
