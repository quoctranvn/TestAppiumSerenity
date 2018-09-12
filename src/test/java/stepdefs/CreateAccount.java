package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import webapp.component.LeftMenu;
import webapp.page.CreateAccountPage;
import webapp.page.CreateCustomerPage;

public class CreateAccount {
    private final LeftMenu menu = new LeftMenu();
    private final CreateAccountPage accountPage = new CreateAccountPage();
    private final String existingCustomerID = new CreateCustomerPage().customerID;

    @Steps
    private
    Login loginSteps;

    @Given("^I logged in to home page successfully to create account$")
    public void i_logged_in_to_home_page_successfully_for_account() {
        loginSteps.enterUserAndPass();
        loginSteps.clickLogin();
    }

    @When("^I click on \"New Account\" link$")
    public void i_click_on_link() {
        menu.goToCreateAccountPage();
    }

    @Then("^\"Add New Account\" page will displays$")
    public void page_will_displays() {
        accountPage.checkCreateAccountPageDisplayed();
    }

    @When("^I leave all input fields and click Submit account$")
    public void i_leave_all_input_fields_and_click_Submit_account() {
        accountPage.clickSubmit();
    }

    @Then("^The alert text will displays as \"([^\"]*)\" on Account page$")
    public void the_alert_text_will_displays_as_on_Account_page(String expectedText) {
        accountPage.verifyAlertForAllBlank(expectedText);
    }

    @When("^I enter invalid values into \"Customer id\" and \"Initial deposit\"$")
    public void i_enter_invalid_values_into_and() {
        accountPage.enterAccountInfo("invalidCustomer", "", "invalidInitialDeposit");
    }

    @Then("^The validation messages will display beside these fields$")
    public void the_validation_texts_will_display_beside_these_fields() {
        accountPage.validateErrorMessages("invalidCustomer", "invalidInitialDeposit");
    }

    @When("^I enter valid values into \"Customer id\" and \"Initial deposit\"$")
    public void i_enter_valid_values_into_and() {
        accountPage.enterAccountInfo( existingCustomerID, "Current", "5000000");
    }

    @When("^Click Submit account button$")
    public void click_Submit_account_button() {
        accountPage.clickSubmit();
    }

    @Then("^Success message and New Account ID will display$")
    public void success_message_and_New_Account_ID_will_display() {
        accountPage.verifyAccountCreatedSuccessfully();
    }
}
