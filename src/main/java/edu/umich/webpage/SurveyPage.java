package edu.umich.webpage;

import lombok.Data;
import static org.assertj.core.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class SurveyPage {

    @FindBy(tagName = "h3")
    private WebElement greetingsMessageField;

    private WebDriver browser;

    /**
     * Use init(WebDriver) instead of this constructor
     *
     * @param browser
     */
    public SurveyPage(WebDriver browser) {
        this.browser = browser;
        assertThat(browser).isNotNull();
    }

    public static SurveyPage init(WebDriver browser) {
        SurveyPage page = PageFactory.initElements(browser, SurveyPage.class);
        return page;
    }

    public SurveyPage validateView() {
        assertThat(browser).isNotNull();
        assertThat(greetingsMessageField).isNotNull();
        assertThat(greetingsMessageField.getText()).isNotNull();
        assertThat(greetingsMessageField.getText()).isEqualTo("Navigation");
//        assertThat(greetingsMessageField.getText()).isEqualTo("NavigationHello HWF Survey!!!!!!");
        return this;
    }
}
