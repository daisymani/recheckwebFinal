import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.web.selenium.RecheckDriver;

public class UnbreakableTestTrial {
    public WebDriver driver;
    public RecheckImpl recheckin;
    public String url = null;
    
    int NewCount = 0;

    @BeforeMethod
	public void beforeMethod( final Method method, final Object[] testData ) throws MalformedURLException {
    	recheckin = new RecheckImpl();
    	System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
    	driver = new RecheckDriver(new ChromeDriver());  
    	recheckin.startTest();
    	url = Paths.get("src/test/resources/loginpage.html").toUri().toURL().toString();
    	driver.get(url);
    	NewCount = NewCount+1;
    	System.out.println("$$$$  NewCount value is $$$: " + NewCount);
	}
    
    @org.testng.annotations.Test
    public void unbreakableTest() throws Exception {   	
    	System.out.println("############ UbreakableTest #################");
        driver.findElement(By.id("username123")).sendKeys("user");
        driver.findElement(By.id("password")).sendKeys("dev");
        driver.findElement(By.id("login-form-submit")).click();

        String result = driver.findElement(By.id("login-success-msg")).getText().equals("You have successfully logged in") ? "Login Pass" : "Login Fail";
        System.out.println(result);
      recheckin.check(driver, "trial");
      recheckin.capTest();
    }
    
/*    @org.testng.annotations.Test
    public void unbreakableTest2() throws Exception {   	
    	System.out.println("******* UbreakableTest2 ******");
    	 driver.findElement(By.id("username123")).sendKeys("user");
         driver.findElement(By.id("password")).sendKeys("dev");
         driver.findElement(By.id("login-form-submit")).click();

         String result = driver.findElement(By.id("login-success-msg")).getText().equals("You have successfully logged in") ? "Login Pass" : "Login Fail";
         System.out.println(result);
       recheckin.check(driver, "trial1");
       recheckin.capTest();
    }
    
    @org.testng.annotations.Test
    public void unbreakableTest3() throws Exception {   	
    	System.out.println("******* UbreakableTest3 ******");
    }
*/
    
	@AfterMethod
	public void afterMethod( final Method method, final Object[] testData ) {
		 System.out.println("executed afterMethod");
		 recheckin.cap();
		 driver.quit();
	}


}
