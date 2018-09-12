package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import webapp.component.LeftMenu;
import webapp.page.CreateCustomerPage;

public class CreateCustomer {
    private final LeftMenu menu = new LeftMenu();
    private final CreateCustomerPage customerPage = new CreateCustomerPage();

    @Steps
    private
    Login loginSteps;

    @Given("^I logged in to home page successfully to create customer$")
    public void i_logged_in_to_home_page_successfully_for_customer() {
        loginSteps.enterUserAndPass();
        loginSteps.clickLogin();
    }

    @When("^I click on \"New Customer\" link$")
    public void i_click_on_link() {
        menu.goToCreateCustomerPage();
    }

    @Then("^\"Add New Customer\" page will displays$")
    public void page_will_displays() {
        customerPage.checkCreateCustomerPageDisplayed();
    }

    @When("^I leave all input fields and click Submit customer$")
    public void i_leave_all_input_fields_and_click_Submit() {
        customerPage.clickSubmit();
    }

    @Then("^The alert text will displays as \"([^\"]*)\" on Customer page$")
    public void the_alert_text_will_displays_as(String expectedText) {
        customerPage.verifyAlertForAllBlank(expectedText);
    }

    @When("^I enter invalid values into some fields$")
    public void i_enter_invalid_values_for_each_field() {
        customerPage.enterCustomerInfo("invalidCustomer123", "", ""
                                ,"", "invalidCity123", "invalidState123", "invalidPin"
                                ,"invalidPhone", "invalidEmail", "");
    }

    @Then("^The validation messages will display beside associate fields$")
    public void the_validation_texts_will_display_beside_associate_fields() {
        customerPage.validateErrorMessages("invalidCustomer123", "invalidCity123", "invalidState123"
                ,"invalidPin", "invalidPhone", "invalidEmail");
    }

    @When("^I enter valid values into all fields$")
    public void i_enter_valid_values_to_all_fields() {
        //Please be note that <dob> has to follow format <YYYY-MM-DD>
        customerPage.enterCustomerInfo("", "male", "1990-10-05"
                ,"test address", "HCM", "Binh Thanh", "123456"
                ,"0123456789", "", "123456");

        customerPage.enterValidNameAndEmail("name", "email");//To avoid duplicate data
    }

    @When("^Click Submit customer button$")
    public void click_Submit_button() {
        customerPage.clickSubmit();
    }

    @Then("^Success message and New customer ID will display$")
    public void success_message_and_New_customer_ID_will_display() {
        customerPage.verifyCustomerInfo();
    }
}
