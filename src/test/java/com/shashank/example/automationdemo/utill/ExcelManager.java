package com.shashank.example.automationdemo.utill;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ExcelManager
{
    public static String readData(String file,String sheetName,int rowIndex,int columnIndex)
    {
        try
        {
            FileInputStream fin = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fin);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rowIndex);
            XSSFCell cell = row.getCell(columnIndex);
            return cell.getStringCellValue();

        }  catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static List<String> readKeywordByTestCase(String file, String sheetName, String testCaseName)
    {
    return  null;
    }
}
