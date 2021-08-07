package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class RaiffeisenTest extends BaseTest {

    @Test
    public void test() {

        // закрыть попап снизу с вопросом "Ваш город Москва?"
        driver.findElement(By.xpath("//div[contains(@class, 'js-region-check-close')]")).click();

        // выбрать пункт меню "Ипотека"
        driver.findElement(By.xpath("//a[@href='/retail/mortgageloans/']")).click();

        // выбрать под-пункт меню "Рефинансирование"
        driver.findElement(By.xpath("//a[contains(@href, '/refinansirovanie-kreditov-inyh-bankov/')]")).click();
        // проверка перехода на страницу с рефинансированием
        WebElement title = driver.findElement(By.tagName("h1"));
        Assertions.assertEquals("Рефинансирование ипотеки и других кредитов",
                title.getText(), "Мы находимся не на странице с рефинансированием ипотеки");

        // кликаем по кнопке "Оставить заявку"
        driver.findElements(By.xpath("//a[contains(@href, '/promo/refin-form')]")).get(0).click();
        // проверка перехода на страницу с заявкой
        Assertions.assertEquals("Оставьте заявку на рефинансирование ипотеки прямо сейчас", driver.findElement(By
                        .xpath("//div[text()='Оставьте заявку на рефинансирование ипотеки прямо сейчас']")).getText(),
                "Мы находимся не на странице с заявкой");


        // заполнение полей формы
        WebElement fullNameField = driver.findElement(By.xpath("//input[@name='fullName']"));
        fillInputField(fullNameField, "Иванов Иван Иванович");
        driver.findElement(By.xpath("//div[text()='Иванов Иван Иванович']")).click();

        WebElement birthDate = driver.findElement(By.xpath("//input[@name='birthDate']"));
        fillInputFieldDate(birthDate, "01.01.1990");

        WebElement birthPlace = driver.findElement(By.xpath("//input[@name='birthPlace']"));
        fillInputField(birthPlace, "Москва");

        driver.findElement(By.xpath("//input[@value='M']/./..")).click();

        driver.findElement(By.xpath("//label[@data-marker-field='isResident']")).click();

        // выбор страны в списке
        driver.findElement(By.xpath("//div[@data-marker-field='citizenship']//span[text()='Выберите вариант']")).click();
        WebElement chooseGermany = driver.findElement(By.xpath("//div[text()='Германия']"));
        chooseGermany.click();

        WebElement foreignSeries = driver.findElement(By.xpath("//input[@data-marker-field='foreignSeries']"));
        fillInputField(foreignSeries, "1111");

        WebElement foreignNumber = driver.findElement(By.xpath("//input[@data-marker-field='foreignNumber']"));
        fillInputField(foreignNumber, "1111111111");

        WebElement foreignIssuedDate = driver.findElement(By.xpath("//input[@data-marker-field='foreignIssuedDate']"));
        fillInputFieldDate(foreignIssuedDate, "01.01.2020");

        WebElement foreignIssuedBy = driver.findElement(By.xpath("//input[@data-marker-field='foreignIssuedBy']"));
        fillInputField(foreignIssuedBy, "111");

        WebElement registrationAddress = driver.findElement(By.xpath("//input[@data-marker-field='registrationAddress']"));
        fillInputField(registrationAddress, "г Москва, Ломоносовский пр-кт, д 27Д");

        WebElement phone = driver.findElement(By.xpath("//input[@data-marker-field='phone']"));
        fillInputFieldPhone(phone, "(909) 999-99-99");

        WebElement btnSubmit = driver.findElement(By.xpath("//button[@type='submit']"));
        btnSubmit.click();


        // проверка наличия сообщений об ошибках
        WebElement salary = driver.findElement(By.xpath("//input[@data-marker-field='salary']"));
        checkErrorMessageAtField(salary, "Поле обязательно для заполнения");

        WebElement acceptSalary = driver.findElement(By.xpath("//span[@data-marker='Select.Value.Value']"));
        acceptSalary = acceptSalary.findElement(By.xpath("./.."));
        checkErrorMessageAtField(acceptSalary, "Поле обязательно для заполнения");

        WebElement pledgeAddress = driver.findElement(By.xpath("//input[@data-marker-field='pledgeAddress']"));
        pledgeAddress = pledgeAddress.findElement(By.xpath("./.."));
        checkErrorMessageAtField(pledgeAddress, "Выберите из списка");

        WebElement email = driver.findElement(By.xpath("//input[@data-marker-field='email']"));
        checkErrorMessageAtField(email, "Поле обязательно для заполнения");

        WebElement agreement = driver.findElement(By.xpath("//span[@data-marker-field='agreement']"));
        agreement = agreement.findElement(By.xpath("./.."));
        checkErrorMessageAtField(agreement, "Поле обязательно для заполнения");

    }


    private void fillInputField(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);
        Assertions.assertEquals(value, element.getAttribute("value"), "Значения не совпадают");
    }

    private void fillInputFieldDate(WebElement element, String value) {
        element.click();
        element.sendKeys(value);
        Assertions.assertEquals(value, element.getAttribute("value"), "Значения не совпадают");
    }

    private void fillInputFieldPhone(WebElement element, String value) {
        element.click();
        element.sendKeys(value);
        Assertions.assertEquals("+7 " + value, element.getAttribute("value"), "Значения не совпадают");
    }

    private void checkErrorMessageAtField(WebElement element, String errorMessage) {
        element = element.findElement(By.xpath("./../../div[@data-marker='Fieldset.value.Error']"));
        Assertions.assertEquals(errorMessage, element.getText(),
                "Проверка ошибки у поля не была пройдена");
    }

}
