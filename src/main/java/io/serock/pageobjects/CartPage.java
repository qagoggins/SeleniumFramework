package io.serock.pageobjects;

import io.serock.abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css=".cartSection h3")
    List<WebElement> productsTitles;

    @FindBy(css=".totalRow button")
    WebElement checkoutButton;

    By checkoutButtonBy = By.cssSelector(".totalRow button");
    By productTitlesBy = By.cssSelector(".cartSection h3");
    public Boolean verifyProductDisplay(String productName) {
//        List<WebElement> productTitles = driver.findElements(By.cssSelector(".cartSection h3"));
       // waitForElementToAppear(productTitlesBy);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Boolean match = productsTitles.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        return match;
    }
    public CheckoutPage goToCheckout() {
        waitForElementToBeClickable(checkoutButtonBy);
        driver.findElement(checkoutButtonBy).click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }



}
