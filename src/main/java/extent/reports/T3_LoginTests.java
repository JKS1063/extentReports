package extent.reports;

import java.util.*;
import java.util.stream.*;
import java.time.Duration;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class T3_LoginTests extends T2_ExtentReports{
    
    public String url = "https://www.saucedemo.com/";
    public String[] validUsernames = {"standard_user", "locked_out_user", "problem_user",
     "performance_glitch_user", "error_user", "visual_user"};
    public String validPassword = "secret_sauce";
    public String[] invalidUsernames = {"", "user_standard", "!@#$*#*$*$", 
    "problem@user", "413312435435", "v1s@l_user"};
    public String[] invalidPasswords = {"", "secret_s@uce", "q4qq5qw4q55443",
     "%^$&^&^$^&$^&$&", "sauce_secret", "visual_user"};    
    
    private String usernamePath = "//*[@name = 'user-name']";
    private String passwordPath = "//*[@name = 'password']";
    private String loginButtonPath = "//*[@name = 'login-button']";
    private String productsInfoPath = "//*[text() = 'Products']";
    private String menuPath = "//*[text() = 'Open Menu']";
    private String logoutPath = "//*[text() = 'Logout']";

    public List<String> getWebEleTextList(String xPath) {
        List<String> texts = getDriver()
        .findElements(By.xpath(xPath))
        .stream().map(WebElement::getText)
        .collect(Collectors.toList());
        return texts;
    }

    public List<String> getWebEleList(String xPath, String attributeName) {
        List<WebElement> elements = getDriver().findElements(By.xpath(xPath));
        List<String> textElements = new ArrayList<>();

        for (WebElement element: elements) {
            String textElement = element.getAttribute(attributeName);
            textElements.add(textElement);
        }

        return textElements;
    }

    public WebElement getXpath(String xPath) {
        return getDriver().findElement(By.xpath(xPath));
    }

    public void syncWait(Integer timeInSeconds) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
    }

    @BeforeMethod
    public void navigate() {
        getDriver().get(url);
        getDriver().manage().window().maximize();
    }

    public void login(String username, String password) {
        try{
            getXpath(usernamePath).sendKeys(username);
            getXpath(passwordPath).sendKeys(password);
            getXpath(loginButtonPath).click();

            boolean homePage = getXpath(productsInfoPath).isDisplayed();
            if (homePage) {
                System.out.println("'" + username + "' able to login");
            }

        } catch (Exception e) {
            System.out.println("'" + username + "' unable to login\n");
            //navigate();
            //e.printStackTrace();
        }
    }

    public boolean productsShown() {
        try{
            return getXpath(productsInfoPath).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //@AfterMethod
    public void logout(String username) {
        try {
            getXpath(menuPath).click();
            getXpath(logoutPath).click();
            System.out.println( "'" + username + "'' Logged Out Successfully\n");
        } catch (Exception e) {
            navigate();
        }
    }
    
}
