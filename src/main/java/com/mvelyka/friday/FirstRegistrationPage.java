package com.mvelyka.friday;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstRegistrationPage extends PageObject {

    private static final Logger LOG = LoggerFactory.getLogger(FirstRegistrationPage.class);

    @FindBy(xpath = "//input[@name='monthYearFirstRegistered']")
    private WebElement monthYearFirstRegistered;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement nextButton;

    @FindBy(xpath = "//div[@data-test-id='wizardTitle']/span[.='Wann war die Erstzulassung?']")
    private WebElement titleSpan;

    public FirstRegistrationPage(WebDriver driver) {
        super(driver);

        getWebDriverWait(driver).until(ExpectedConditions.visibilityOfAllElements(titleSpan));
        LOG.debug("Created FirstRegistrationPage");
    }

    public BirthDatePage setMonthYearAndGoToNextPage(String monthYear) {
        LOG.info("Selecting selecting month and year and go to BirthDatePage");
        monthYearFirstRegistered.sendKeys(monthYear);
        nextButton.click();

        return new BirthDatePage(driver);

    }
}
