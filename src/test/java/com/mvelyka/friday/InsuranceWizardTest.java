package com.mvelyka.friday;

import com.mvelyka.friday.util.BrowserHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;

import static com.mvelyka.friday.WizardStep.*;

public class InsuranceWizardTest {

    private static final Logger LOG = LoggerFactory.getLogger(InsuranceWizardTest.class);

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = BrowserHelper.initWebdriver();
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "CarsProvider")
    public void verifyCarDetailsCouldBeConfigured(Object[][] steps) {
        // TODO move out to environment specific property files (dev, stage, prod).
        driver.get("https://hello.friday.de/quote/selectPrecondition");

        LandingPage landingPage = new LandingPage(driver);
        landingPage.setKeepingCar();
        landingPage.setInceptionDate("05.05.2020");
        ApproveCarPage approveCarPage = landingPage.goToApproveCarPage();

        approveCarPage.setNewAuto();
        approveCarPage.setRegisteredOwnerNo();

        ListChoicePage listChoiceStep = approveCarPage.goToWizardStep();

        FirstRegistrationPage firstRegistrationPage = null;
        for (int i = 0; i < steps.length; i++) {
            // If it's not the last step before First Registration page.
            if (i < steps.length - 1) {
                listChoiceStep = listChoiceStep.selectAndGoToNextStep(String.valueOf(steps[i][0]), WizardStep.valueOf(String.valueOf(steps[i + 1][1])));
            } else {
                firstRegistrationPage = listChoiceStep.selectAndGoToFirstRegistrationPage(String.valueOf(steps[i][0]));
            }
        }

        firstRegistrationPage.setMonthYearAndGoToNextPage("10.2020");

        // Stop here as required in the task
        BirthDatePage birthDatePage = new BirthDatePage(driver);
    }

    /**
     * Each brand and model can have different number of steps. E.g. VW cars have more steps than TESLA.
     * It's handled via implementing chaining capability in ListChoicePage.
     */
    @DataProvider(name = "CarsProvider")
    public Object[][] getParams() {
        return new Object[][][]
                {
                        {{"VW", MANUFACTURER}, {"Passat", MODEL}, {"Kombi", TYPE}, {"Diesel", FUEL}, {"51 kW / 69 PS", HORSE_POWER}, {"PASSAT VARIANT TURBO-DIESEL", HSN_TSN_ID}},
                        {{"VW", MANUFACTURER}, {"Polo", MODEL}, {"Limousine", TYPE}, {"Gas", FUEL}, {"66 kW / 90 PS", HORSE_POWER}, {"POLO VI 1.0 TGI", HSN_TSN_ID}},
                        {{"VW", MANUFACTURER}, {"Tiguan", MODEL}, {"Benzin", FUEL}, {"92 kW / 125 PS", HORSE_POWER}, {"TIGUAN 1.4 TSI", HSN_TSN_ID}},

                        {{"TESLA", MANUFACTURER}, {"Model S", MODEL}, {"66 kW / 90 PS", HORSE_POWER}, {"MODEL S ALLRAD", HSN_TSN_ID}},
                        {{"TESLA", MANUFACTURER}, {"Model 3", MODEL}, {"100 kW / 136 PS", HORSE_POWER}, {"MODEL 3", HSN_TSN_ID}},
                        {{"TESLA", MANUFACTURER}, {"Model X", MODEL}, {"158 kW / 215 PS", HORSE_POWER}, {"MODEL X 75D/90D", HSN_TSN_ID}},

                        {{"FORD", MANUFACTURER}, {"Fiesta", MODEL}, {"Benzin", FUEL}, {"33 kW / 45 PS", HORSE_POWER}, {"FIESTA 84", HSN_TSN_ID}},
                        {{"FORD", MANUFACTURER}, {"KA", MODEL}, {"Benzin", FUEL}, {"36 kW / 49 PS", HORSE_POWER}, {"KA", HSN_TSN_ID}},
                        {{"FORD", MANUFACTURER}, {"Edge", MODEL}, {"155 kW / 211 PS", HORSE_POWER}, {"EDGE 2.0 TDCI AWD", HSN_TSN_ID}},

                        // Uncomment to see screenshot capturing
//                        {{"FORD", MANUFACTURER}, {"Edge", MODEL}, {"10 kW / 211 PS", HORSE_POWER}, {"EDGE 2.0 TDCI AWD", HSN_TSN_ID}}
                };

    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);

                FileUtils.copyFile(source, new File("./screenshots/" + result.getName() + System.currentTimeMillis() + ".png"));

                LOG.info("Screenshot taken");
            } catch (Exception e) {
                LOG.error("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    @AfterTest
    public void cleanup() {
        driver.close();
    }
}
