import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.junit.jupiter.api.Assertions.assertEquals;

class HomePageTests {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void openHomePageTest() {
        String actualTitle = driver.getTitle();

        assertEquals("Hands-On Selenium WebDriver with Java", actualTitle);
    }

    @ParameterizedTest
    @DisplayName("Проверка открытия страниц")
    @CsvSource({
            "Chapter 3. WebDriver Fundamentals, web-form.html, Web form",
            "Chapter 3. WebDriver Fundamentals, navigation1.html, Navigation example",
            "Chapter 3. WebDriver Fundamentals, dropdown-menu.html, Dropdown menu",
            "Chapter 3. WebDriver Fundamentals, mouse-over.html, Mouse over",
            "Chapter 3. WebDriver Fundamentals, drag-and-drop.html, Drag and drop",
            "Chapter 3. WebDriver Fundamentals, draw-in-canvas.html, Drawing in canvas",
            "Chapter 3. WebDriver Fundamentals, loading-images.html, Loading images",
            "Chapter 3. WebDriver Fundamentals, slow-calculator.html, Slow calculator",
            "Chapter 4. Browser-Agnostic Features, long-page.html, This is a long page",
            "Chapter 4. Browser-Agnostic Features, infinite-scroll.html, Infinite scroll",
            "Chapter 4. Browser-Agnostic Features, shadow-dom.html, Shadow DOM",
            "Chapter 4. Browser-Agnostic Features, cookies.html, Cookies",
            "Chapter 4. Browser-Agnostic Features, iframes.html, IFrame",
            "Chapter 4. Browser-Agnostic Features, dialog-boxes.html, Dialog boxes",
            "Chapter 4. Browser-Agnostic Features, web-storage.html, Web storage",
            "Chapter 5. Browser-Specific Manipulation, geolocation.html, Geolocation",
            "Chapter 5. Browser-Specific Manipulation, notifications.html, Notifications",
            "Chapter 5. Browser-Specific Manipulation, get-user-media.html, Get user media",
            "Chapter 5. Browser-Specific Manipulation, multilanguage.html,",
            "Chapter 5. Browser-Specific Manipulation, console-logs.html, Console logs",
            "Chapter 7. The Page Object Model (POM), login-form.html, Login form",
            "Chapter 7. The Page Object Model (POM), login-slow.html, Slow login form",
            "Chapter 8. Testing Framework Specifics, random-calculator.html, Random calculator",
            "Chapter 9. Third-Party Integrations, download.html, Download files",
            "Chapter 9. Third-Party Integrations, ab-testing.html, A/B Testing",
            "Chapter 9. Third-Party Integrations, data-types.html, Data types",
    })

    void openPageTest(String nameChapter, String path, String expectedTitle) {
        if (expectedTitle == null) {
            expectedTitle = "";
        }
        driver.findElement(By.xpath("//h5[contains(text(),'" + nameChapter +"')]/following-sibling::a[@href='" + path + "']")).click();
        String currentUrl = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));

        assertEquals(BASE_URL + path, currentUrl,"URL of opened page does not match");
        assertEquals(expectedTitle, title.getText(), "Title of opened page does not match");
    }

    @ParameterizedTest
    @DisplayName("Проверка открытия страниц")
    @CsvSource({
            "Chapter 4. Browser-Agnostic Features, frames.html, Frames"
    })

    void openPageWithFrameTest(String nameChapter, String path, String expectedTitle) {
        driver.findElement(By.xpath("//h5[contains(text(),'" + nameChapter +"')]/following-sibling::a[@href='" + path + "']")).click();
        String currentUrl = driver.getCurrentUrl();
        WebElement iframeElement = driver.findElement(By.cssSelector("frame[src='header.html']"));
        driver.switchTo().frame(iframeElement);
        WebElement title = driver.findElement(By.className("display-6"));

        assertEquals(BASE_URL + path, currentUrl,"URL of opened page does not match");
        assertEquals(expectedTitle, title.getText(), "Title of opened page does not match");
    }

}
