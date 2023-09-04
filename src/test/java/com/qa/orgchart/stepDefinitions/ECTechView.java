package com.qa.orgchart.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.orgchart.locators.CommonLocators;
import com.qa.orgchart.utils.GenericUtils;
import groovy.util.logging.Log;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ECTechView {
    boolean passed = false;
    static String chair = null;
    static List<WebElement> firstRowEmployees = null;

    @Given("Open modals box in EC View {string}")
    public void openModalsBoxInECView(String teamBox) {
        GenericUtils.waitUntilLoaderDisappear();
        DriverAction.scrollIntoView(By.xpath("//div[@id='root-node']//img"));
        DriverAction.scrollIntoView(CommonLocators.ecTeamBox(teamBox));
        DriverAction.waitSec(1);
        DriverAction.hoverOver(CommonLocators.ecTeamBox(teamBox));
        chair = null;
        if(DriverAction.isExist(CommonLocators.chairBox(teamBox))){
            chair = DriverAction.getElementText(CommonLocators.chairName(teamBox));
        }
        DriverAction.waitSec(1);
        DriverAction.getElement(By.xpath("//i[@class='edge verticalEdge bottomEdge fa fa-chevron-circle-down']")).click();

        firstRowEmployees = DriverAction.getElements(By.xpath("(//tr[@class='nodes'])[2]/td/table//div[@class='node cursorPointer']"));

        List<WebElement> members = DriverAction.getElements(By.xpath("(//tr[@class='nodes'])[2]/td/table"));
        String str = "(//tr[@class='nodes'])[2]/td/table";
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
//    @Given("Search for {int} to {int} employee in EC view of OrgChart")
//    public void search_for_to_employee_in_ec_view_of_org_chart(Integer start, Integer end) {
//        GenericUtils.waitUntilLoaderDisappear();
//        GenericUtils.waitUntilElementAppear(CommonLocators.chartContainer);
//        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList2();
//        String mentorName = null;
//        String mentorCode = null;
//        for (int i = start; i < end; i++) {
//// 124 anil pahal, 125 Anil singh, 212,380,479,504 Branding, 385 (Harinath Mn)
//            if (i==124 || i==125 ||i==212 ||i==385 ||i == 479|| i==504) {
//                System.out.println("null");
//            } else {
//                List<String> userHierarchy = GenericUtils.getECHierarchy(i);
//
//                int lastIndex = userHierarchy.size() - 1;
//                String EcTech = userHierarchy.get(lastIndex);
//                userHierarchy.remove(lastIndex);
//                DriverAction.scrollIntoView(CommonLocators.ecTeamBox(EcTech));
//                DriverAction.hoverOver(CommonLocators.ecTeamBox(EcTech));
//                DriverAction.scrollToBottom();
//                GenericUtils.waitUntilElementAppear(CommonLocators.ecTeamBoxDownKey(EcTech));
//                DriverAction.getElement(CommonLocators.ecTeamBoxDownKey(EcTech)).click();
//                lastIndex = userHierarchy.size() - 1;
//                while (userHierarchy.size() != 2) {
//                    DriverAction.scrollIntoView(CommonLocators.ecEmployeeBox(userHierarchy.get(lastIndex - 1), userHierarchy.get(lastIndex)));
//                    DriverAction.scrollToBottom();
//                    DriverAction.hoverOver(CommonLocators.ecEmployeeBox(userHierarchy.get(lastIndex - 1), userHierarchy.get(lastIndex)));
//                    DriverAction.scrollIntoView(CommonLocators.ecTeamDownKey(userHierarchy.get(lastIndex - 1), userHierarchy.get(lastIndex)));
//                    DriverAction.scrollToBottom();
//                    DriverAction.getElement(CommonLocators.ecTeamDownKey(userHierarchy.get(lastIndex - 1), userHierarchy.get(lastIndex))).click();
//                    mentorName = userHierarchy.get(lastIndex - 1);
//                    mentorCode = userHierarchy.get(lastIndex);
//                    userHierarchy.remove(lastIndex);
//                    userHierarchy.remove(lastIndex - 1);
//                    lastIndex = userHierarchy.size() - 1;
//                }
//                DriverAction.waitSec(1);
//
//                DriverAction.scrollIntoView(CommonLocators.hierarchyCheck(mentorName, mentorCode, userHierarchy.get(0), userHierarchy.get(1)));
//                if (mentorName != null && mentorCode != null) {
//                    if (DriverAction.isExist(CommonLocators.hierarchyCheck(mentorName, mentorCode, userHierarchy.get(0), userHierarchy.get(1)))) {
//                        GemTestReporter.addTestStep(i+ ". Verify if " + userHierarchy.get(0) + " is at right hierarchy or not",
//                                userHierarchy.get(0) + " is at right hierarchy", STATUS.PASS, DriverAction.takeSnapShot());
//                    } else {
//                        GemTestReporter.addTestStep(i+". Verify if " + userHierarchy.get(0) + " is at right hierarchy or not",
//                                userHierarchy.get(0) + " is at wrong hierarchy", STATUS.FAIL, DriverAction.takeSnapShot());
//                    }
//                } else {
//                    if (DriverAction.isExist(CommonLocators.noMentorHierarchy(EcTech, userHierarchy.get(0),  userHierarchy.get(1)))) {
//                        GemTestReporter.addTestStep("Verify if " + userHierarchy.get(0) + " is at right hierarchy or not",
//                                userHierarchy.get(0) + " is at right hierarchy", STATUS.PASS, DriverAction.takeSnapShot());
//                    } else {
//                        GemTestReporter.addTestStep("Verify if " + userHierarchy.get(0) + " is at right hierarchy or not",
//                                userHierarchy.get(0) + " is at wrong hierarchy", STATUS.FAIL, DriverAction.takeSnapShot());
//                    }
//                }
//
//                System.out.println(CommonLocators.employeeDiv(userHierarchy.get(0), userHierarchy.get(1)));
//                DriverAction.getElement(CommonLocators.employeeDiv(userHierarchy.get(0), userHierarchy.get(1))).click();
//                GenericUtils.waitUntilElementAppear(CommonLocators.infoCard);
//                DriverAction.waitSec(2);
//
//                assert hashMapList != null;
//                HashMap<String, String> employeeData = hashMapList.get(i);
//                String str = employeeData.get("DateOfJoining");
//                str = str.substring(0, 10);
//                LocalDate date = LocalDate.parse(str);
//
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                String formattedDate = date.format(formatter);
//                employeeData.put("DateOfJoining", formattedDate);
//
//                HashMap<String, String> extractData = new HashMap<>();
//                extractData.put("ImagePath", DriverAction.getAttributeName(CommonLocators.employeeProfile, "src"));
//                extractData.put("EmployeeName", DriverAction.getElementText(CommonLocators.employeeDataSet1(employeeData.get("EmployeeName"))));
//                extractData.put("EmployeeCode", DriverAction.getElementText(CommonLocators.employeeDataSet1(employeeData.get("EmployeeCode"))).substring(1, DriverAction.getElementText(CommonLocators.employeeDataSet1(employeeData.get("EmployeeCode"))).length() - 1));
//                extractData.put("Designation", DriverAction.getElementText(CommonLocators.employeeDataSet1(employeeData.get("Designation"))));
//                extractData.put("EmailId", DriverAction.getElementText(CommonLocators.employeeDataSet3("Email")));
//                extractData.put("MobileNumber", DriverAction.getElementText(CommonLocators.employeeDataSet3("Phone Number")));
//                extractData.put("Location", DriverAction.getElementText(CommonLocators.employeeDataSet3("Location")));
//                extractData.put("DateOfJoining", DriverAction.getElementText(CommonLocators.employeeDataSet3("Date of Joining")));
//                String[] temp = DriverAction.getElementText(CommonLocators.employeeDataSet4("Experience")).split(" ");
//                extractData.put("OverallExp", temp[0]);
//                extractData.put("ECTech", DriverAction.getElementText(CommonLocators.employeeDataSet3("Engineering Council")));
//                extractData.put("DCTech", DriverAction.getElementText(CommonLocators.employeeDataSet3("Delivery Council")));
//                if (employeeData.containsKey("PrimarySkills") && employeeData.containsKey("SecondarySkills"))
//                    extractData.put("BothSkills", DriverAction.getElementText(CommonLocators.employeeDataSet3("Skills")));
//                else if (employeeData.containsKey("PrimarySkills"))
//                    extractData.put("PrimarySkills", DriverAction.getElementText(CommonLocators.employeeDataSet3("Skills")));
//                else if (employeeData.containsKey("SecondarySkills"))
//                    extractData.put("SecondarySkills", DriverAction.getElementText(CommonLocators.employeeDataSet3("Skills")));
//
//                extractData.put("ECMentorName", DriverAction.getElementText(CommonLocators.employeeDataSet3("EC Mentor")));
//
//
//                String wrongValue = "";
//                for (Map.Entry<String, String> entry : extractData.entrySet()) {
//                    String key = entry.getKey();
//                    String value1 = entry.getValue();
//                    String value2 = "";
//                    if (key.equalsIgnoreCase("PrimarySkills")) {
//                        value2 = employeeData.get(key);
//                    } else if (key.equalsIgnoreCase("SecondarySkills")) {
//                        value2 = employeeData.get(key);
//                    } else if (key.equalsIgnoreCase("BothSkills")) {
//                        value2 = employeeData.get("PrimarySkills") + ", " + employeeData.get("SecondarySkills");
//                    } else {
//                        value2 = employeeData.get(key);
//                    }
//                    if (value1.equals(value2) || value1.equalsIgnoreCase("NA")) {
//                        passed = true;
//                    } else {
//                        passed = false;
//                        wrongValue = key;
//                        break;
//                    }
//                }
//
//                if (passed) {
//                    GemTestReporter.addTestStep("Verify if " + userHierarchy.get(0) + " has right values",
//                            userHierarchy.get(0) + " has right values", STATUS.PASS, DriverAction.takeSnapShot());
//                } else {
//                    GemTestReporter.addTestStep("Verify if " + userHierarchy.get(0) + " is at right hierarchy or not",
//                            userHierarchy.get(0) + " has wrong value: " + wrongValue, STATUS.FAIL, DriverAction.takeSnapShot());
//                }
//
//                DriverAction.getElement(CommonLocators.crossIcon).click();
//                DriverAction.getElement(CommonLocators.companyLogo).click();
//
//                GenericUtils.waitUntilLoaderDisappear();
//                GenericUtils.waitUntilElementAppear(CommonLocators.chartContainer);
//                LoginSrepDefinition.switchToView("EC");
//            }
//        }
//    }

    @Then("Check employee in EC view for {string} of OrgChart")
    public void checkEmployeeInECViewForOfOrgChart(String ecName) {
        GenericUtils.waitUntilLoaderDisappear();
        GenericUtils.waitUntilElementAppear(CommonLocators.chartContainer);
        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList2();
        int flag = 1;

        assert hashMapList != null;
        for (HashMap<String, String> hashMap : hashMapList) {
            String ecTechValue = hashMap.get("ECTech");

            if (ecTechValue != null && ecTechValue.contains(ecName)) {
                String empName = hashMap.get("EmployeeName");
                String empCode = hashMap.get("EmployeeCode");
                String mentorName = hashMap.get("ECMentorName");
                String mentorCode = hashMap.get("ECMentorId");


                    if (!DriverAction.isExist(CommonLocators.employeeDiv(empName, empCode))) {
                        GemTestReporter.addTestStep(flag + ". Verify if " + empName + " is at right hierarchy or not",
                                empName + " is missing from hierarchy", STATUS.FAIL, DriverAction.takeSnapShot());
                        flag++;
                        continue;
                    }

                    String mentorECTech = GenericUtils.getEcTech(mentorName, mentorCode);
                    assert mentorECTech != null;
                    DriverAction.waitSec(1);
                    if (!mentorECTech.contains(ecName)  && !mentorName.equalsIgnoreCase(chair)) {
                        if (GenericUtils.isEmployeeInFirstRow(firstRowEmployees, empName, empCode)) {
                            GemTestReporter.addTestStep(flag + ". Verify if " + empName + " is at right hierarchy or not",
                                    empName + " is at right hierarchy", STATUS.PASS, DriverAction.takeSnapShot());
                        } else {
                            GemTestReporter.addTestStep(flag + ". Verify if " + empName + " is at right hierarchy or not",
                                    empName + " is at wrong hierarchy", STATUS.FAIL, DriverAction.takeSnapShot());
                        }
                    }
//                    else if (!mentorECTech.contains(ecName) && mentorName.equalsIgnoreCase(chair)) {
//                        if (DriverAction.isExist(CommonLocators.hierarchyCheck(mentorName, mentorCode, empName, empCode))) {
//                            GemTestReporter.addTestStep(flag + ". Verify if " + empName + " is at right hierarchy or not",
//                                    empName + " is at right hierarchy", STATUS.PASS, DriverAction.takeSnapShot());
//                        } else {
//                            GemTestReporter.addTestStep(flag + ". Verify if " + empName + " is at right hierarchy or not",
//                                    empName + " is at wrong hierarchy", STATUS.FAIL, DriverAction.takeSnapShot());
//                        }
//                    }
                    else {
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
