import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.web.selenium.RecheckDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.nio.file.Paths;

public class UnbreakableSeleniumTest {
//    public WebDriver driver;
    public Recheck recheck;
    RecheckDriver driver;

    @BeforeTest
    public void setupRecheck() {
        recheck = new RecheckImpl();
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        driver = new RecheckDriver(new ChromeDriver());
    }

    @org.testng.annotations.Test
    public void unBreakableTest() throws Exception {
        recheck.startTest();

    	System.out.println("Entry");
        String url = Paths.get("src/test/resources/loginpage.html").toUri().toURL().toString();
        Thread.sleep(1000);
        driver.get(url);

        Thread.sleep(1000);        
        
        driver.findElement(By.id("username123")).sendKeys("user");
        driver.findElement(By.id("password")).sendKeys("dev");
        driver.findElement(By.id("login-form-submit")).click();

/*        driver.findElement(By.retestId("usernamefield")).sendKeys("user");
        driver.findElement(By.retestId("passwordfield")).sendKeys("dev");
        driver.findElement(By.retestId("loginformsubmit")).click();*/
       recheck.check(driver,"LoginPage");
       recheck.capTest();
    }

    @AfterTest
    public void windUp(){
        driver.quit();
    	//recheck.capTest();
        recheck.cap();
    }
}
