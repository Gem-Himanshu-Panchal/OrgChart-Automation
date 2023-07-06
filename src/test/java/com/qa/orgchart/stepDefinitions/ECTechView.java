package com.qa.orgchart.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.orgchart.locators.CommonLocators;
import com.qa.orgchart.utils.GenericUtils;
import groovy.util.logging.Log;
import io.cucumber.java.en.Given;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ECTechView {
    boolean passed = false;

    @Given("Search for {int} to {int} employee in EC view of OrgChart")
    public void search_for_to_employee_in_ec_view_of_org_chart(Integer start, Integer end) {
        GenericUtils.waitUntilLoaderDisappear();
        GenericUtils.waitUntilElementAppear(CommonLocators.chartContainer);
        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList();
        String mentorName = null;
        String mentorCode = null;
        for (int i = start; i < end; i++) {
//           5 is for aaradhya ahuja, 11 for aayush kalra, 32 for abhirakshit, 122 anil pahal, 123 for anil singh, 211 ashish verma
            if (i == 122 || i == 123) {
                System.out.println("null");
            } else {
                List<String> userHierarchy = GenericUtils.getECHierarchy(i);

                int lastIndex = userHierarchy.size() - 1;
                String EcTech = userHierarchy.get(lastIndex);
                userHierarchy.remove(lastIndex);
                DriverAction.scrollIntoView(CommonLocators.ecTeamBox(EcTech));
                DriverAction.hoverOver(CommonLocators.ecTeamBox(EcTech));
                DriverAction.scrollToBottom();
                GenericUtils.waitUntilElementAppear(CommonLocators.ecTeamBoxDownKey(EcTech));
                DriverAction.getElement(CommonLocators.ecTeamBoxDownKey(EcTech)).click();
                lastIndex = userHierarchy.size() - 1;
                while (userHierarchy.size() != 2) {
                    DriverAction.scrollIntoView(CommonLocators.ecEmployeeBox(userHierarchy.get(lastIndex - 1), userHierarchy.get(lastIndex)));
                    DriverAction.scrollToBottom();
                    DriverAction.hoverOver(CommonLocators.ecEmployeeBox(userHierarchy.get(lastIndex - 1), userHierarchy.get(lastIndex)));
                    DriverAction.scrollIntoView(CommonLocators.ecTeamDownKey(userHierarchy.get(lastIndex - 1), userHierarchy.get(lastIndex)));
                    DriverAction.scrollToBottom();
                    DriverAction.getElement(CommonLocators.ecTeamDownKey(userHierarchy.get(lastIndex - 1), userHierarchy.get(lastIndex))).click();
                    mentorName = userHierarchy.get(lastIndex - 1);
                    mentorCode = userHierarchy.get(lastIndex);
                    userHierarchy.remove(lastIndex);
                    userHierarchy.remove(lastIndex - 1);
                    lastIndex = userHierarchy.size() - 1;
                }
                DriverAction.waitSec(1);

                DriverAction.scrollIntoView(CommonLocators.hierarchyCheck(mentorName, mentorCode, userHierarchy.get(0), userHierarchy.get(1)));
                if (mentorName != null && mentorCode != null) {
                    if (DriverAction.isExist(CommonLocators.hierarchyCheck(mentorName, mentorCode, userHierarchy.get(0), userHierarchy.get(1)))) {
                        GemTestReporter.addTestStep("Verify if " + userHierarchy.get(0) + " is at right hierarchy or not",
                                userHierarchy.get(0) + " is at right hierarchy", STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify if " + userHierarchy.get(0) + " is at right hierarchy or not",
                                userHierarchy.get(0) + " is at wrong hierarchy", STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                } else {
                    if (DriverAction.isExist(CommonLocators.noMentorHierarchy(EcTech, userHierarchy.get(0),  userHierarchy.get(1)))) {
                        GemTestReporter.addTestStep("Verify if " + userHierarchy.get(0) + " is at right hierarchy or not",
                                userHierarchy.get(0) + " is at right hierarchy", STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verify if " + userHierarchy.get(0) + " is at right hierarchy or not",
                                userHierarchy.get(0) + " is at wrong hierarchy", STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                }

                System.out.println(CommonLocators.employeeDiv(userHierarchy.get(0), userHierarchy.get(1)));
                DriverAction.getElement(CommonLocators.employeeDiv(userHierarchy.get(0), userHierarchy.get(1))).click();
                GenericUtils.waitUntilElementAppear(CommonLocators.infoCard);
                DriverAction.waitSec(2);

                assert hashMapList != null;
                HashMap<String, String> employeeData = hashMapList.get(i);
                String str = employeeData.get("DateOfJoining");
                str = str.substring(0, 10);
                LocalDate date = LocalDate.parse(str);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String formattedDate = date.format(formatter);
                employeeData.put("DateOfJoining", formattedDate);

                HashMap<String, String> extractData = new HashMap<>();
                extractData.put("ImagePath", DriverAction.getAttributeName(CommonLocators.employeeProfile, "src"));
                extractData.put("EmployeeName", DriverAction.getElementText(CommonLocators.employeeDataSet1(employeeData.get("EmployeeName"))));
                extractData.put("EmployeeCode", DriverAction.getElementText(CommonLocators.employeeDataSet1(employeeData.get("EmployeeCode"))).substring(1, DriverAction.getElementText(CommonLocators.employeeDataSet1(employeeData.get("EmployeeCode"))).length() - 1));
                extractData.put("Designation", DriverAction.getElementText(CommonLocators.employeeDataSet1(employeeData.get("Designation"))));
                extractData.put("EmailId", DriverAction.getElementText(CommonLocators.employeeDataSet3("Email")));
                extractData.put("MobileNumber", DriverAction.getElementText(CommonLocators.employeeDataSet3("Phone Number")));
                extractData.put("Location", DriverAction.getElementText(CommonLocators.employeeDataSet3("Location")));
                extractData.put("DateOfJoining", DriverAction.getElementText(CommonLocators.employeeDataSet3("Date of Joining")));
                String[] temp = DriverAction.getElementText(CommonLocators.employeeDataSet4("Experience")).split(" ");
                extractData.put("OverallExp", temp[0]);
                extractData.put("ECTech", DriverAction.getElementText(CommonLocators.employeeDataSet3("Engineering Council")));
                extractData.put("DCTech", DriverAction.getElementText(CommonLocators.employeeDataSet3("Delivery Council")));
                if (employeeData.containsKey("PrimarySkills") && employeeData.containsKey("SecondarySkills"))
                    extractData.put("BothSkills", DriverAction.getElementText(CommonLocators.employeeDataSet3("Skills")));
                else if (employeeData.containsKey("PrimarySkills"))
                    extractData.put("PrimarySkills", DriverAction.getElementText(CommonLocators.employeeDataSet3("Skills")));
                else if (employeeData.containsKey("SecondarySkills"))
                    extractData.put("SecondarySkills", DriverAction.getElementText(CommonLocators.employeeDataSet3("Skills")));

                extractData.put("ECMentorName", DriverAction.getElementText(CommonLocators.employeeDataSet3("EC Mentor")));


                String wrongValue = "";
                for (Map.Entry<String, String> entry : extractData.entrySet()) {
                    String key = entry.getKey();
                    String value1 = entry.getValue();
                    String value2 = "";
                    if (key.equalsIgnoreCase("PrimarySkills")) {
                        value2 = employeeData.get(key);
                    } else if (key.equalsIgnoreCase("SecondarySkills")) {
                        value2 = employeeData.get(key);
                    } else if (key.equalsIgnoreCase("BothSkills")) {
                        value2 = employeeData.get("PrimarySkills") + ", " + employeeData.get("SecondarySkills");
                    } else {
                        value2 = employeeData.get(key);
                    }
                    if (value1.equals(value2) || value1.equalsIgnoreCase("NA")) {
                        passed = true;
                    } else {
                        passed = false;
                        wrongValue = key;
                        break;
                    }
                }

                if (passed) {
                    GemTestReporter.addTestStep("Verify if " + userHierarchy.get(0) + " has right values",
                            userHierarchy.get(0) + " has right values", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verify if " + userHierarchy.get(0) + " is at right hierarchy or not",
                            userHierarchy.get(0) + " has wrong value: " + wrongValue, STATUS.FAIL, DriverAction.takeSnapShot());
                }

                DriverAction.getElement(CommonLocators.crossIcon).click();
                DriverAction.getElement(CommonLocators.companyLogo).click();

                GenericUtils.waitUntilLoaderDisappear();
                GenericUtils.waitUntilElementAppear(CommonLocators.chartContainer);
                LoginSrepDefinition.switchToView("EC");
            }
        }
    }
}
