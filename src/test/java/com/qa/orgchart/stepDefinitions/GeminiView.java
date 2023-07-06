package com.qa.orgchart.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.orgchart.locators.CommonLocators;
import com.qa.orgchart.utils.GenericUtils;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeminiView {
    boolean passed = false;

    @Given("Search for {int} to {int} employee in OrgChart")
    public void searchForToEmployeeInOrgChart(int start, int end) {
        try {
            GenericUtils.waitUntilLoaderDisappear();
            List<HashMap<String, String>> hashMapList = jsonToHash.getHashList2();

//            79 is for Akash Verma, 1081 for Vishal Malik and 1106 for Yogi rana
            for (int i = start; i < end; i++) {
                if(i==79 || i==1081 ||i==1106){
                    i++;
                }
                List<String> userHierarchy = GenericUtils.getHierarchy(i);

                int lastIndex = userHierarchy.size() - 1;
                while (userHierarchy.size() != 2) {
                    DriverAction.scrollIntoView(CommonLocators.dataSource("name", userHierarchy.get(lastIndex - 1), "EmployeeCode", userHierarchy.get(lastIndex)));
                    DriverAction.scrollToBottom();
                    DriverAction.hoverOver(CommonLocators.dataSource("name", userHierarchy.get(lastIndex - 1), "EmployeeCode", userHierarchy.get(lastIndex)));
                    DriverAction.scrollIntoView(CommonLocators.downArrowDataSource("name", userHierarchy.get(lastIndex - 1), "EmployeeCode", userHierarchy.get(lastIndex)));
                    DriverAction.scrollToBottom();
//                    DriverAction.scrollIntoView(CommonLocators.downArrowDataSource("name", userHierarchy.get(lastIndex - 1), "EmployeeCode", userHierarchy.get(lastIndex)));
//                    DriverAction.scrollToBottom();
                    DriverAction.getElement(CommonLocators.downArrowDataSource("name", userHierarchy.get(lastIndex - 1), "EmployeeCode", userHierarchy.get(lastIndex))).click();
                    userHierarchy.remove(lastIndex);
                    userHierarchy.remove(lastIndex - 1);
                    lastIndex = userHierarchy.size() - 1;
                }
                DriverAction.waitSec(1);
                DriverAction.scrollIntoView(CommonLocators.dataSource("name", userHierarchy.get(0), "EmployeeCode", userHierarchy.get(1)));
                if (DriverAction.isExist(CommonLocators.dataSource("name", userHierarchy.get(0), "EmployeeCode", userHierarchy.get(lastIndex)))) {
                    GemTestReporter.addTestStep("Verify if " + userHierarchy.get(0) + " is at right hierarchy or not",
                            userHierarchy.get(0) + " is at right hierarchy", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verify if " + userHierarchy.get(0) + " is at right hierarchy or not",
                            userHierarchy.get(0) + " is at wrong hierarchy", STATUS.FAIL, DriverAction.takeSnapShot());
                }
                DriverAction.getElement(CommonLocators.employeeDiv(userHierarchy.get(0),  userHierarchy.get(1))).click();
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
                extractData.put("ECTech", DriverAction.getElementText(CommonLocators.employeeDataSet3("EC Tech")));
                extractData.put("DCTech", DriverAction.getElementText(CommonLocators.employeeDataSet3("DC Tech")));
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
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Given("^Search for any duplicate employee in OrgChart Gemini view$")
    public void searchForAnyDuplicateEmployeeInOrgChartGeminiView() {
        try{
            List<HashMap<String, String>> hashMapList = jsonToHash.getHashList();

            for(int i=0;i<hashMapList.size();i++){
                String name = hashMapList.get(i).get("EmployeeName");
               String code =  hashMapList.get(i).get("EmployeeCode");

               List<WebElement> li = DriverAction.getElements(CommonLocators.dataSource("name",name,"EmployeeCode",""));

               if(li.size()>1){
                   System.out.println(name+" "+code);
               }

            }
        }catch (Exception e){
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }
}
