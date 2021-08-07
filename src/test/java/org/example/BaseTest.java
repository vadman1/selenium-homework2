package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeAll
    static void beforeAll() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications"); // отключение уведомлений от браузера

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize(); // раскрытие окна браузера на максимальную ширину
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS); // ожидание загрузки страницы
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // ожидание появления элемента на странице

        wait = new WebDriverWait(driver, 20, 1000);

    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    void beforeEach(){
        driver.get("https://www.raiffeisen.ru/"); // при следующем тесте переходим на начальную страницу
    }

    @AfterEach
    void afterEach() {
        // по окончании теста браузер не закрываем
    }

}
