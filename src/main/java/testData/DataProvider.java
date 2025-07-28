package testData;

import Utilities.CSVUtil;
import org.testng.annotations.DataProvider;

public class DataProvider {

    @DataProvider(name = "loginCSVData")
    public Object[][] getCSVData() throws Exception {
        String filePath = "src/test/resources/TestData.csv";
        return CSVUtil.getTestData(filePath);
    }
}
