package webapp.page;

import core.driver.AndroidDriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.BasePage.setText;

public class LoginPage{
    private final AndroidDriver driver = new AndroidDriverFactory().getDriver();

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name=\"uid\"]")
    private WebElement input_User;

    @FindBy(xpath = "//input[@name=\"password\"]")
    private WebElement input_Password;

    @FindBy(xpath = "//input[@name=\"btnLogin\"]")
    private WebElement btn_Login;

    public void enterUserAndPass(String userName, String passWord) {
        setText(input_User, userName);
        setText(input_Password, passWord);
    }

    public void clickSubmit() {
        btn_Login.submit();
    }
}


