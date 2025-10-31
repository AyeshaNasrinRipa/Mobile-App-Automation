package testrunner;

import config.Setup;
import config.SeriesDataSet;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screen.CalcScreen;

public class CalcTestRunner extends Setup {

    CalcScreen screen;

    @BeforeClass
    public void initScreen() {
        screen = new CalcScreen(driver);
    }

    @Test(priority = 1, description = "Test manual series calculation")
    public void testSeriesManual() {
        String series = "100/10*5-10+60";
        screen.calculateSeries(series);
        double result = screen.getResultDouble();
        System.out.println("Manual Series Result: " + result);
        Assert.assertEquals(result, 100, 0.01);
    }

    @Test(priority = 2, description = "Calculate series from CSV", dataProvider = "SeriesData", dataProviderClass = SeriesDataSet.class)
    public void testSeriesFromCSV(String series, double expected) {
        screen.calculateSeries(series);
        double result = screen.getResultDouble();
        System.out.println("CSV Series: " + series + " Result: " + result + " Expected: " + expected);
        Assert.assertEquals(Math.round(result * 100.0) / 100.0, expected, 0.01);
    }

    @AfterMethod
    public void clearScreenAfterTest() {
        screen.clearScreen();
    }
}
