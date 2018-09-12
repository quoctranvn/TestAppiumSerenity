package core.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.webdriver.WebDriverFacade;

public class MobilePageObject extends PageObject{
        public MobilePageObject(final WebDriver driver) {
        super(driver, page -> {
            PageFactory
                    .initElements(new AppiumFieldDecorator(((WebDriverFacade) page.getDriver()).getProxiedDriver()), page);
            return true;
        });
    }
}
