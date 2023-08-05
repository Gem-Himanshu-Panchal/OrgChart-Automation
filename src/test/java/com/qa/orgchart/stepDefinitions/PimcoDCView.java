package com.qa.orgchart.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.orgchart.locators.CommonLocators;
import com.qa.orgchart.utils.GenericUtils;
import io.cucumber.java.en.Given;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PimcoDCView {
    @Given("Check employee in PIMCODC view for {string} of OrgChart")
    public void check_for_to_employee_in_pimcodc_view_of_org_chart(String dcTechName) {
        boolean passed = false;
        GenericUtils.waitUntilLoaderDisappear();
        GenericUtils.waitUntilElementAppear(CommonLocators.chartContainer);
        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList2();
        int flag = 1;
        for (HashMap<String, String> hashMap : hashMapList){
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
                if (!mentorDCTech.contains(dcTechName) && !mentorSecondaryDCTech.contains(dcTechName)) {
                    if (!DriverAction.isExist(CommonLocators.hierarchyCheck(mentorName,mentorCode, empName, empCode))) {
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
                scrollToElement.scrollToElement(empName,empCode);
                DriverAction.getElement(CommonLocators.employeeDiv(empName, empCode)).click();
                GenericUtils.waitUntilElementAppear(CommonLocators.infoCard);
                DriverAction.waitSec(2);

                HashMap<String, String> employeeData = hashMap;
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
                    GemTestReporter.addTestStep("Verify if " + empName + " has right values",
                            empName + " has right values", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verify if " + empName + " is at right hierarchy or not",
                            empName + " has wrong value: " + wrongValue, STATUS.FAIL, DriverAction.takeSnapShot());
                }

                DriverAction.getElement(CommonLocators.crossIcon).click();

            }

        }


    }
}
