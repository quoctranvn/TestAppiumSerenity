package webapp.page;

import core.driver.AndroidDriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static core.BasePage.*;

public class CreateCustomerPage {
    private final AndroidDriver driver = new AndroidDriverFactory().getDriver();
    public static String customerID;

    public CreateCustomerPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "name")
    private WebElement input_CustomerName;

    @FindBy(xpath = "//input[@value=\"f\"]")
    private WebElement rdo_FemaleGender;

    @FindBy(xpath = "//input[@value=\"m\"]")
    private WebElement rdo_MaleGender;

    @FindBy(id = "dob")
    private WebElement input_DoB;

    @FindBy(name = "addr")
    private WebElement txa_Address;

    @FindBy(name = "city")
    private WebElement input_City;

    @FindBy(name = "state")
    private WebElement input_State;

    @FindBy(name = "pinno")
    private WebElement input_PIN;

    @FindBy(name = "telephoneno")
    private WebElement input_Mobile;

    @FindBy(name = "emailid")
    private WebElement input_Email;

    @FindBy(name = "password")
    private WebElement input_Password;

    @FindBy(name = "sub")
    private WebElement btn_Submit;

    public void checkCreateCustomerPageDisplayed() {
        verifyElementPresent(input_CustomerName);
    }

    public void verifyAlertForAllBlank(String expectedText){
        verifyAlertText(expectedText);
        driver.switchTo().alert().accept();
    }

    private void selectDateOfBirth(String dob) {
        if(!Objects.equals(dob, "")) {
            //Set date of birth value and mark validation message to be hidden
            driver.executeScript("document.getElementById('dob').setAttribute('value', arguments[0])", dob);
            driver.executeScript(" document.getElementById('message24').style.visibility=\"hidden\"");
        }
    }

    private void selectGender(String gender) {
        if(gender != null) {
            if (Objects.equals(gender.toLowerCase(), "male")) {
                if (rdo_MaleGender.getAttribute("checked") != null)
                    rdo_MaleGender.click();
            } else {
                if (rdo_FemaleGender.getAttribute("checked") != null)
                    rdo_FemaleGender.click();
            }
        }
    }

    public void enterValidNameAndEmail(String customerName, String email){
        DateFormat sdf = new SimpleDateFormat("HHmmss");
        String uniqueValue = sdf.format(new Date());
        String randomName = RandomStringUtils.randomAlphabetic(10);
        setText(input_CustomerName, randomName);//Use random string to avoid duplicate values
        setText(input_Email, email + uniqueValue +"@gmail.com");//Use timeStamp to avoid duplicate values
    }

    public void enterCustomerInfo(String customerName, String gender, String doB, String address, String city, String state, String pin, String phone, String email, String password) {
        setText(input_CustomerName, customerName);
        selectGender(gender);
        selectDateOfBirth(doB);
        setText(txa_Address, address);
        setText(input_City, city);
        setText(input_State, state);
        setText(input_PIN, pin);
        setText(input_Mobile, phone);
        setText(input_Email, email);
        setText(input_Password, password);
    }

    public void validateErrorMessages(String customerName, String city, String state, String pin, String phone, String email) {
        String expectedText = "are not allowed";
        if(!Objects.equals(customerName, ""))
            verifyTextContainedByTableRowValue("Customer Name", expectedText);
        if(!Objects.equals(city, ""))
            verifyTextContainedByTableRowValue("City", expectedText);
        if(!Objects.equals(state, ""))
            verifyTextContainedByTableRowValue("State", expectedText);
        if(!Objects.equals(pin, ""))
            verifyTextContainedByTableRowValue("PIN", expectedText);
        if(!Objects.equals(phone, ""))
            verifyTextContainedByTableRowValue("Mobile Number", expectedText);
            if(!Objects.equals(email, ""))
            verifyTextContainedByTableRowValue("E-mail", "is not valid");
    }

    public void clickSubmit() {
        clickJS(btn_Submit);
    }

    public void verifyCustomerInfo(){
        String successMessage = "Customer Registered Successfully!!!";
        verifyTextPresent(successMessage);
        verifyTextPresent(getCustomerID());
    }

    private String getCustomerID() {
        customerID = getTextByTableRowValue("Customer ID");
        return customerID;
    }
}
