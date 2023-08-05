package com.qa.orgchart.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.orgchart.locators.CommonLocators;
import com.qa.orgchart.utils.GenericUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSrepDefinition {
    @Then("^Switch to \"(.*)\" view$")
    public static void switchToView(String viewName) {
        try {
            GenericUtils.waitUntilLoaderDisappear();
            GenericUtils.waitUntilElementAppear(CommonLocators.dropdownBox);
            DriverAction.click(CommonLocators.dropdownBox);
            GenericUtils.waitUntilLoaderDisappear();
            DriverAction.scrollIntoView(CommonLocators.viewValue(viewName));
            GenericUtils.waitUntilElementAppear(CommonLocators.viewValue(viewName));
            DriverAction.getElement(CommonLocators.viewValue(viewName)).click();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Given("^Navigate to OrgChart and login$")
    public void navigate_to_org_chart_and_login() {
        try {
            GenericUtils.waitUntilLoaderDisappear();
            GenericUtils.waitUntilElementAppear(CommonLocators.loginButton);
            if (DriverAction.isExist(CommonLocators.loginButton)) {
                DriverAction.click(CommonLocators.loginButton);
                GemTestReporter.addTestStep("Click on Login button"
                        , "Successfully clicked on login button", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Click on Login button"
                        , "Unable to click on login button", STATUS.FAIL, DriverAction.takeSnapShot());
            WebDriver driver = DriverManager.getWebDriver();
            try {

                String mainWin = driver.getWindowHandle();
                String popUpWin = null;
                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.numberOfWindowsToBe(2));

                for (String handle : driver.getWindowHandles()) {
                    if (!handle.equalsIgnoreCase(mainWin)) {
                        popUpWin = handle;
                        break;
                    }
                }
                DriverAction.switchToWindow(popUpWin);
                GenericUtils.waitUntilElementAppear(CommonLocators.loginEmail);
                if (DriverAction.isExist(CommonLocators.loginEmail)) {
                    DriverAction.typeText(CommonLocators.loginEmail, "himanshu.panchal@geminisolutions.com");
                    DriverAction.click(CommonLocators.submitButton);
                }
                GenericUtils.waitUntilElementAppear(CommonLocators.loginPswd);
                if (DriverAction.isExist(CommonLocators.loginPswd)) {
                    DriverAction.typeText(CommonLocators.loginPswd, "HimuJune@1808");
                    DriverAction.click(CommonLocators.submitButton);
                }
                GenericUtils.waitUntilElementAppear(CommonLocators.submitButton);
                DriverAction.click(CommonLocators.submitButton);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(driver.getWindowHandles().size()>0){
                    String mainWindow = driver.getWindowHandles().iterator().next();
                    DriverAction.switchToWindow(mainWindow);
                }
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("^Verify if user is on OrgChart dashboard$")
    public void verifyIfUserIsOnOrgChartDashboard() {
        try {
            GenericUtils.waitUntilLoaderDisappear();
            GenericUtils.waitUntilElementAppear(CommonLocators.chartContainer);
            if (DriverAction.isExist(CommonLocators.companyLogo) && DriverAction.isExist(CommonLocators.chartContainer)
                    && DriverAction.isExist(CommonLocators.searchField)) {
                GemTestReporter.addTestStep("Verify if User is logged into OrgChart"
                        , "Successfully logged into OrgChart", STATUS.PASS, DriverAction.takeSnapShot());
            } else GemTestReporter.addTestStep("Verify if User is logged into OrgChart"
                    , "Unable to log into Orgchart", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }
}
