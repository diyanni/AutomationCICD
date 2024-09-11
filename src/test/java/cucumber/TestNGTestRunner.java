package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="rahulshettyacademy.stepDefnitions",
monochrome=true,tags = "@Regression",plugin= {"html:target/cucumber.html"})//path monochrome is nothing but readble format
//all html should be in key value pairs--all these are helper attributes

public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
