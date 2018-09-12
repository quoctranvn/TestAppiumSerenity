import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static core.server.AppiumServerController.startAppiumServer;
import static core.server.AppiumServerController.stopAppiumServer;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"hooks", "stepdefs"},
        plugin = { "pretty", "html:target/cucumber-html-reports"}
)
public class TestRunner {
    @BeforeClass
    public static void startAppium() { startAppiumServer(); }

    @AfterClass
    public static void stopAppium() {
        stopAppiumServer();
    }
}
