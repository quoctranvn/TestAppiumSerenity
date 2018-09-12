package webapp.component;

import core.driver.AndroidDriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.PageFactory;

import static core.BasePage.clickByTextValue;

public class LeftMenu {
    private final AndroidDriver driver = new AndroidDriverFactory().getDriver();

    public LeftMenu() {
        PageFactory.initElements(driver, this);
    }

    public void goToCreateCustomerPage() {
        clickByTextValue("New Customer", false);
    }

    public void goToCreateAccountPage() {
        clickByTextValue("New Account", false);
    }

    public void goToCreateDepositPage() {
        clickByTextValue("Deposit", false );
    }

    public void clickLogout() {
        clickByTextValue("Log out", true);
        driver.switchTo().alert().accept();
    }
}
