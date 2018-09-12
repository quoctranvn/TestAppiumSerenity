package webapp.page;

import core.driver.AndroidDriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Objects;

import static core.BasePage.*;

public class CreateAccountPage {
    private final AndroidDriver driver = new AndroidDriverFactory().getDriver();
    public static String accountID;

    public CreateAccountPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "cusid")
    private WebElement input_CustomerID;

    @FindBy(xpath = "//select[@name=\"selaccount\"]")
    private WebElement lst_AccountType;

    @FindBy(name = "inideposit")
    private WebElement input_InitialDeposit;

    @FindBy(xpath = "//p[normalize-space(text())=\"Account Generated Successfully!!!\"]")
    private WebElement lbl_AccountGenerated;

    @FindBy(xpath = "//input[@value=\"submit\"]")
    private WebElement btn_Submit;

    public void checkCreateAccountPageDisplayed() {
        verifyElementPresent(input_CustomerID);
    }

    public void enterAccountInfo(String customerID, String accountType, String initialDeposit){
        if(customerID == null) {
            customerID = "69494"; //Default existing customer id for testing
        }
        setText(input_CustomerID, customerID);
        setText(input_InitialDeposit, initialDeposit);
        if(!Objects.equals(accountType, ""))
            new Select(lst_AccountType).selectByVisibleText(accountType);
    }

    public void verifyAlertForAllBlank(String expectedText){
        waitForAlert(5);
        verifyAlertText(expectedText);
        driver.switchTo().alert().accept();
    }

    public void clickSubmit() {
        clickJS(btn_Submit);
    }

    public void verifyAccountCreatedSuccessfully(){
        String successMessage = "Account Generated Successfully!!!";
        verifyTextPresent(successMessage);
        verifyTextPresent(getAccountID());
    }

    public void validateErrorMessages(String customerID, String initialDeposit) {
        String expectedText = "are not allowed";
        if(!Objects.equals(customerID, ""))
            verifyTextContainedByTableRowValue("Customer id", expectedText);
        if(!Objects.equals(initialDeposit, ""))
            verifyTextContainedByTableRowValue("Initial deposit", expectedText);
    }

    private String getAccountID() {
        accountID = getTextByTableRowValue("Account ID");
        return accountID;
    }
}