package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Alerts {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

//      1. Открыть http://the-internet.herokuapp.com/javascript_alerts
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");

//      2. Нажать на “Click for JS Alert”
        WebElement buttonAlert = driver.findElement(By.xpath("//button[@onclick='jsAlert()']"));
        buttonAlert.click();

//      3. Получить и проверить что текст в алерте - I am a JS Alert
        driver.switchTo().alert();

        String textAlert = driver.switchTo().alert().getText();

        if (textAlert.equals("I am a JS Alert")) {
            System.out.println("Текст в алерте - I am a JS Alert");
        }

//      4. Нажать кнопку “OK”
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

//      5. Проверить что алерт закрылся
        checkAlertClosed(driver);

//      6. Нажать на “Click for JS Confirm”
        WebElement buttonConfirm = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
        buttonConfirm.click();

//      7. Отказаться от алерта
        Thread.sleep(2000);
        driver.switchTo().alert().dismiss();

//      8. Проверить что алерт закрылся
        checkAlertClosed(driver);

//      9. Нажать на Click for JS Prompt
        WebElement buttonPrompt = driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));
        buttonPrompt.click();

//      10. Ввести текст “Hello World”
        driver.switchTo().alert().sendKeys("Hello World");

//      11. Нажать “ОК”
        driver.switchTo().alert().accept();

//      12. Проверить что на странице появился текст You entered: Hello World
        if (driver.getPageSource().contains("You entered: Hello World")) {
            System.out.println("You entered: Hello World");
        }

        driver.quit();
    }

    public static void checkAlertClosed(WebDriver driver) {
        try {
            driver.switchTo().alert();
            System.out.println("Алерт открыт");
        } catch (NoAlertPresentException e) {
            System.out.println("Алерт закрылся");
        }
    }
}
