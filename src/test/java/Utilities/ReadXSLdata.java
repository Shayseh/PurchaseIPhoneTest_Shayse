package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadXSLdata {

    private static final String TEST_DATA_PATH = System.getProperty("user.dir")
            + "/src/test/resources/Testdata/testData.xlsx";

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        return getData("enterLoginDetails");
    }

    @DataProvider(name = "addressData")
    public Object[][] addressData() throws IOException {
        return getData("fillInAddress");
    }

    public Object[][] getData(String sheetName) throws IOException {
        File file = new File(TEST_DATA_PATH);
        DataFormatter formatter = new DataFormatter();

        try (FileInputStream fileInputStream = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet NOT FOUND: " + sheetName);
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Header row missing in sheet: " + sheetName);
            }

            int rowCount = sheet.getLastRowNum();
            int columnCount = headerRow.getLastCellNum();
            Object[][] data = new Object[rowCount][columnCount];

            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < columnCount; j++) {
                    Cell cell = (row == null) ? null : row.getCell(j);
                    data[i - 1][j] = formatter.formatCellValue(cell);
                }
            }

            return data;
        }
    }

}




