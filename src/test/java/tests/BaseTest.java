package tests;

import driver.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.asserts.SoftAssert;

public class BaseTest {

    public WebDriver driver;
    public SoftAssert softAssert;

    @BeforeClass()
    void beforeClass(@Optional("chrome") String browser) throws Exception {
        Driver testInit = new Driver();
        driver = testInit.SeleniumDriverSetup(browser);
    }

    @BeforeMethod()
    void beforeMethod() {
        softAssert = new SoftAssert();
    }


    @AfterClass()
    void afterClass() {
        driver.quit();
    }
}
