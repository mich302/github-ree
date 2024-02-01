package com.sample.files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AmazonSearchResults {
   WebDriver driver;
   private static final Logger logger = (Logger) LogManager.getLogger(AmazonSearchResults.class);

   String addToCartButton="//*[@id=\"add-to-cart-button\"]";

   public AmazonSearchResults(WebDriver driver){
      this.driver=driver;
   }


   //@Test
      public void partialLink (String key) {
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebElement elem4 = driver.findElement(By.partialLinkText(key));
            //System.out.println(elem4);
            if ((elem4.getText()).equals(key))
               logger.info("'" + key + "' filter applied");
            else
               logger.error(key + " Not Found");
            String string1 = elem4.getText();
            Assert.assertEquals(string1, key, "WebElement Not Found");
            elem4.click();
      }

   //@Test
   public void checkBox(String key){
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
      WebElement elem5=driver.findElement(By.partialLinkText(key));
      Assert.assertTrue(elem5.isEnabled(),"WebElement Not Intractable");
      if(elem5.isEnabled())
         logger.info("'"+key+"' filter applied");
      else
         logger.error(key+" Not Intractable");
      elem5.click();
   }

   //@Test
   public void addToCart(Integer tab, String key){
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
      if(tab==1){
      List<WebElement> productlist= driver.findElements(By.xpath(key));

      if(!productlist.isEmpty()){
         WebElement product=productlist.get(0);
         product.click();
      }
         String parent = driver.getWindowHandle();
         List<String> windows = new ArrayList<String>(driver.getWindowHandles());
         driver.switchTo().window(windows.get(1));
      }
      else{
         List<WebElement> productList = driver.findElements(By.xpath(key));
         if(!productList.isEmpty()) {
            WebElement product=productList.get(0);
            product.click();
         }
      }
      List<WebElement> buttons=driver.findElements(By.xpath(addToCartButton));
      buttons.get(1).click();
      if((buttons.get(1)).isEnabled())
         logger.info("Item added to cart");
      else
         logger.error("Item not added to cart");
   }
}
