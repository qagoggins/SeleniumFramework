package io.serock.tests;

import io.serock.pageobjects.*;
import io.serock.testComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

// 1084702
public class SubmitOrderTest extends BaseTest { // 1084702
//    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void buyProductTest(HashMap<String, String> data) {
//        String countryName = "Kyrgyzstan";
        ProductCatalogue productCatalogue = landingPage.loginApplication(data.get("email"), data.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(data.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(data.get("product"));
        System.out.println(products);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("Kyrgyzstan");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    } //R0hul_bek -> password of practice website

    @Test(dependsOnMethods = {"buyProductTest"})
    public void OrderHistoryTest() {
        ProductCatalogue productCatalogue = landingPage.loginApplication("moldosheripovbaya@gmail.com", "R0hul_bek");
        OrderPage orderPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay("ZARA COAT 3"));
    }



    @DataProvider
    public Object[][] getData() throws IOException {

//        HashMap<String, String> dataset1 = new HashMap<>();
//        dataset1.put("product", "ZARA COAT 3");
//        dataset1.put("email", "moldosheripovbaya@gmail.com");
//        dataset1.put("password", "R0hul_bek");
//
//        HashMap<String, String> dataset2 = new HashMap<>();
//        dataset2.put("product", "ADIDAS ORIGINAL");
//        dataset2.put("email", "moldosheripovbaya@gmail.com");
//        dataset2.put("password", "R0hul_bek");
//        return new Object[][] {{dataset1}, {dataset2}};
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "/src/test/java/io/serock/data/PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }

}
