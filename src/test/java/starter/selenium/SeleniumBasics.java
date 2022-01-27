package starter.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;

public class SeleniumBasics {

    @Test
    public void checkWebElements(){

        System.setProperty("webdriver.chrome.driver","./src/test/resources/webdriver/mac/chromedriver");
        WebDriver driver = new ChromeDriver();
        String baseUrl = "http://demo.guru99.com/test/login.html";
        driver.get(baseUrl);
        driver.close();
    }

}
