package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Frames {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

//      1. Открыть http://the-internet.herokuapp.com/frames
        driver.get("http://the-internet.herokuapp.com/frames");

//      2. Перейти на iFrame
        WebElement goIframe = driver.findElement(By.xpath("//a[@href='/iframe']"));
        goIframe.click();
        driver.switchTo().frame("mce_0_ifr");

//      3. В текстовый редактор ввести Hello World
        WebElement textEditor = driver.findElement(By.xpath("//body[@id='tinymce']/p"));
        textEditor.clear();
        textEditor.sendKeys("Hello World");

//      4. Сделать текст жирным через кнопку B в окне фрейма
        Actions actions = new Actions(driver);

        actions.moveToElement(textEditor)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .perform();

        driver.switchTo().defaultContent();

        WebElement textFormat = driver.findElement(By.xpath("//button[@aria-label='Bold']/span"));
        textFormat.click();
        Thread.sleep(1000);

        driver.quit();
    }
}