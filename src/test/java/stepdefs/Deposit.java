package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import webapp.component.LeftMenu;
import webapp.page.CreateAccountPage;
import webapp.page.DepositPage;

public class Deposit {
    private final LeftMenu menu = new LeftMenu();
    private final DepositPage depositPage = new DepositPage();
    private final String existingAccountID = new CreateAccountPage().accountID;

    @Steps
    private
    Login loginSteps;

    @Given("^I logged in to home page successfully to create deposit$")
    public void i_logged_in_to_home_page_successfully_for_deposit() {
        loginSteps.enterUserAndPass();
        loginSteps.clickLogin();
    }

    @When("^I click on Deposit link$")
    public void i_click_on_Deposit_link() {
        menu.goToCreateDepositPage();
    }

    @Then("^\"Amount Deposit Form\" page will displays$")
    public void page_will_displays() {
        depositPage.checkDepositPageDisplayed();
    }

    @When("^I leave all input fields and click Submit deposit$")
    public void i_leave_all_input_fields_and_click_Submit_deposit() {
        depositPage.clickSubmit();
    }

    @Then("^The alert text will displays as \"([^\"]*)\" on Deposit page$")
    public void the_alert_text_will_displays_as_on_Deposit_page(String expectedText) {
        depositPage.verifyAlertForAllBlank(expectedText);
    }

    @When("^I enter invalid values into \"Account No\" and Amount$")
    public void i_enter_invalid_values_into_and() {
        depositPage.enterDepositInfo("invalidAccount", "invalidAmount", "");
    }

    @Then("^The validation messages will display beside these fields on Deposit page$")
    public void the_validation_messages_will_display_beside_these_fields_on_Deposit_page() {
        depositPage.validateErrorMessages("invalidAccount", "invalidAmount");
    }

    @When("^I enter valid values into \"Account No\" and Amount$")
    public void i_enter_valid_values_into_and() {
        depositPage.enterDepositInfo(existingAccountID, "5000", "description test deposit");
    }

    @When("^Click Submit deposit button$")
    public void click_Submit_deposit_button() {
        depositPage.clickSubmit();
    }

    @Then("^Success message and New Transaction ID will display$")
    public void success_message_and_New_Transaction_ID_will_display() {
        depositPage.verifydepositCreatedSuccessfully(existingAccountID);
    }
}
