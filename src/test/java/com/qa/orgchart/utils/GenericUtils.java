package com.qa.orgchart.utils;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.orgchart.locators.CommonLocators;
import com.qa.orgchart.stepDefinitions.jsonToHash;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GenericUtils {
    public static void waitUntilElementDisappear(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 60);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
public static void waitUntilLoaderDisappear(){
    WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 60);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(CommonLocators.loader));
}
    public static void waitUntilElementAppear(By locator) {
        WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(60));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }





    @Given("^Switch to \"(.*)\" view$")
    public void switchToView(String viewName) {
        try {
            GenericUtils.waitUntilLoaderDisappear();
            GenericUtils.waitUntilElementAppear(CommonLocators.dropdownBox);
            DriverAction.click(CommonLocators.dropdownBox);
            GenericUtils.waitUntilLoaderDisappear();
            GenericUtils.waitUntilElementAppear(CommonLocators.viewValue(viewName));
            DriverAction.click(CommonLocators.viewValue(viewName));
        }catch (Exception e){
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    public static List<String> getHierarchy(int index){
        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList();

        HashMap<String, String> employee = hashMapList.get(index);
        String name = "";
        String code= "";
        List<String> hierarchy = new ArrayList<>();
        hierarchy.add(employee.get("EmployeeName"));
        hierarchy.add(employee.get("EmployeeCode"));
        name =  employee.get("ReportingManager");
        code = employee.get("ManagerCode");
        while(!name.equalsIgnoreCase("Vishal Malik")){
            for (int i = 0; i < hashMapList.size(); i++) {
                HashMap<String, String> hashMap = hashMapList.get(i);
                if (hashMap.containsKey("EmployeeName") && hashMap.containsKey("EmployeeCode")
                        && hashMap.get("EmployeeName").equals(name) && hashMap.get("EmployeeCode").equals(code)) {
                    hierarchy.add(hashMap.get("EmployeeName"));
                    hierarchy.add(hashMap.get("EmployeeCode"));
                    name =  hashMap.get("ReportingManager");
                    code = hashMap.get("ManagerCode");
                    break;
                }
            }
        }
       return hierarchy;
    }

    public static List<String> getECHierarchy(int index){
        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList();
        HashMap<String, String> employee = hashMapList.get(0);

        String name = "";
        String code= "";
        String ecTech =  employee.get("ECTech");
        List<String> hierarchy = new ArrayList<>();
        hierarchy.add(employee.get("EmployeeName"));
        hierarchy.add(employee.get("EmployeeCode"));
        name =  employee.get("ECMentorName");
        code = employee.get("ECMentorId");
        outerLoop:    while(!name.equalsIgnoreCase("Anil Pahal") && !name.equalsIgnoreCase("Anil Singh") && !name.equalsIgnoreCase("Vishal Malik")
                && !name.equalsIgnoreCase("Neeraj Yadav")){
            innerLoop:  for (int i = 0; i < hashMapList.size(); i++) {
                HashMap<String, String> hashMap = hashMapList.get(i);
                if (hashMap.containsKey("ECMentorName") && hashMap.containsKey("EmployeeName") && hashMap.containsKey("EmployeeCode")
                        && hashMap.get("EmployeeName").equals(name) && hashMap.get("EmployeeCode").equals(code)) {
                    if(hashMap.get("ECTech").equalsIgnoreCase(ecTech)){
                        hierarchy.add(hashMap.get("EmployeeName"));
                        hierarchy.add(hashMap.get("EmployeeCode"));
                        name =  hashMap.get("ECMentorName");
                        code = hashMap.get("ECMentorId");
                        break;
                    }
                    break;
                }
                else if(!hashMap.containsKey("ECMentorName") && name.equalsIgnoreCase("Lovish Sanghvi")){
                    hierarchy.add("Lovish Sanghvi");
                    hierarchy.add("GSU 117");
                    break outerLoop;
                }
            }
        }
        hierarchy.add(ecTech);
        return hierarchy;
    }

}
