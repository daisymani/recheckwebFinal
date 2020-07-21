import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;

public class FinalUnbreakableTest {
    public WebDriver driver;
    public Recheck re;
    
    //@BeforeEach

    @BeforeTest
    public void setupDriver() {
    	
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        
        driver = new ChromeDriver();
        re = new RecheckImpl();
    }
    
 
    @org.testng.annotations.Test
    public void breakableTest() throws Exception {
    	re.startTest();
    	System.out.println("Entered Test Run");
        String url = Paths.get("src/test/resources/loginpage.html").toUri().toURL().toString();
        driver.get(url);
        Thread.sleep(1000);
        driver.findElement(By.id("username123")).sendKeys("user");
        driver.findElement(By.id("password")).sendKeys("dev");
        driver.findElement(By.id("login-form-submit")).click();

        String result = driver.findElement(By.id("login-success-msg")).getText().equals("You have successfully logged in") ? "Login Pass" : "Login Fail";
        System.out.println(result);
        
        re.check(driver, "finalstate_Trial");

        re.capTest();
    }

    @AfterTest
    public void windUp(){
      //  driver.quit();
        re.cap();
        
        System.out.println("Exit Run :: TEST EXECUTED****");
    }
}
