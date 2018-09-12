import core.server.AppiumServerProperty;

import static core.server.AppiumServerProperty.*;

/**
 * Created by quoctran on 8/19/2018.
 */
public class debug {
    public static void main(String[] args) {
        new AppiumServerProperty();
        System.out.println(IS_USING_SAUCELAB);
        System.out.println(SAUCE_USERNAME);
        System.out.println(SAUCE_ACCESS_KEY);
        System.out.println(PLATFORM_NAME);
        System.out.println(PLATFORM_VERSION);
        System.out.println(DEVICE_NAME);
        System.out.println(BROWSER_NAME);
        System.out.println(APPIUM_HOST);
        System.out.println(APPIUM_PORT);
        System.out.println(APPIUM_HUB);
    }
}
