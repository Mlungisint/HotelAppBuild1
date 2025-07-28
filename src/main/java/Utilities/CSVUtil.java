package Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    public static Object[][] getTestData(String filePath) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        List<Object[]> data = new ArrayList<>();

        boolean isFirstRow = true;

        while ((line = reader.readLine()) != null) {
            if (isFirstRow) {
                isFirstRow = false; // skip header
                continue;
            }
            String[] row = line.split(",");
            data.add(row);
        }

        reader.close();
        return data.toArray(new Object[0][0]);
    }
}
