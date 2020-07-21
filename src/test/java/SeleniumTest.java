import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class SeleniumTest {
    public WebDriver driver;
    
    //@BeforeEach

    @BeforeTest
    public void setupDriver() {
    	
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }
    
 
    @org.testng.annotations.Test
    public void breakableTest() throws Exception {
    	System.out.println("Entered Test Run");
        String url = Paths.get("src/test/resources/loginpage.html").toUri().toURL().toString();
        driver.get(url);
        Thread.sleep(1000);
        driver.findElement(By.id("username123")).sendKeys("user");
        driver.findElement(By.id("password")).sendKeys("dev");
        driver.findElement(By.id("login-form-submit")).click();

        String result = driver.findElement(By.id("login-success-msg")).getText().equals("You have successfully logged in") ? "Login Pass" : "Login Fail";
        System.out.println(result);
    }

    @AfterTest
    public void windUp(){
        driver.quit();
    }
}
