package hooks;

import core.driver.AndroidDriverFactory;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.android.AndroidDriver;
import webapp.component.LeftMenu;

import java.util.concurrent.TimeUnit;

public class CustomHooks {
    private final AndroidDriver driver = new AndroidDriverFactory().getDriver();
    private final String loginURL = "http://demo.guru99.com/v4";
    private final int timeOut = 30;

    @Before
    public void beforeScenario() {
        System.out.println("******** BEFORE SCENARIO ********");
        //Go to Login page
        driver.get(loginURL);
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    @After
    public void afterScenario() {
        System.out.println("******** AFTER SCENARIO ********");
        //Back to Login page
        new LeftMenu().clickLogout();
    }
}
