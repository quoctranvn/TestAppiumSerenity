package stepdefs;

import net.thucydides.core.annotations.Step;
import webapp.page.LoginPage;

public class Login {
    private final LoginPage loginPage = new LoginPage();

    @Step
    public void enterUserAndPass(){
        loginPage.enterUserAndPass("mngr149553", "qadenYt");
    }

    @Step
    public void clickLogin(){ loginPage.clickSubmit(); }

}
