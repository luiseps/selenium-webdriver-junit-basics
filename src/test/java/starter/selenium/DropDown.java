package starter.selenium;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DropDown {

    @Test
    public void testCheckBox(){

        System.setProperty("webdriver.chrome.driver","./src/test/resources/webdriver/mac/chromedriver");
        String baseURL = "http://demo.guru99.com/test/newtours/register.php";
        WebDriver driver = new ChromeDriver();
        driver.get(baseURL);

        Select drpCountry = new Select(driver.findElement(By.name("country")));
        drpCountry.selectByVisibleText("ANTARCTICA");

        //Selecting Items in a Multiple SELECT elements
        driver.get("http://jsbin.com/osebed/2");
        Select fruits = new Select(driver.findElement(By.id("fruits")));
        fruits.selectByVisibleText("Banana");
        fruits.selectByIndex(1);
        driver.close();
    }
}
