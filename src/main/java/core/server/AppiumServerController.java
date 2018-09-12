package core.server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import static core.server.AppiumServerProperty.*;
import static io.appium.java_client.service.local.AppiumDriverLocalService.buildService;
import java.io.File;

public class AppiumServerController {
    private final static AppiumDriverLocalService service;
    private final static String appiumJSPath = "C:/Users/quoctran/AppData/Local/Programs/Appium/resources/app/node_modules/appium/build/lib/main.js";

    static {
        new AppiumServerProperty();//Set values for properties

        service = buildService(new AppiumServiceBuilder()
                .withIPAddress(APPIUM_HOST)
                .usingPort(Integer.parseInt(APPIUM_PORT))
                .withAppiumJS(new File(appiumJSPath))
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info"));
    }

    public static void startAppiumServer() {
        try {
            service.start();
        } catch (AppiumServerHasNotBeenStartedLocallyException e){
            e.printStackTrace();
        }
    }

    public static void stopAppiumServer() {
        if(service.isRunning())
            service.stop();
    }
}
