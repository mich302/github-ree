package com.sample.files;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
    WebDriver driver;
    String username,pass,email;
    public SignUpPage(WebDriver driver){
        this.driver=driver;
    }

    public SignUpPage(){
    }
    public void getDetails(Details details){
        username=details.getUserName();
        pass= details.getPass();
        email= details.getEmail();
        System.out.println(username);
    }
    public void enterDetails(){
        driver.findElement(By.xpath("//input[@id='user_full_name']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='user_email_login']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys(email);
        //driver.findElement(By.xpath("//input[@id='tnc_checkbox']")).click();
    }

}
