package edu.umich.webpage;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Browser implements WebDriver {

    @Value("${browser.vendor}")
    private String browserVendor;
    private WebDriver driver;

    public void start() {
        System.err.println("starting browser...");
        driver = instantiateBrowser(browserVendor);
        Objects.requireNonNull(driver, "browser did not start");
    }

    @Override
    public void quit() {
        driver.quit();
    }

    private WebDriver instantiateBrowser(String vendor) {
        switch (vendor) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "/Users/clementp/tools/selenium/chromedriver");
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            case "htmlunit":
                return new HtmlUnitDriver();
            case "safari":
                System.setProperty("webdriver.safari.noinstall", "true");
                return new SafariDriver();
            default:
                throw new IllegalArgumentException("configuration does not recognize browser vendor " + vendor);
        }
    }

    public void timeToWaitForPagesToRender(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public void waitForJavascriptToComplete() {
        int secondsToWaitBeforeTimeout = 10;
        (new WebDriverWait(driver, secondsToWaitBeforeTimeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });
    }

    public void waitForWebElementToRender() {
        driver.get("http://somedomain/url_that_delays_loading");
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("myDynamicElement")));
    }
    
    public void waitForWebElementToBeClickable() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("someid")));
    }

    @Override
    public void get(String string) {
        driver.get(string);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }
}
