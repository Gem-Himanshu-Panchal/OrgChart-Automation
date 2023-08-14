package com.qa.orgchart.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.orgchart.locators.CommonLocators;
import com.qa.orgchart.utils.GenericUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class DCTechView {
    static String chair = null;
    static List<WebElement> firstRowEmployees = null;

    @Given("Open modals in {string}")
    public static void clickOnDownArrows(String teamBox) {
        GenericUtils.waitUntilLoaderDisappear();
        DriverAction.scrollIntoView(By.xpath("//tr[@class='nodes']//table//tr//td//div[@class='node cursorPointer']//img"));
        DriverAction.waitSec(1);
        DriverAction.hoverOver(By.xpath("//tr[@class='nodes']//table//tr//td//div[@class='node cursorPointer']//img"));
        DriverAction.waitSec(1);
        DriverAction.getElement(By.xpath("//i[@class='edge verticalEdge bottomEdge fa fa-chevron-circle-down']")).click();
        DriverAction.waitSec(1);
        DriverAction.scrollIntoView(CommonLocators.ecTeamBox(teamBox));
        DriverAction.waitSec(1);
        DriverAction.hoverOver(CommonLocators.ecTeamBox(teamBox));
        chair = null;
        if (DriverAction.isExist(CommonLocators.chairBox(teamBox))) {
            chair = DriverAction.getElementText(CommonLocators.chairName(teamBox));
        }
        DriverAction.waitSec(1);
        DriverAction.getElement(By.xpath("//i[@class='edge verticalEdge bottomEdge fa fa-chevron-circle-down']")).click();

        firstRowEmployees = DriverAction.getElements(By.xpath("(//tr[@class='nodes'])[3]/td/table//div[@class='node cursorPointer']"));

        List<WebElement> members = DriverAction.getElements(By.xpath("(//tr[@class='nodes'])[3]/td/table"));
        String str = "(//tr[@class='nodes'])[3]/td/table";
        String str2 = "/tr[@class='nodes']/td/table";
        while (!members.isEmpty()) {
            for (int i = 0; i < members.size(); i++) {
                DriverAction.scrollIntoView(members.get(i));
                DriverAction.hoverOver(members.get(i));
                if (DriverAction.isExist(CommonLocators.downArrow)) {
                    DriverAction.getElement(CommonLocators.downArrow).click();
                    DriverAction.waitSec(1);
                }
            }
            members.clear();
            str = str + str2;
            members.addAll(DriverAction.getElements(By.xpath(str)));

        }
    }

    @Then("Check employee in DC view for {string} of OrgChart")
    public void check_for_to_employee_in_dc_view_of_org_chart(String dcTechName) {
        boolean passed = false;
        GenericUtils.waitUntilLoaderDisappear();
        GenericUtils.waitUntilElementAppear(CommonLocators.chartContainer);
        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList2();
        int flag = 1;
        if (dcTechName.equalsIgnoreCase("Discern")) {
            if (DriverAction.isExist(CommonLocators.hierarchyCheck("Akshit Chauhan", "GSI G 099", "Rishabh Zutshi", "GSI G 1666"))
                    && DriverAction.isExist(CommonLocators.employeeDiv("Akshit Chauhan", "GSI G 099"))) {
                GemTestReporter.addTestStep("Verify if employees are at right hierarchy or not",
                        "Employees is at right hierarchy", STATUS.PASS, DriverAction.takeSnapShot());
            } else GemTestReporter.addTestStep("Verify if employees are at right hierarchy or not",
                    "Employees is at wrong hierarchy", STATUS.FAIL, DriverAction.takeSnapShot());
        } else {
            for (HashMap<String, String> hashMap : hashMapList) {
                if (hashMap.get("DCTech").contains(dcTechName)
                        || (hashMap.containsKey("SecondaryDCs") &&
                        hashMap.get("SecondaryDCs") != null &&
                        hashMap.get("SecondaryDCs").contains(dcTechName))) {
                    String empName = hashMap.get("EmployeeName");
                    String empCode = hashMap.get("EmployeeCode");
                    String mentorName = hashMap.get("ReportingManager");
                    String mentorCode = hashMap.get("ManagerCode");

                    if (!DriverAction.isExist(CommonLocators.employeeDiv(empName, empCode))) {
                        GemTestReporter.addTestStep(flag + ". Verify if " + empName + " is at right hierarchy or not",
                                empName + " is at wrong hierarchy", STATUS.FAIL, DriverAction.takeSnapShot());
                        flag++;
                        continue;
                    }

                    String mentorDCTech = GenericUtils.getDcTech(mentorName, mentorCode);
                    String mentorSecondaryDCTech = GenericUtils.getSecondaryDcTech(mentorName, mentorCode);
                    assert mentorDCTech != null;
                    DriverAction.waitSec(1);
                    if (!mentorDCTech.contains(dcTechName) && !mentorSecondaryDCTech.contains(dcTechName) && !mentorName.equalsIgnoreCase(chair)) {
                        if (GenericUtils.isEmployeeInFirstRow(firstRowEmployees, empName, empCode)) {
                            GemTestReporter.addTestStep(flag + ". Verify if " + empName + " is at right hierarchy or not",
                                    empName + " is at right hierarchy", STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep(flag + ". Verify if " + empName + " is at right hierarchy or not",
                                    empName + " is at wrong hierarchy", STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    } else if (!mentorDCTech.contains(dcTechName) && !mentorSecondaryDCTech.contains(dcTechName) && mentorName.equalsIgnoreCase(chair)) {
                        if (DriverAction.isExist(CommonLocators.hierarchyCheck(mentorName, mentorCode, empName, empCode))) {
                            GemTestReporter.addTestStep(flag + ". Verify if " + empName + " is at right hierarchy or not",
                                    empName + " is at right hierarchy", STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep(flag + ". Verify if " + empName + " is at right hierarchy or not",
                                    empName + " is at wrong hierarchy", STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    } else if (!mentorDCTech.contains(dcTechName) && !mentorSecondaryDCTech.contains(dcTechName)) {
                        if (GenericUtils.isEmployeeInFirstRow(firstRowEmployees, empName, empCode)) {
                            GemTestReporter.addTestStep(flag + ". Verify if " + empName + " is at right hierarchy or not",
                                    empName + " is at right hierarchy", STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep(flag + ". Verify if " + empName + " is at right hierarchy or not",
                                    empName + " is at wrong hierarchy", STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    } else {
                        if (DriverAction.isExist(CommonLocators.hierarchyCheck(mentorName, mentorCode, empName, empCode))) {
                            GemTestReporter.addTestStep(flag + ". Verify if " + empName + " is at right hierarchy or not",
                                    empName + " is at right hierarchy", STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep(flag + ". Verify if " + empName + " is at right hierarchy or not",
                                    empName + " is at wrong hierarchy", STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    }
                    flag++;
                    scrollToElement.scrollToElement(empName, empCode);
                    DriverAction.getElement(CommonLocators.employeeDiv(empName, empCode)).click();
                    GenericUtils.waitUntilElementAppear(CommonLocators.infoCard);
                    DriverAction.waitSec(2);

                    List<String> resp = GenericUtils.verifyEmployeeDetails(hashMap);

                    if (resp.get(0).equalsIgnoreCase("True")) {
                        GemTestReporter.addTestStep("Verify if " + empName + " has right values",
                                empName + " has right values", STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify if " + empName + " is at right hierarchy or not",
                                empName + " has wrong value: " + resp.get(1), STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                    DriverAction.getElement(CommonLocators.crossIcon).click();
                }

            }
        }
    }
}

