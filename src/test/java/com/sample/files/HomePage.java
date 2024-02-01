package com.sample.files;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.AssertJUnit.assertEquals;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void verifyHeader() {
        String headerText = driver.getTitle();
        assertEquals("Most Reliable App & Cross Browser Testing Platform | BrowserStack", headerText);
    }

    public void clickOnGetStarted(){
        driver.findElement(By.xpath("//a[@id='signupModalProductButton']")).click();
    }
}

