package core.driver;

import core.server.AppiumServerProperty;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import static core.server.AppiumServerProperty.*;

public class AndroidDriverFactory {
    private static AndroidDriver driver;

    public AndroidDriverFactory() {
        if(driver == null)
            setDriver();
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    private void setDriver(){
        new AppiumServerProperty();//Set values for properties

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BROWSER_NAME);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);

        try {
            if(!IS_USING_SAUCELAB) {
                driver = new AndroidDriver(new URL(APPIUM_HUB), capabilities);
            }
            else {
                driver = new AndroidDriver(new URL(MessageFormat.format("http://{0}:{1}@ondemand.saucelabs.com:{2}/wd/hub"
                        , SAUCE_USERNAME
                        , SAUCE_ACCESS_KEY, APPIUM_PORT))
                        , capabilities);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

