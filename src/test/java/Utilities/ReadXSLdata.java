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

        // Fallback to first sheet if the exact sheet name is not found
        if (sheetName == null) {
            if (workbook.getNumberOfSheets() > 0) {
                sheetName = workbook.getSheetAt(0);
            } else {
                throw new RuntimeException("No sheets found in the Excel file!");
            }
        }


        Row rowCells = sheetName.getRow(0); //Get the first row to determine the number of columns


        int totalColumns = rowCells.getLastCellNum(); //Get the total number of columns in the sheet

        DataFormatter formatter = new DataFormatter();//Create a DataFormatter to format cell values as strings

        // Return only 1 row of test data (first data row only)
        String testData[][] = new String[1][totalColumns]; //Create a 2D array with only 1 row of test data

        Row firstDataRow = sheetName.getRow(1); //Get the first data row (row 1, since row 0 is header)
        if (firstDataRow != null) {//Check if the first data row is not null
            for (int j = 0; j < totalColumns; j++) { //Iterate through each cell
                testData[0][j] = formatter.formatCellValue(firstDataRow.getCell(j)); //Format the cell value as a string and store it in the testData array
            }
        }

        return testData; //Return the test data array
    }
}


