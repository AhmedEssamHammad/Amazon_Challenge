package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By loginIcon = By.id("nav-link-accountList");
    private final By phoneField = By.id("ap_email");
    private final By continueButton = By.id("continue");
    private final By passwordField = By.id("ap_password");
    private final By signInButton = By.id("signInSubmit");


    @Step("-click login button")
    public LoginPage openAmazonURL(String url) {
        openURL(url);
        return this;
    }

    @Step("-click login Icon")
    public LoginPage clickLoginIcon() {
        clickElement(loginIcon);
        return this;
    }

    @Step("-send text to phone number")
    public LoginPage sendTextToPhoneNumberField(String phone) {
        sendText(phoneField, phone);
        return this;
    }

    @Step("-click continue button")
    public LoginPage clickContinueButton() {
        clickElement(continueButton);
        return this;
    }

    @Step("-send text to password field")
    public LoginPage sendTextToPasswordField(String password) {
        sendText(passwordField, password);
        return this;
    }

    @Step("-click sign in button")
    public void clickSignInButton() {
        clickElement(signInButton);
    }


}
