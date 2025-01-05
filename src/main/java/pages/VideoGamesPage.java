package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;

public class VideoGamesPage extends Page{
    public VideoGamesPage(WebDriver driver){super(driver);}


    private final By freeShippingCheckBox = By.xpath("//label[@for='apb-browse-refinements-checkbox_0']//i[@class='a-icon a-icon-checkbox']");
    private final By newCondition = By.xpath("//span[text()='New']");
    private final By sortDropDown = By.id("s-result-sort-select");
    private final By addToCartButton = By.id("add-to-cart-button");
    private final By cartIcon = By.id("nav-cart-count");
    private final By nextResultsPageArrow = By.xpath("//a[text()='Next']");
    private int numberOfCartItems = 0;
    private int totalPrice = 0;

    @Step("-sort price from high to low")
    public VideoGamesPage sortPriceFromHighToLow() {
        clickDropDownItemByVisibleText(sortDropDown, "Price: High to Low");
        return this;
    }

    @Step("-click free shipping filter")
    public VideoGamesPage clickFreeShippingFilter() {
        scrollToElementThenClick(freeShippingCheckBox);
        return this;
    }

    @Step("-click new condition filter")
    public VideoGamesPage clickNewConditionFilter() {
        scrollToElementThenClickUsingJS(newCondition);
        return this;
    }

    @Step("-click new condition filter")
    public VideoGamesPage clickCartIcon() {
        scrollToElementThenClick(cartIcon);
        return this;
    }

    @Step("-Add all items less than 15k to cart")
    public VideoGamesPage addAllItemsLessThan15KToCart() throws ParseException {

        for(int i = 1; i < 8; i++){
            By itemPrice = By.xpath("(//span[@class='a-price-whole'])["+i+"]");
            scrollToElement(itemPrice);
            if(getPriceAsInteger(itemPrice) < 15000){
                clickElement(itemPrice);
                clickElement(addToCartButton);
                driver.navigate().back();
                numberOfCartItems++;
                totalPrice = totalPrice + getPriceAsInteger(itemPrice);
            }
        }
        if (numberOfCartItems < 1){
            scrollToElementThenClick(nextResultsPageArrow);
            addAllItemsLessThan15KToCart();
        }
        return this;
    }

    public int numberOfItemsAddedToCart() {
        return numberOfCartItems;
    }

    public int totalPriceForItemsAddedToCart() {
        return totalPrice;
    }


    //div[@class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1']//span[@class='a-price-whole']

   //div[class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_'+1+'] span[class='a-price-whole']
    //div[@class='a-section a-spacing-micro']//span[@class='a-price-whole']
   // div[class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1'] a[class='a-link-normal s-line-clamp-2 s-link-style a-text-normal']
      //      (//span[contains(text(),'No featured offers available')])[1]

    //div[@class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1']//span[@class='a-price-whole']
}
