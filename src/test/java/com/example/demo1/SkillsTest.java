package com.example.demo1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class SkillsTest {
    private WebDriver driver;
    private PlexusPage mainPlexusPage;

    @BeforeTest
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.plexusworldwide.com/");
    }

    @Test
    public void skillTest() {
        mainPlexusPage = new PlexusPage(driver);
        mainPlexusPage.acceptAllCookies();

        String mainBannerText = mainPlexusPage.getHeroBannerText();
        assertEquals(mainBannerText, "FREE RESET for new VIPs with the purchase of Triplex & Trim**");

        String currentUrl = mainPlexusPage.getCurrentUrl();
        mainPlexusPage.openGutHealthProductsPage();
        mainPlexusPage.waitForPageLoad();
        assertNotEquals(currentUrl, mainPlexusPage.getCurrentUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
