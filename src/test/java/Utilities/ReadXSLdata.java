package Utilities;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class ReadXSLdata {

    @DataProvider(name = "testData")
    public String[][] getData(Method method) {
        String excelSheetName = method.getName(); //Get the name of the test method

    }

}
