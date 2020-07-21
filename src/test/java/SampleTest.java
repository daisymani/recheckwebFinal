

import java.lang.reflect.Method;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;

public class SampleTest implements ITest {

	private final ThreadLocal<String> testName = new ThreadLocal<String>();

	private Recheck recheck;
	private WebDriver webDriver;

	public String getTestName() {
		
		//System.out.println("############# Test name is : " + testName.get());
		return testName.get();
	}

	@BeforeMethod
	public void beforeMethod( final Method method, final Object[] testData ) {
		recheck = new RecheckImpl();
		webDriver = new ChromeDriver();
		
		System.out.println("$$$$$$$  Inside Beforemethod : " );

		if ( testData == null || testData.length == 0 ) {
			testName.set( String.format( "%s", method.getName() ) );
		} else {
			testName.set( String.format( "%s %s", method.getName(), Arrays.toString( testData ) ) );
		}
	}

	@Test
	public void test() {
		recheck.startTest( "test" );
		
		System.out.println("$$$$$$$  Inside Test : " );

		webDriver.get( "https://google.com" );

		recheck.check( webDriver, "step-1" );

		recheck.capTest();
		recheck.cap();

	}

	@AfterMethod
	public void afterMethod( final Method method, final Object[] testData ) {
		System.out.println("Entered After Test **************");
		recheck.cap();
		webDriver.quit();
	}

}