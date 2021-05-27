package com.wardy.wardy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductPageTest {

    private static WebDriver driver;

    @BeforeAll
    public static void initialseDriver(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void getDashboardTitle() {
        String pageUrl = "http://localhost:4200/products";
        String expectedTitle = "Wardy";
        String actualTitle = "";

        driver.get(pageUrl);
        actualTitle = driver.getTitle();

        assertEquals(expectedTitle, actualTitle);

        System.out.println("Expected " +expectedTitle + " - Actual:  " + actualTitle);
    }

    @Test
    public void loginAsBatman() {
        String pageUrl = "http://localhost:4200";
        String expectedTitle = "Kirby";
        String actualTitle = "";

        driver.get(pageUrl);
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.xpath("/html/body/app-root/main/app-login/button[1]")).click();
        driver.findElement(By.linkText("Products")).click();

        WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/main/app-products/div/div[2]/div/div[1]/app-product-card/div/div[1]/h5")));

        actualTitle = driver.findElement(By.xpath("/html/body/app-root/main/app-products/div/div[2]/div/div[1]/app-product-card/div/div[1]/h5")).getText();
        assertEquals(expectedTitle, actualTitle);
        System.out.println("Expected " +expectedTitle + " - Actual:  " + actualTitle);
    }

    @AfterAll
    public static void closeDriver() {
        driver.close();
    }
}
