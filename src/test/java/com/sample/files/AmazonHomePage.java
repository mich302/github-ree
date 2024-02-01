package com.sample.files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.time.Duration;


public class AmazonHomePage {
    private static final Logger logger = (Logger) LogManager.getLogger(AmazonHomePage.class);
    WebDriver driver;
    String searchBox="//input[@id='twotabsearchtextbox']";
    String searchButton="//input[@id='nav-search-submit-button']";
    int row=0;
    int col=0;
    int i=0,j=0;
    String value;


    public AmazonHomePage(WebDriver driver){
       this.driver=driver;
    }

    //@Test
    public void search(String key){
        //String key=ReadData();
        //System.out.println("mobile pho");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebElement elem1= driver.findElement(By.xpath(searchBox));
        Assert.assertTrue(elem1.isDisplayed(),"SearchBox Not Found");
        elem1.sendKeys(key);
        logger.info("Text entered in search box");
        WebElement elem2=driver.findElement(By.xpath(searchButton));
        Assert.assertTrue(elem2.isDisplayed(),"SearchButton Not Found");
        if(elem2.isDisplayed())
            logger.info("Search Button clicked");
        else
            logger.error("Search Button Not Found");
        elem2.click();
    }

    //@Test
    public void select(String key){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebElement elem3=driver.findElement(By.linkText(key));
        Assert.assertTrue(elem3.isDisplayed(),"WebElement Not Found");
        if(elem3.isDisplayed())
            logger.info("'"+key+"' selected");
        else
            logger.error(key+" Not Found");
        elem3.click();
    }

    //
    // @Test
    public void hoverAndDropDown(String key1,String key2){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Actions actions = new Actions(driver);
        WebElement category=driver.findElement(By.linkText(key1));
        Assert.assertTrue(category.isDisplayed(),"WebElement Not Found");
        actions.moveToElement(category).build().perform();
        if(category.isDisplayed())
            logger.info("Hovered to '"+key1+"'");
        else
            logger.error(key1+" Not Found");
        WebElement dropDown=driver.findElement(By.linkText(key2));
        Assert.assertTrue(dropDown.isDisplayed(),"WebElement Not Found");
        if(dropDown.isDisplayed())
            logger.info("'"+key2+"' clicked");
        else
            logger.error(key2+" Not Found");
        actions.moveToElement(dropDown).click().build().perform();
    }
}
