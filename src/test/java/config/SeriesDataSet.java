package config;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SeriesDataSet {

    @DataProvider(name = "SeriesData")
    public Object[][] getCSVData() throws IOException {
        List<Object[]> data = new ArrayList<>();

        CSVParser csvParser = new CSVParser(
                new FileReader("./src/test/resources/data.csv"),
                CSVFormat.DEFAULT.withFirstRecordAsHeader()
        );

        for (CSVRecord csvRecord : csvParser) {
            String series = csvRecord.get("series");
            double expected = Double.parseDouble(csvRecord.get("expected"));
            data.add(new Object[]{series, expected});
        }

        return data.toArray(new Object[0][]);
    }
}
