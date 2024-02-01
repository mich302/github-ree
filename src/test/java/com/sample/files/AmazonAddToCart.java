package com.sample.files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

//Test Scenario: Amazon add to cart
public class AmazonAddToCart {
    AmazonHomePage amazonHomePage;
    AmazonSearchResults amazonSearchResults;
    String priceAmt="//span[@class='a-price-whole']";
    String amazonChoice="//div[@class='a-row a-badge-region']/span[@id='B0CN2LTL18-amazons-choice']";
    private static final Logger logger = (Logger) LogManager.getLogger(AmazonAddToCart.class);
    WebDriver driver;
    int i,j;
    int col,row;
    public String ReadData(String rowData,String colData){
        String fileLocation="./src./test./resources/testdata.xlsx";
        logger.info("Reading data from excel");
        try( XSSFWorkbook wb=new XSSFWorkbook(fileLocation))
        {
            XSSFSheet sheet = wb.getSheetAt(0);

            int rowCount=sheet.getPhysicalNumberOfRows();
            int colCount=sheet.getRow(0).getPhysicalNumberOfCells();

            for(i=0;i<rowCount;i++){
                XSSFRow currentRow=sheet.getRow(i);
                XSSFCell currentCell = currentRow.getCell(0);
                if((currentCell.getStringCellValue()).equals(rowData)) {
                    row = i;
                    break;
                }
            }
            for(j=1;j<colCount;j++){
                XSSFRow currentRow = sheet.getRow(0);
                XSSFCell currentCell = currentRow.getCell(j);
                if((currentCell.getStringCellValue()).equals(colData)) {
                    col = j ;
                    break;
                }
            }
            XSSFRow Row=sheet.getRow(row);

            XSSFCell Cell = Row.getCell(col);

            String value =Cell.getStringCellValue();

            return value;
        }
        catch(Exception e){
            logger.error("Unable to read data from excel");
            return "error";
        }

    }

    @BeforeMethod
    public void start(){
            driver=new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://www.amazon.in/");
            driver.manage().window().maximize();
        }

    @Test(enabled = true)
    public void mobilePhonePriceFilter() throws InterruptedException {
        logger.info("Browser opened");
        amazonHomePage = new AmazonHomePage(driver);
        amazonSearchResults=new AmazonSearchResults(driver);
        String searchData=ReadData("MinimumPrice","SearchBox");
        amazonHomePage.search(searchData);
        Thread.sleep(5000);
        String priceData=ReadData("MinimumPrice","PriceFilter");
        amazonSearchResults.partialLink(priceData);
        Thread.sleep(5000);
        amazonSearchResults.addToCart(1,priceAmt);
        Thread.sleep(5000);
        logger.info("Browser closed");
    }

    @Test(enabled = true)
    public void tvBrandAndSizeFilter() throws InterruptedException {
        //System.out.println("t2");
        logger.info("Browser opened");
        amazonHomePage = new AmazonHomePage(driver);
        amazonSearchResults = new AmazonSearchResults(driver);
        String searchData=ReadData("Amazon'sChoiceTag","SearchBox");
        amazonHomePage.search(searchData);
        String brandData=ReadData("Amazon'sChoiceTag","Brand");
        amazonSearchResults.checkBox(brandData);
        Thread.sleep(5000);
        String sizeData=ReadData("Amazon'sChoiceTag","Size");
        amazonSearchResults.checkBox(sizeData);
        Thread.sleep(5000);
        amazonSearchResults.addToCart(0,amazonChoice);
        Thread.sleep(5000);
        logger.info("Browser closed");
    }

    @Test(enabled = true)
    public void laptopsPriceFilter() throws InterruptedException {
        logger.info("Browser opened");
        amazonHomePage = new AmazonHomePage(driver);
        amazonSearchResults = new AmazonSearchResults(driver);
        String searchData=ReadData("ThinLaptops","Choose in HomePage");
        amazonHomePage.select(searchData);
        String categoryData=ReadData("ThinLaptops","HoverTo");
        String dropDownData=ReadData("ThinLaptops","DropDown");
        amazonHomePage.hoverAndDropDown(categoryData,dropDownData);
        String priceData=ReadData("ThinLaptops","PriceFilter");
        amazonSearchResults.partialLink(priceData);
        Thread.sleep(5000);
        logger.info("Browser closed");
    }

    @AfterMethod
    public void end(){
        driver.quit();
    }
}
