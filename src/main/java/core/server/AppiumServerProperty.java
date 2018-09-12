package core.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppiumServerProperty {
    public static Boolean IS_USING_SAUCELAB;
    public static String SAUCE_USERNAME ;
    public static String SAUCE_ACCESS_KEY;
    public static String PLATFORM_NAME;
    public static String PLATFORM_VERSION;
    public static String DEVICE_NAME ;
    public static String BROWSER_NAME;
    public static String APPIUM_HOST ;
    public static String APPIUM_PORT;
    public static String APPIUM_HUB;

    public AppiumServerProperty(){
        setProperties();
    }

    private static void setProperties() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("AppiumServer.properties");
            prop.load(input);
            //Set properties
            IS_USING_SAUCELAB = Boolean.getBoolean(prop.getProperty("is_using_saucelab"));
            SAUCE_USERNAME = prop.getProperty("saucelab.name");
            SAUCE_ACCESS_KEY = prop.getProperty("saucelab.accessKey");
            PLATFORM_NAME = prop.getProperty("platform.name");
            PLATFORM_VERSION = prop.getProperty("platform.version");
            DEVICE_NAME = prop.getProperty("device.name");
            BROWSER_NAME = prop.getProperty("browser.name");
            APPIUM_HOST = prop.getProperty("appium.host");
            APPIUM_PORT = prop.getProperty("appium.port");
            APPIUM_HUB = "http://" + APPIUM_HOST + ":" + APPIUM_PORT +"/wd/hub";

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
