package io.serock.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.zh_cn.但是;
import io.serock.pageobjects.LandingPage;
import io.serock.pageobjects.ProductCatalogue;
import io.serock.testComponents.BaseTest;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StepDefinitionImpl extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    @Given("I landed on Ecommerce page")
    public void I_landen_on_Ecommerce_page() {
        landingPage = launchApp();
    }

    @Given("^Logged in with email (.+) and password (.+)$")
    public void logged_in_with_email_and_password(String email, String password) {
        productCatalogue = landingPage.loginApplication(email, password);
    }
    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }
//    @When("Checkout <productName> and submit the order")

}
