package edu.umich.webpage;

import java.util.Objects;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Data
public class ResourcePage {

    private WebDriver browser;

    /**
     * Use init(WebDriver) instead of this constructor
     *
     * @param browser
     */
    public ResourcePage(WebDriver browser) {
        this.browser = browser;
        Objects.requireNonNull(browser, "Browser should not be null");
    }

    public static ResourcePage init(WebDriver browser) {
        ResourcePage page = PageFactory.initElements(browser, ResourcePage.class);
        return page;
    }

    public ResourcePage validateView() {
        Assertions.assertThat(browser.getPageSource()).contains("details");
        Assertions.assertThat(browser.getPageSource()).contains("remoteAddress");
        Assertions.assertThat(browser).isNotNull();
        return this;
    }
}
