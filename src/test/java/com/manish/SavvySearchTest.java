package com.manish;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.build.Plugin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SavvySearchTest {


    WebDriver webDriver;


    @BeforeClass
    public void searchload() {
        System.out.println("Search Savvy Time");
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        webDriver = new ChromeDriver();
    }

    @BeforeMethod
    public void enteringData1() {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://savvytime.com/converter");
        webDriver.findElement(By.xpath("//input[@placeholder='Add Time Zone, City or Town']")).sendKeys("Hyderabad");

//        WebDriverWait wait = new WebDriverWait(webDriver,15,2);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Add Time Zone, City or Town']")));
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='list-group']//a"));
        ////div[@class='list-group']//a"
        elements.get(0).click();

        WebElement element = webDriver.findElement(By.xpath("//h1[@class='title']"));
        Assert.assertTrue(element.getText().contains("Hyderabad"));

        webDriver.findElement(By.xpath("//input[@placeholder='Add Time Zone, City or Town']")).sendKeys("New York");

//        WebDriverWait wait = new WebDriverWait(webDriver,15,2);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Add Time Zone, City or Town']")));
        List<WebElement> elements1 = webDriver.findElements(By.xpath("//div[@class='list-group']//a"));
        ////div[@class='list-group']//a"
        elements1.get(0).click();

        WebElement element1 = webDriver.findElement(By.xpath("//h1[@class='title']"));
        Assert.assertTrue(element1.getText().contains("New York"));
//        webDriver.quit();
    }
    @Test
    public void enteringDataCheck()
    {
        webDriver.findElement(By.xpath("//input[@placeholder='Add Time Zone, City or Town']")).sendKeys("Hyderabad");

//        WebDriverWait wait = new WebDriverWait(webDriver,15,2);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Add Time Zone, City or Town']")));
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='list-group']//a"));
        ////div[@class='list-group']//a"
        elements.get(0).click();

        WebElement element = webDriver.findElement(By.xpath("//h1[@class='title']"));
        Assert.assertTrue(element.getText().contains("Hyderabad"));

        webDriver.findElement(By.xpath("//input[@placeholder='Add Time Zone, City or Town']")).sendKeys("New York");

//        WebDriverWait wait = new WebDriverWait(webDriver,15,2);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Add Time Zone, City or Town']")));
        List<WebElement> elements1 = webDriver.findElements(By.xpath("//div[@class='list-group']//a"));
        ////div[@class='list-group']//a"
        elements1.get(0).click();

        WebElement element1 = webDriver.findElement(By.xpath("//h1[@class='title']"));
        Assert.assertTrue(element1.getText().contains("New York"));
    }
    @Test
    public void diffDate() throws ParseException{
        List<WebElement> elements2 = webDriver.findElements(By.xpath("//input[@class='time ampm format12 form-control ui-timepicker-input']"));
        String s1 = elements2.get(0).getAttribute("value");
        String s2 = elements2.get(1).getAttribute("value");
        System.out.println(s1);
        System.out.println(s2);
        //time ampm format12 form-control ui-timepicker-input
        SimpleDateFormat format1 = new SimpleDateFormat("hh:mm aa");
        Date strDate= format1.parse(s1);
        Date strDate1= format1.parse(s2);
        long d=Math.abs(strDate.getTime()-strDate1.getTime());
        Assert.assertEquals(d/60000,630);
        System.out.println(Math.abs((strDate.getTime()-strDate1.getTime())/60000));
    }
    @Test
    public void dateCheck() throws InterruptedException {
        List<WebElement> elements = webDriver.findElements(By.xpath("//span[@class='input-group-addon']"));
        elements.get(0).click();
        //Thread.sleep(2000);
        List<WebElement> datele = webDriver.findElements(By.xpath("//th[@class='datepicker-switch']"));
        datele.get(0).click();
        webDriver.findElement(By.xpath("//th[text()='2020']")).click();;
        webDriver.findElement(By.xpath("//span[text()='2022']")).click();
        webDriver.findElement(By.xpath("//span[text()='Jan']")).click();
        webDriver.findElement(By.xpath("//th[text()=\"Th\"]"));
        WebElement ele = webDriver.findElement(By.xpath("//td[text()='8']"));
        ele.click();
        List<WebElement> elements1 = webDriver.findElements(By.xpath("//input[@class='form-control']"));
        System.out.println(elements1.get(0).getAttribute("value"));
       Assert.assertTrue(elements1.get(0).getAttribute("value").contains("Jan 8, 2022"));
       // webDriver.quit();
    }
    @Test
    public void scrollTest() throws ParseException, InterruptedException {
       // Thread.sleep(2000);
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='noUi-handle noUi-handle-lower']"));
        WebElement movement = elements.get(0);
        Actions move = new Actions(webDriver);
        move.dragAndDropBy(movement, 10, 0).build().perform();

        diffDate();
    }
    @Test
    public void scrollTest1() throws ParseException, InterruptedException {
      //  Thread.sleep(2000);
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='noUi-handle noUi-handle-lower']"));
        WebElement movement = elements.get(1);
        Actions move = new Actions(webDriver);
        move.dragAndDropBy(movement, 45, 0).build().perform();
        diffDate();
    }
    @Test
    public void deleteCheck()
    {
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='table-time row']"));
        elements.get(0).click();
        webDriver.findElement(By.xpath("//a[@class='delete-btn btn']")).click();
        WebElement element1 = webDriver.findElement(By.xpath("//h1[@class='title']"));
        Assert.assertFalse(element1.getText().contains("Hyderabad"));
    }
    @Test
    public void permaLinkTest()
    {
        webDriver.findElement(By.xpath("//a[@id='permanent-link']")).click();
        WebElement element = webDriver.findElement(By.xpath("//input[@class='form-control share-url']"));
        System.out.println(element.getAttribute("value"));
        Assert.assertFalse(element.getAttribute("value").contains("https://savvytime.com/converter/ny-new-york-city"));
    }
    @Test
    public void swapTest()
    {
        webDriver.findElement(By.xpath("//a[@class='swap-tz btn']")).click();
        WebElement element = webDriver.findElement(By.xpath("//a[@class='time-abb']"));
        System.out.println(element.getText());
        Assert.assertTrue(element.getText().contains("New York City, New York, USA"));
    }
    @AfterClass
    public void quit()
    {
        webDriver.quit();
    }
}
