package org.prog.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.prog.driver.DriverFacade;
import org.prog.pages.FirstPage;
import org.prog.pages.SecondPage;
import org.prog.pages.elements.AndroidElement;
import org.testng.Assert;

public class AndroidSteps {

    private final DriverFacade facade = DriverFacade.getInstance();
    private final FirstPage firstPage = new FirstPage(facade.getDriver());
    private final SecondPage secondPage = new SecondPage(facade.getDriver());

    @Given("First Page is loaded")
    public void firstPageIsLoaded() {
        Assert.assertTrue(firstPage.isPageLoaded());
    }

    @When("I click on element with text {string}")
    public void iClickOnButton(String buttonName) {
        firstPage.getElementWithText("*", buttonName).click();
    }

    @When("I click on {string} button")
    public void clickEmail(String email) {
        firstPage.clickEmail();
    }

    @Then("I see second text with value {string}")
    public void iSeeSecondTextWithValue(String textValue) {
        Assert.assertEquals(secondPage.getTextContent(), textValue, "Text value mismatch for page 2");
    }

    @Then("I see first text with value {string}")
    public void iSeeFirstTextWithValue(String textValue) {
        Assert.assertEquals(firstPage.getTextContent(), textValue, "Text value mismatch for page 1");
    }

    @Then("I see popup with text {string}")
    public void iSeePopupWithText(String popupValue) {
        firstPage.waitForPopup(popupValue);
    }

    @When("I do some stuff in WidgetOne")
    public void someWidgetAction() {
        firstPage.onWidgetOne().anotherWidgetAction();
    }

    @Given("i set {} element value to {string}")
    public void iSetLoginInputTo(AndroidElement element, String value) {
        firstPage.setFieldValue(element, value);
    }

    @When("i click login button")
    public void iClickLoginButton() {
        firstPage.clickLoginBtn();
    }

    @When("check number {int}")
    public void smth(int param){

    }
}
