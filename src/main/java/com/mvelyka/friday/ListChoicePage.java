package com.mvelyka.friday;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListChoicePage extends PageObject {

    private static final Logger LOG = LoggerFactory.getLogger(ListChoicePage.class);

    private static final String LIST_OPTION_SELECTOR = "//label[contains(text(), '%s')]";

    @FindBy(xpath = "//div[@data-test-id = 'wizardTitle']/span")
    private WebElement titleSpan;

    public ListChoicePage(WizardStep step, WebDriver driver) {
        super(driver);

        getWebDriverWait(driver).until(ExpectedConditions.textToBePresentInElement(titleSpan, step.getTitle()));
        LOG.debug("Created FirstRegistrationPage");
    }

    public ListChoicePage selectAndGoToNextStep(String optionToSelect, WizardStep nextStep) {
        LOG.info("Going to next ListChoicePage");
        WebElement option = driver.findElement(By.xpath(String.format(LIST_OPTION_SELECTOR, optionToSelect)));
        option.click();

        return new ListChoicePage(nextStep, driver);
    }

    public FirstRegistrationPage selectAndGoToFirstRegistrationPage(String optionToSelect) {
        LOG.info("Going to next FirstRegistrationPage");
        selectOption(optionToSelect);

        return new FirstRegistrationPage(driver);
    }

    private void selectOption(String optionToSelect) {
        WebElement option = driver.findElement(By.xpath(String.format(LIST_OPTION_SELECTOR, optionToSelect)));
        option.click();
    }

}
