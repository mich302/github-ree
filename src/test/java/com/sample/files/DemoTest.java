package com.sample.files;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class DemoTest {
    HomePage homePage ;
    SignUpPage signUpPage ;

    Details details;
    WebDriver driver;

    @BeforeTest
    public void setup(){
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.browserstack.com/");
    }

    @Test(priority=1)
    public void checkHomePage(){
        homePage= new HomePage(driver);
        homePage.verifyHeader();
        homePage.clickOnGetStarted();
    }

    @Test(dependsOnMethods = "checkHomePage")
    public void checkSignUpPage(){
        signUpPage=new SignUpPage(driver);
        details=new Details();
        details.userCredentials();
        signUpPage.getDetails(details);
        signUpPage.enterDetails();
    }

}