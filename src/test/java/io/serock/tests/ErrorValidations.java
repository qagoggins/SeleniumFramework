package io.serock.tests;

import io.serock.pageobjects.CartPage;
import io.serock.pageobjects.ProductCatalogue;
import io.serock.testComponents.BaseTest;
import io.serock.testComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ErrorValidations extends BaseTest {


    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() {
        landingPage.loginApplication("moldosheripovbaya@gmail.com", "R0hul_");
        Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
    } // Incorrect email or password. -> right text(just in case)

    @Test
    public void ProductErrorValidation() {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("moldosheripovbaya@gmail.com", "R0hul_bek");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }
}
