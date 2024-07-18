package com.example.demo1;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlexusPage {
    private final WebDriver driver;
    private final String startPageUrl;

    public PlexusPage(WebDriver driver) {
        this.driver = driver;
        this.startPageUrl = driver.getCurrentUrl();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@data-testid='hero-banner-content-area']/h1")
    public WebElement heroBanner;

    @FindBy(xpath = "//div[h3[text()='Gut Health']]//following-sibling::div//a[text()='Shop Now']")
    public WebElement gutHealthProductsButton;

    @FindBy(css = "#truste-consent-button")
    public WebElement acceptAllCookiesButton;

    @Step("Accept all cookies")
    public void acceptAllCookies() {
        acceptAllCookiesButton.click();
    }

    @Step("Get hero banner text")
    public String getHeroBannerText() {
        return heroBanner.getText();
    }

    @Step("Open Gut Health Products page")
    public void openGutHealthProductsPage() {
        scrollToElement(gutHealthProductsButton).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitForPageLoad() {
        long timeOutInSeconds = 10;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(startPageUrl)));

        wait.until((ExpectedCondition<Boolean>) wd ->
                        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
    }

    public WebElement scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", element);
        return element;
    }
}
