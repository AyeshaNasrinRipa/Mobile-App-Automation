package screen;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static config.Setup.PACKAGE_ID;

public class CalcScreen {

    @FindBy(id = PACKAGE_ID + ":id/op_add")
    WebElement btnPlus;

    @FindBy(id = PACKAGE_ID + ":id/op_sub")
    WebElement btnMinus;

    @FindBy(id = PACKAGE_ID + ":id/eq")
    WebElement btnEqual;

    @FindBy(id = PACKAGE_ID + ":id/result_final")
    WebElement txtResult;

    @FindBy(id = PACKAGE_ID + ":id/clr")
    public WebElement btnClear;

    AndroidDriver driver;

    public CalcScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickChar(String ch) {
        switch (ch) {
            case "+": btnPlus.click(); break;
            case "-": btnMinus.click(); break;
            case "*": driver.findElement(By.id(PACKAGE_ID + ":id/op_mul")).click(); break;
            case "/": driver.findElement(By.id(PACKAGE_ID + ":id/op_div")).click(); break;
            case "=": btnEqual.click(); break;
            case ".": driver.findElement(By.id(PACKAGE_ID + ":id/dec_point")).click(); break;
            case "pi": driver.findElement(By.id(PACKAGE_ID + ":id/const_pi")).click(); break;
            case "^": driver.findElement(By.id(PACKAGE_ID + ":id/op_pow")).click(); break;
            default: driver.findElement(By.id(PACKAGE_ID + ":id/digit_" + ch)).click();
        }
    }

    public void calculateSeries(String series) {
        for (int i = 0; i < series.length(); i++) {
            char c = series.charAt(i);
            if (c == 'p' && i + 1 < series.length() && series.charAt(i + 1) == 'i') {
                clickChar("pi");
                i++;
            } else {
                clickChar(String.valueOf(c));
            }
        }
        btnEqual.click();
    }

    public double getResultDouble() {
        return Double.parseDouble(txtResult.getText());
    }

    public void clearScreen() {
        btnClear.click();
    }
}
