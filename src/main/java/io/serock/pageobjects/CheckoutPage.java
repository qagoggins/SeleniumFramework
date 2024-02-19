package io.serock.pageobjects;

import io.serock.abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
//    @FindBy(xpath="(//button[contains(@class,'ta-item')])[1]")
//    WebElement orderButton;
//    @FindBy(css="[placeholder='Select Country']")
//    WebElement countryInput;
    @FindBy(css=".action__submit")
    WebElement orderButton;

    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;
    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[1]")
    WebElement selectCountry;
    By results = By.cssSelector(".ta-results");
    public void selectCountry(String countryName) {
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(results);
        selectCountry.click();
    }
    public ConfirmationPage submitOrder() {
        orderButton.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }


}
