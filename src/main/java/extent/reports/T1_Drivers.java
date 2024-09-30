package extent.reports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class T1_Drivers {
    private WebDriver driver;

    public T1_Drivers() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @BeforeClass
    public WebDriver getDriver(){
        return driver;
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }
}
