package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class ReadXSLdata {


    @DataProvider(name = "testData")
    public String[][] getData(String sheetName) throws IOException {

        File file = new File(System.getProperty("user.dir") + "/src/test/resources/TestData/testData.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(fileInputStream);

        Sheet sheet = workbook.getSheet(sheetName); // ✅ correct

        if (sheet == null) {
            throw new RuntimeException("Sheet NOT FOUND: " + sheetName);
        }

        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();

        String[][] data = new String[rowCount][columnCount];

        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);

            for (int j = 0; j < columnCount; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = formatter.formatCellValue(cell);
            }
        }

        workbook.close();
        return data;
    }

}




