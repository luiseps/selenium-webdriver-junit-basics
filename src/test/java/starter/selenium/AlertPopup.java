package starter.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertPopup {
    WebDriver driver;

    @Before
    public void settingUp(){
        System.setProperty("webdriver.chrome.driver","./src/test/resources/webdriver/mac/chromedriver");
        driver = new ChromeDriver();
    }


    @Test
    public void testAlertPopup(){

        driver.get("http://demo.guru99.com/test/delete_customer.php");


        driver.findElement(By.name("cusid")).sendKeys("53920");
        driver.findElement(By.name("submit")).submit();

        // Switching to Alert
        Alert alert = driver.switchTo().alert();

        // Capturing alert message.
        String alertMessage= driver.switchTo().alert().getText();

        // Displaying alert message
        System.out.println(alertMessage);
        // Accepting alert
        alert.accept();
    }

    @After
    public void closeDriver(){
        driver.close();
    }
}
