package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonHomePage extends Page {
    public AmazonHomePage(WebDriver driver) {
        super(driver);
    }

    private final By allMenu = By.id("nav-hamburger-menu");
    private final By seeAllOption = By.xpath("//a[@aria-label='See All Categories']");
    private final By videoGamesOption = By.xpath("//div[contains(text(), 'Video Games')]");
    private final By allVideoGamesOption = By.xpath("//a[text()='All Video Games']");


    @Step("-click All menu icon")
    public AmazonHomePage clickAllMenuIcon() {
        clickElement(allMenu);
        return this;
    }

    @Step("-click see all")
    public AmazonHomePage clickSeeAll() {
        scrollToElementThenClick(seeAllOption);
        return this;
    }

    @Step("-click video Games")
    public AmazonHomePage clickVideoGames() {
        scrollToElementThenClick(videoGamesOption);
        return this;
    }

    @Step("-click all video games")
    public void clickAllVideoGames() {
        clickElementUsingJS(allVideoGamesOption);
    }
}
