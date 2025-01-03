package com.example.automation;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginAutomationTest {

    @Test
    public void testLogin() {
        System.setProperty("webdriver.chrome.driver", "D:/tools/chromedriver-win64/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        try {

            driver.get("file:///D:/loginpage/login.html");


            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("loginButton"));


            usernameField.sendKeys("testUser");
            passwordField.sendKeys("testPassword");
            loginButton.click();


            String expectedTitle = "Test Login Page";
            String actualTitle = driver.getTitle();
            assertEquals(expectedTitle, actualTitle);
        } finally {

            driver.quit();
        }
    }
}
