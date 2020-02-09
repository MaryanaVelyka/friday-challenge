package com.mvelyka.friday.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserHelper {

    private static final Logger LOG = LoggerFactory.getLogger(BrowserHelper.class);

    // TODO improve in order not to setup browser on each test execution
    public static WebDriver initWebdriver() {
        String browser = System.getProperty("browser");

        if (browser != null) {
            LOG.info("Trying to configure {} browser base on config", browser);
            switch (browser) {
                case "firefox": {
                    LOG.info("Using {} browser", browser);
                    WebDriverManager.firefoxdriver().setup();
                    return new FirefoxDriver();
                }
                // More browsers could be added
            }
        }

        // default case
        LOG.info("Using chrome browser");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
