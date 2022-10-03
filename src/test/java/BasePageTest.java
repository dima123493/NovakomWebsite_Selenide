import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasePageTest {
    private static final String URL = "https://novakom.com.ua";

    @Test
    void openAllUrls() {
        Selenide.open(URL);
        ElementsCollection hrefs = $$x("//div/div[1]/ul/li/a");
        List<String> links = new ArrayList<>();
        //1
        for (SelenideElement href : hrefs) {
            links.add(href.getAttribute("href"));
        }
        links.forEach(Selenide::open);
        //2
        for (String listURL : links) {
            Selenide.open(listURL);
            String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
            assertEquals(currentUrl, listURL);
        }
    }

}