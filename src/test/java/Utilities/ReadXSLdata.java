package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class ReadXSLdata {

    @DataProvider(name = "testData")
    public String[][] getData(Method method) throws IOException {

        String excelSheetName = method.getName(); //Get the name of the test method

        File file = new File(System.getProperty("user.dir") + "/src/test/resources/TestData/testData.xlsx"); //Create a File object for the Excel file
        FileInputStream fileInputStream = new FileInputStream(file);//Create a FileInputStream to read the Excel file
        Workbook workbook = WorkbookFactory.create(fileInputStream); //Create a Workbook object using the FileInputStream
        Sheet sheetName = workbook.getSheet(excelSheetName); //Get the sheet with the same name as the test method

        int rowRows = sheetName.getLastRowNum(); //Get the number of rows in the sheet
        Row rowCells = sheetName.getRow(0); //Get the first row to determine the number of columns
        int columnCount = rowCells.getLastCellNum(); //Get the number of columns in the sheet

        DataFormatter formatter = new DataFormatter();
        String testData[][] = new String[rowRows][columnCount];

        for (int i = 1; i <= rowRows; i++) { //Loop through the rows, starting from 1 to skip the header
            Row row = sheetName.getRow(i); //Get the current row
            for (int j = 0; j < columnCount; j++) { //Loop through the columns
                Cell cell = row.getCell(j); //Get the current cell
                testData[i - 1][j] = formatter.formatCellValue(cell); //Store the cell value in the testData array, using DataFormatter to handle different cell types
            }
        }


        return testData; //Return the test data array
    }
}


