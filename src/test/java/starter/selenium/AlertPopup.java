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
import java.util.stream.Collectors;

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
    public void maxNumber() {
        String numbers = "1 -1 100 500 -1 -2";
        List<String> numberList = Arrays.asList(numbers.split(" "));
        OptionalInt max = numberList
                .stream()
                .mapToInt(v -> Integer.parseInt(v))
                .max();
        System.out.println(max.getAsInt());
    }

    @Test
    public void getOccurrences(){
        String randomWord = "the world random word the they we";
        List<String> words = Arrays.asList(randomWord.split(" "));
        List<String> processed = new ArrayList<>();
        words.forEach( w -> {
            int count = 0;
            for(int i= 0; i<words.size(); i++){
                if(w.equals(words.get(i)) && !processed.contains(w)){
                    count++;
                }
            }
            if(!processed.contains(w)){
                System.out.println(count + " : " + w);
            }
            processed.add(w);

        });
    }

    @Test
    public void getOccurrencesJava8(){
        String randomWord = "the world random word the they we";
        List<String> words = Arrays.asList(randomWord.split(" "));
        List<String> processed = new ArrayList<>();

        words.forEach( w -> {
            if(!processed.contains(w)){
                System.out.println(w + ": " + words.stream().filter( e -> e.equals(w) ).collect(Collectors.toList()).size());
            }
            processed.add(w);
        });
    }

    @Test
    public void searchOnTable(){
        driver.get("http://demo.guru99.com/test/table.html");
        WebElement myTable = driver.findElement(By.xpath("/html/body/table/tbody"));
        List < WebElement > rows_table = myTable.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();

        for (int row = 0; row < rows_count; row++) {
            List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
            int columns_count = Columns_row.size();
            for (int column = 0; column < columns_count; column++) {
                String celText = Columns_row.get(column).getText();
                System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celText);
            }
        }
    }

    @Test
    public void testAlertPopup(){

        driver.get("http://demo.guru99.com/test/delete_customer.php");

        driver.findElement(By.name("cusid")).sendKeys("53920");
        driver.findElement(By.name("submit")).submit();
        Alert alert = driver.switchTo().alert();
        String alertMessage= driver.switchTo().alert().getText();
        System.out.println(alertMessage);
        alert.accept();
    }

    @Test
    public void testMultipleAlerts(){
        driver.get("http://demo.guru99.com/popup.php");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();

        String MainWindow=driver.getWindowHandle();
        Set<String> s1=driver.getWindowHandles();
        Iterator<String> i1=s1.iterator();

        while(i1.hasNext())
        {
            String ChildWindow=i1.next();

            if(!MainWindow.equalsIgnoreCase(ChildWindow))
            {
                driver.switchTo().window(ChildWindow);
                driver.findElement(By.name("emailid"))
                        .sendKeys("gaurav.3n@gmail.com");
                driver.findElement(By.name("btnLogin")).click();
                driver.close();
            }
        }
        driver.switchTo().window(MainWindow);
    }
    @After
    public void closeDriver(){
        driver.close();
    }
}
