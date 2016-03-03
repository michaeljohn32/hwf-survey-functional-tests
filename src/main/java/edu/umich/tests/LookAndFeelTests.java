package edu.umich.tests;

import edu.umich.runner.AfterFunctionalTest;
import edu.umich.runner.BeforeFunctionalTest;
import edu.umich.runner.FunctionalTest;
import edu.umich.webpage.Browser;
import edu.umich.webpage.SurveyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LookAndFeelTests {

    @Value("${survey.site.url}")
    private String url;
    @Autowired
    private Browser browser;

    @BeforeFunctionalTest
    public void beforeEachTest() {
        browser.start();
        browser.timeToWaitForPagesToRender(5);
    }

    @AfterFunctionalTest
    public void afterEachTest() {
        browser.quit();
    }

    @FunctionalTest
    public void givenLandingOnSurveyPage_shouldSeeGreetingMessage() throws Exception {
        System.out.println("testing against " + url);
        browser.get(url);
        SurveyPage
                .init(browser)
                .validateView();
    }
}
