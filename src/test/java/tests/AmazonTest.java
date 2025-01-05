package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AmazonHomePage;
import pages.CartPage;
import pages.LoginPage;
import pages.VideoGamesPage;
import utils.JsonDataProviderMapper;

import java.text.ParseException;

import static utils.Constants.*;

public class AmazonTest extends BaseTest {

    LoginPage loginPage;
    AmazonHomePage amazonHomePage;
    VideoGamesPage videoGamesPage;
    CartPage cartPage;
    JsonDataProviderMapper data;

    @BeforeMethod()
    public void setup() {
        loginPage = new LoginPage(driver);
        amazonHomePage = new AmazonHomePage(driver);
        videoGamesPage = new VideoGamesPage(driver);
        cartPage = new CartPage(driver);
        data = new JsonDataProviderMapper(Amazon_DATA_PROVIDER_PATH);
    }

    @Test(priority = 1, description = "check that all items less than 15k added to the cart correctly")
    public void checkThatAllItemsLessThan15kAddedToTheCartCorrectly() throws ParseException {

        loginPage.openAmazonURL(data.getValueOf("URLs", "AmazonURL"))
                .clickLoginIcon()
                .sendTextToPhoneNumberField(data.getValueOf("Credentials", "AhmedEssamPhone"))
                .clickContinueButton()
                .sendTextToPasswordField(data.getValueOf("Credentials", "AhmedEssamPassword"))
                .clickSignInButton();

        amazonHomePage.clickAllMenuIcon()
                .clickSeeAll()
                .clickVideoGames()
                .clickAllVideoGames();

        videoGamesPage.clickFreeShippingFilter()
                .clickNewConditionFilter()
                .sortPriceFromHighToLow()
                .addAllItemsLessThan15KToCart()
                .clickCartIcon();
        softAssert.assertEquals(cartPage.numberOfItemsInTheCart(), videoGamesPage.numberOfItemsAddedToCart(), "Not All items less than 15k is added to the cart");
        softAssert.assertAll();

    }

    @Test(priority = 2, description = "check that total price is calculated correctly")
    public void checkThatTotalPriceIsCalculatedCorrectly() throws ParseException {

        cartPage.clickProceedToBuyButton()
                .clickAddDeliveryInstruction()
                .chooseSecurityGuardOption()
                .clickSaveInstructionsButton()
                .chooseValueOption();
        softAssert.assertEquals(cartPage.cartOrderTotal(), videoGamesPage.totalPriceForItemsAddedToCart(),"Total price in the cart is not calculated correctly");
        softAssert.assertAll();

    }
}
