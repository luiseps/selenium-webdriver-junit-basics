package starter.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class AlertPopup {
    WebDriver driver;

    @Before
    public void settingUp(){
        System.setProperty("webdriver.chrome.driver","./src/test/resources/webdriver/mac/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testLinksBlankSpaces() {
        driver.get("http://demo.guru99.com/test/delete_customer.php");
        List<WebElement> links =  driver.findElements(By.tagName("a"));
        links.forEach( link -> {
            String linkText = link.getText();
            assertEquals(linkText.trim(), linkText);
        });
    }

    @Test
    public void maxNumber() throws NoSuchFieldException {
        String numbers = "1 -1 100 500 -1 -2";
        List<String> numberList = Arrays.asList(numbers.split(" "));
        Integer max = numberList
                .stream()
                .mapToInt(v -> Integer.parseInt(v))
                .max()
                .orElseThrow(NoSuchFieldException::new);
        System.out.println(max);
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

    @Test
    public void testMultipleAlerts(){
        driver.get("http://demo.guru99.com/popup.php");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();

        String MainWindow=driver.getWindowHandle();

        // To handle all new opened window.
        Set<String> s1=driver.getWindowHandles();
        Iterator<String> i1=s1.iterator();

        while(i1.hasNext())
        {
            String ChildWindow=i1.next();

            if(!MainWindow.equalsIgnoreCase(ChildWindow))
            {

                // Switching to Child window
                driver.switchTo().window(ChildWindow);
                driver.findElement(By.name("emailid"))
                        .sendKeys("gaurav.3n@gmail.com");

                driver.findElement(By.name("btnLogin")).click();

                // Closing the Child Window.
                driver.close();
            }
        }
        // Switching to Parent window i.e Main Window.
        driver.switchTo().window(MainWindow);
    }
    @After
    public void closeDriver(){
        driver.close();
    }
}
