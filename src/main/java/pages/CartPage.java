package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;

public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private final By subTotalLabel = By.xpath("//span[@id='sc-subtotal-label-activecart']");
    private final By proceedToBuyButton = By.id("sc-buy-box-ptc-button");
    private final By valueOption = By.xpath("(//div[@class='a-fixed-left-grid-col pmts-instrument-description a-col-right'])[1]");
    private final By AddDeliveryInstructions = By.xpath("//span[@data-action='a-modal']");
    private final By leavePackageWithSecurityOption = By.cssSelector("input[value='SECURITY_GUARD'][name='preferredDeliveryLocationAPARTMENT']");
    private final By saveInstructionsButton = By.id("cdp-save-button-announce");
    private final By orderTotal = By.xpath("//td[@class='a-color-base a-size-medium a-text-right grand-total-price aok-nowrap a-text-bold a-nowrap']");


    @Step("-click proceed to buy button")
    public CartPage clickProceedToBuyButton() {
        clickElement(proceedToBuyButton);
        return this;
    }

    @Step("-click add delivery instruction")
    public CartPage clickAddDeliveryInstruction() {
        clickElement(AddDeliveryInstructions);
        return this;
    }

    @Step("-choose security guard option")
    public CartPage chooseSecurityGuardOption() {
        clickElement(leavePackageWithSecurityOption);
        return this;
    }

    @Step("-click save instructions option")
    public CartPage clickSaveInstructionsButton() {
        scrollToElementThenClickUsingJS(saveInstructionsButton);
        return this;
    }

    @Step("-choose value option")
    public void chooseValueOption() {
        scrollToElementThenClick(valueOption);
    }

    @Step("-get Cart order total")
    public int cartOrderTotal() throws ParseException {
        return getDecimalPriceAsInteger(orderTotal);
    }

    public int numberOfItemsInTheCart() {

        String[] parts = getElementText(subTotalLabel).split("\\(");
        String numberString = parts[1].split(" ")[0];
        return Integer.parseInt(numberString);
    }


}
