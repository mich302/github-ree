package com.sample.files;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExample {
    static int Row;
    public static void main (String[]args) {System.out.println();
    String fileLocation="./src./test./resources/content.xlsx";
    try( XSSFWorkbook wb=new XSSFWorkbook(fileLocation))
    {
        XSSFSheet sheet = wb.getSheetAt(0);
        /*XSSFRow row = sheet.getRow(1);
        XSSFCell cell = row.getCell(0);
        String value = cell.getStringCellValue();
        System.out.println(value);*/


        int rowCount=sheet.getPhysicalNumberOfRows();

        for(int i=0;i<rowCount;i++){
            XSSFRow currentRow=sheet.getRow(i);
            XSSFCell cell = currentRow.getCell(0);
            if((cell.getStringCellValue()).equals("MinimumPrice")) {
                Row = i;
                break;
            }
        }
        //System.out.println("row num: "+Row);
    }
    catch(Exception e){
       System.out.println("error"+e);
    }
    }
}
