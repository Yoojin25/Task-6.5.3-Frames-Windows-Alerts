package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Windows {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

//      1. Открыть http://the-internet.herokuapp.com/windows
        driver.get("http://the-internet.herokuapp.com/windows");
        String firstHandle = driver.getWindowHandle();

//      2. Нажать “Click Here”
        WebElement clickHere = driver.findElement(By.xpath("//a[@href='/windows/new']"));
        clickHere.click();

//      3. Проверить что произошел переход на новую страницу
        Set<String> allHandles = driver.getWindowHandles();

        for (String handle : allHandles) {
            if (!handle.equals(firstHandle)) {
                driver.switchTo().window(handle);
            }
        }

        if (!firstHandle.equals(driver.getWindowHandle())) {
            System.out.println("Произошел переход на новую страницу");
        }

//      4. Проверить текст “New Page” (тут, наверное, имеется в виду "New Window")
        WebElement textNewHandle = driver.findElement(By.xpath("//h3"));

        if (textNewHandle.getText().equals("New Window")) {
            System.out.println("Текст \"New Window\" найден на странице");
        }

//      5. Закрыть новую вкладку
        Thread.sleep(2000);
        driver.close();

//      6. Проверить переход на предыдущую страницу
        Thread.sleep(2000);
        driver.switchTo().window(firstHandle);

        if (firstHandle.equals(driver.getWindowHandle())) {
            System.out.println("Произошел переход на предыдущую страницу");
        }

        driver.quit();
    }
}
