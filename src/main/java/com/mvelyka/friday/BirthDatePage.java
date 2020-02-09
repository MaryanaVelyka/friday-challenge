package com.mvelyka.friday;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BirthDatePage extends PageObject{

    private static final Logger LOG = LoggerFactory.getLogger(BirthDatePage.class);

    @FindBy(xpath = "//div[@data-test-id='wizardTitle'][.='Wann wurdest du geboren?']")
    private WebElement titleSpan;

    public BirthDatePage(WebDriver driver) {
        super(driver);

        getWebDriverWait(driver).until(ExpectedConditions.visibilityOfAllElements(titleSpan));
        LOG.debug("Created BirthDatePage");
    }

}
