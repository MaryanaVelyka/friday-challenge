package com.mvelyka.friday;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LandingPage extends PageObject {

    private static final Logger LOG = LoggerFactory.getLogger(LandingPage.class);

    @FindBy(xpath = "//span[@data-test-id='wizardTitle']/span[.='In 90 Sekunden zum Beitrag']")
    private WebElement titleSpan;

    @FindBy(xpath = "//button[@name='precondition'] [@value='keepingCar']")
    private WebElement keepingCar;

    @FindBy(xpath = "//button[@name='precondition'] [@value='buyingCar']")
    private WebElement buyingCar;

    @FindBy(xpath = "//input[@name='inceptionDate']")
    private WebElement inceptionDate;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement nextButton;

    public LandingPage(WebDriver driver) {
        super(driver);

        getWebDriverWait(driver).until(ExpectedConditions.visibilityOf(titleSpan));
        LOG.debug("Created FirstRegistrationPage");
    }

    public LandingPage setKeepingCar() {
        LOG.info("Selecting car keeping");
        keepingCar.click();
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(inceptionDate));

        return this;
    }

    public LandingPage setInceptionDate(String inceptionDateValue) {
        LOG.info("Setting inception date");
        inceptionDate.clear();
        inceptionDate.click();
        inceptionDate.sendKeys(inceptionDateValue);

        return this;
    }

    public ApproveCarPage goToApproveCarPage() {
        LOG.info("Going to ApproveCarPage");
        // Sometimes regular clicks fails due to form reloading. Using js click.
        jsClick(nextButton);

        return new ApproveCarPage(driver);
    }



}
