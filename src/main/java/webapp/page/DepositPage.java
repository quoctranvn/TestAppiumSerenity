package webapp.page;

import core.driver.AndroidDriverFactory;
import io.appium.java_client.android.AndroidDriver;
import jxl.common.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

import static core.BasePage.*;

public class DepositPage {
    private final AndroidDriver driver = new AndroidDriverFactory().getDriver();
    private static String transactionID;

    public DepositPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "accountno")
    private WebElement input_AccountNumber;

    @FindBy(name = "ammount")
    private WebElement input_Amount;

    @FindBy(name = "desc")
    private WebElement input_Description;

    @FindBy(xpath = "//tr[td[text()=\"Transaction ID\"]]/td[2]")
    private WebElement lbl_TransactionID;

    @FindBy(xpath = "//p[contains(text(),\"Transaction details of Deposit for Account\")]")
    private WebElement lbl_TransactionInfo;

    @FindBy(name = "AccSubmit")
    private WebElement btn_Submit;

    public void checkDepositPageDisplayed() {
        verifyElementPresent(input_AccountNumber);
    }

    public void enterDepositInfo(String accountNumber, String amount, String description){
        if(accountNumber == null) {
            accountNumber = "48143"; //Default existing account number for testing
        }
        setText(input_AccountNumber, accountNumber);
        setText(input_Amount, amount);
        setText(input_Description, description);
    }

    public void validateErrorMessages(String accountNumber, String amount) {
        String expectedText = "are not allowed";
        if(!Objects.equals(accountNumber, ""))
            verifyTextContainedByTableRowValue("Account No", expectedText);
        if(!Objects.equals(amount, ""))
            verifyTextContainedByTableRowValue("Amount", expectedText);
    }

    public void verifyAlertForAllBlank(String expectedText){
        verifyAlertText(expectedText);
        driver.switchTo().alert().accept();
    }

    public void clickSubmit() {
        clickJS(btn_Submit);
    }

    public void verifydepositCreatedSuccessfully(String accountNumber){
        if(accountNumber == null) {
            accountNumber = "48143"; //Default existing account number for testing
        }
        verifyElementPresent(lbl_TransactionID);
        Assert.verify(lbl_TransactionInfo.getText().contains(accountNumber));
    }

    private String getTransactionID() {
        transactionID = getTextByTableRowValue("Transaction ID");
        return transactionID;
    }
}
