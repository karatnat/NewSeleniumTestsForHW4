package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebFormPageTests {
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
    void openWebFormPageTest() {
        driver.findElement(By.xpath("//a[contains(@href, 'web-form')]")).click();
        WebElement actualTitle = driver.findElement(By.className("display-6"));

        assertEquals("Web form", actualTitle.getText(), "Название страницы не совпадает");
    }

    @Test
    void checkHeaderWebFormPageTest() {
        WebElement actualHeaderTitle = driver.findElement(By.cssSelector(".display-4"));
        WebElement actualTitleBelow = driver.findElement(By.xpath("//h1/following-sibling::h5"));
        WebElement topIcon = driver.findElement(By.cssSelector("img[src='img/hands-on-icon.png']"));

        assertEquals("Hands-On Selenium WebDriver with Java", actualHeaderTitle.getText(), "Название не совпадает");
        assertEquals("Practice site", actualTitleBelow.getText(), "Название не совпадает");
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/img/hands-on-icon.png", topIcon.getAttribute("src"), "Картинка не совпадает");
    }

    @Test
    void checkWebFormPageElementsFieldsTest() {
        driver.findElement(By.xpath("//a[contains(@href, 'web-form')]")).click();
        WebElement actualTitle = driver.findElement(By.xpath("//div[@class='col-12']/descendant::h1"));

        WebElement actualNameTextInput = driver.findElement(By.xpath("//label[normalize-space(text())='Text input']"));
        WebElement textInputField = driver.findElement(By.cssSelector("[name='my-text']"));

        WebElement actualNamePassword = driver.findElement(By.xpath("//label[normalize-space(text())='Password']"));
        WebElement passwordField = driver.findElement(By.xpath("//label[contains(@class, 'form-label')]/input[@name='my-password']"));

        WebElement actualNameTextarea = driver.findElement(By.xpath("//label[normalize-space(text())='Textarea']"));
        WebElement textareaField = driver.findElement(By.cssSelector("textarea.form-control"));

        WebElement actualNameDisabled = driver.findElement(By.xpath("//label[normalize-space(text())='Disabled input']"));
        WebElement disabledField = driver.findElement(By.cssSelector("[placeholder='Disabled input']"));

        WebElement actualNameReadonly = driver.findElement(By.xpath("//label[normalize-space(text())='Readonly input']"));
        WebElement readonlyField = driver.findElement(By.cssSelector("[name='my-readonly']"));

        WebElement actualTextLink = driver.findElement(By.xpath("//a[@href='./index.html']"));

        WebElement actualNameFileInput = driver.findElement(By.xpath("//label[normalize-space(text())='File input']"));
        WebElement fileInputField = driver.findElement(By.xpath("//label/input[@name='my-file']"));

        assertEquals("Web form", actualTitle.getText(), "Название не совпадает");

        assertEquals("Text input", actualNameTextInput.getText(), "Название не совпадает");
        assertEquals("", textInputField.getAttribute("value"), "Поле не пустое");

        assertEquals("Password", actualNamePassword.getText(), "Название не совпадает");
        assertEquals("", passwordField.getAttribute("value"), "Поле не пустое");

        assertEquals("Textarea", actualNameTextarea.getText(), "Название не совпадает");
        assertEquals("", textareaField.getAttribute("value"), "Поле не пустое");

        assertEquals("Disabled input", actualNameDisabled.getText(), "Название не совпадает");
        assertEquals("Disabled input", disabledField.getAttribute("placeholder"), "Поле имеет другой плэйсхолдер");

        assertEquals("Readonly input", actualNameReadonly.getText(), "Название не совпадает");
        assertEquals("Readonly input", readonlyField.getAttribute("value"), "Поле имеет другой текст");

        assertEquals("Return to index", actualTextLink.getText(), "Название не совпадает");

        assertEquals("File input", actualNameFileInput.getText(), "Название не совпадает");
        assertTrue(fileInputField.isDisplayed(), "Кнопка выбора файла должна быть видимой");
    }

    @Test
    void testWebFormPageDropdownMenusTest() {
        driver.findElement(By.xpath("//a[contains(@href, 'web-form')]")).click();
        WebElement dropdownSelectElement = driver.findElement(By.name("my-select"));
        WebElement dropdownDatalistElement = driver.findElement(By.name("my-datalist"));

        assertTrue(dropdownSelectElement.isDisplayed(), "Дропдаун должен быть видимым");
        assertTrue(dropdownDatalistElement.isDisplayed(), "Дропдаун должен быть видимым");

        WebElement actualNameDropdownSelect = driver.findElement(By.xpath("//label[normalize-space(text())='Dropdown (select)']"));
        WebElement actualNameDropdownDatalist = driver.findElement(By.name("my-datalist"));

        assertTrue(actualNameDropdownSelect.getText().contains("Dropdown (select)"), "Название не совпадает");
        assertEquals("Type to search...", actualNameDropdownDatalist.getAttribute("placeholder"), "Поле имеет другой плэйсхолдер");

        Select dropdown = new Select(dropdownSelectElement);

        assertEquals("Open this select menu", dropdown.getFirstSelectedOption().getText(),
                "По умолчанию должен быть выбран 'Open this select menu'");

        dropdown.selectByValue("1");
        assertEquals("One", dropdown.getFirstSelectedOption().getText(), "Должен быть выбран 'One'");

        dropdown.selectByValue("2");
        assertEquals("Two", dropdown.getFirstSelectedOption().getText(), "Должен быть выбран 'Two'");

        dropdown.selectByValue("3");
        assertEquals("Three", dropdown.getFirstSelectedOption().getText(), "Должен быть выбран 'Three'");
    }

    @Test
    void checkWebFormPageElementsCheckBoxAndRadioTest() {
        driver.findElement(By.xpath("//a[contains(@href, 'web-form')]")).click();

        WebElement chekedCheckBox = driver.findElement(By.xpath("//input[@id='my-check-1']/parent::label[normalize-space()='Checked checkbox']"));
        WebElement defaultCheckBox = driver.findElement(By.xpath("//input[@id='my-check-2']/parent::label[normalize-space()='Default checkbox']"));

        assertEquals("Checked checkbox", chekedCheckBox.getText(), "Название не совпадает");
        assertEquals("Default checkbox", defaultCheckBox.getText(), "Название не совпадает");

        WebElement chekedRadio = driver.findElement(By.xpath("//input[@id='my-radio-1']/parent::label[normalize-space()='Checked radio']"));
        WebElement defaultRadio = driver.findElement(By.xpath("//input[@id='my-radio-2']/parent::label[normalize-space()='Default radio']"));

        assertEquals("Checked radio", chekedRadio.getText(), "Название не совпадает");
        assertEquals("Default radio", defaultRadio.getText(), "Название не совпадает");
    }

    @Test
    void checkSubmitButtonTest() {
        WebElement nameSubmitButton = driver.findElement(By.xpath("//button"));

        assertEquals("Submit",nameSubmitButton.getText(),"Название кнопки отличается");
    }

    @Test
    void checkColorPickerTest() {
        WebElement nameColorPicker = driver.findElement(By.xpath("//input[@type='color']/parent::label[normalize-space()='Color picker']"));
        WebElement colorPicker = driver.findElement(By.xpath("//input[@type='color']"));

        assertEquals("Color picker", nameColorPicker.getText(), "Название отличается");
        assertEquals("#563d7c", colorPicker.getAttribute("value"), "Поле имеет другой цвет");
    }

    @Test
    void checkDatePickerTest() {
        WebElement nameDatePicker = driver.findElement(By.xpath("//input[@name='my-date']/parent::label[normalize-space()='Color picker']"));
        WebElement datePicker = driver.findElement(By.xpath("//input[@name='my-date']"));

        assertEquals("Date picker", nameDatePicker.getText(), "Название отличается");

        datePicker.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement openedDatePicker = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("datepicker-dropdown")));

        assertTrue(openedDatePicker.isDisplayed(), "Date picker не появился");
    }

    @Test
    void checkExampleRangeTest() {
        WebElement nameExampleRange = driver.findElement(By.xpath("//input[@name='my-range']/parent::label[normalize-space()='Example range']"));
        WebElement exampleRange = driver.findElement(By.xpath("//input[@name='my-range']"));

        assertEquals("Date picker", nameExampleRange.getText(), "Название отличается");
        assertEquals("5", exampleRange.getAttribute("value"), "Ползунок имеет другое положение");
    }

    @Test
    void checkFutterWebFormTest() {
        WebElement actualFutterText = driver.findElement(By.cssSelector(".text-muted"));
        WebElement hiperLink = driver.findElement(By.xpath("//span/a[contains(@href,'bonigarcia.dev')]"));

        assertEquals("Copyright © 2021-2025 ", actualFutterText.getText(), "Текст не совпадает");
        assertEquals("Boni García", hiperLink.getText(), "Название не совпадает");
        assertEquals("https://bonigarcia.dev/", hiperLink.getAttribute("href"), "Ссылка не совпадает");
    }

}
