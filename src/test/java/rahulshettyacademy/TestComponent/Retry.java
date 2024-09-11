package rahulshettyacademy.TestComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {//class has to implement the methods exposed by the interface
    int count=0;
    int maxTry=1;//say you want to re-run only once
    		
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		//whenever test fails it also comes to this block-to check whether it is flaky or not . 
		//It re-runs the test. It asks a question to re-run or not. Sometimes it re-runs 2 or 3 times depending 
		//upon the times it has to re-run in the code
		
		if(count<maxTry)
		{
			//re-run and runs method returns true
			count++;
			return true;
			
		}
		return false;
	}

}
