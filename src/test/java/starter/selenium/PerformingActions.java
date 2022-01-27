package starter.selenium;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class PerformingActions {

    @Test
    public void testActions(){

        String baseUrl = "http://www.facebook.com/";
        System.setProperty("webdriver.chrome.driver","./src/test/resources/webdriver/mac/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get(baseUrl);
        WebElement txtUsername = driver.findElement(By.id("email"));

        Actions builder = new Actions(driver);
        Action seriesOfActions = builder
                .moveToElement(txtUsername)
                .click()
                .keyDown(txtUsername, Keys.SHIFT)
                .sendKeys(txtUsername, "hello")
                .keyUp(txtUsername, Keys.SHIFT)
                .doubleClick(txtUsername)
                .contextClick()
                .build();

        seriesOfActions.perform() ;
        driver.quit();

    }
}
