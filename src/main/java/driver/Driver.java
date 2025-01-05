package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Driver {

    protected static ThreadLocal<org.openqa.selenium.WebDriver> driver = new ThreadLocal<>();

    public WebDriver SeleniumDriverSetup(String browser) throws Exception {

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            driver.set(new ChromeDriver(options));
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get().manage().window().maximize();
            return driver.get();
        } else {
            // If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
    }
}
