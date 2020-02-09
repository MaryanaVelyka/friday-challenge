package com.mvelyka.friday;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mvelyka.friday.WizardStep.MANUFACTURER;

public class ApproveCarPage extends PageObject {

    private static final Logger LOG = LoggerFactory.getLogger(ApproveCarPage.class);

    @FindBy(xpath = "//div[@data-test-id='wizardTitle']/span[.='Bist du der Halter des Autos (zugelassen auf dich)?']")
    private WebElement titleSpan;

    @FindBy(xpath = "//button[@name='registeredOwner'] [@value='Yes']")
    private WebElement registeredOwnerYes;

    @FindBy(xpath = "//button[@name='registeredOwner'] [@value='No']")
    private WebElement registeredOwnerNo;

    @FindBy(xpath = "//button[@name='newOrUsed'] [@value='used']")
    private WebElement usedAuto;

    @FindBy(xpath = "//button[@name='newOrUsed'] [@value='brandNew']")
    private WebElement newAuto;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement nextButton;

    public ApproveCarPage(WebDriver driver) {
        super(driver);

        getWebDriverWait(driver).until(ExpectedConditions.visibilityOfAllElements(titleSpan));
        LOG.debug("Created ApproveCarPage");
    }

    public ApproveCarPage setRegisteredOwnerNo() {
        LOG.info("Selecting registeredOwnerNo");
        registeredOwnerNo.click();
        return this;
    }

    public ApproveCarPage setNewAuto() {
        LOG.info("Selecting new auto");
        newAuto.click();
        return this;
    }
    public ListChoicePage goToWizardStep() {
        LOG.info("Going to ListChoicePage");
        nextButton.click();
        return new ListChoicePage(MANUFACTURER, driver);
    }
}
