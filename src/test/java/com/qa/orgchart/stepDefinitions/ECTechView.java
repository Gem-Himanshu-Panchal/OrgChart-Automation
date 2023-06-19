package com.qa.orgchart.stepDefinitions;

import com.gemini.generic.ui.utils.DriverAction;
import com.qa.orgchart.locators.CommonLocators;
import com.qa.orgchart.utils.GenericUtils;
import io.cucumber.java.en.Given;

import java.util.HashMap;
import java.util.List;

public class ECTechView {
    @Given("Search for {int} to {int} employee in EC view of OrgChart")
    public void search_for_to_employee_in_ec_view_of_org_chart(Integer start, Integer end) {
        GenericUtils.waitUntilLoaderDisappear();
        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList();

        for (int i = start; i < end; i++) {
            List<String> userHierarchy = GenericUtils.getECHierarchy(i);

            int lastIndex = userHierarchy.size() - 1;
            String EcTech = userHierarchy.get(lastIndex);
            userHierarchy.remove(lastIndex);
            DriverAction.scrollIntoView(CommonLocators.ecTeamBox(EcTech));
            DriverAction.hoverOver(CommonLocators.ecTeamBox(EcTech));
            DriverAction.scrollToBottom();
            DriverAction.getElement(CommonLocators.downArrowDataSource("name", userHierarchy.get(lastIndex - 1), "EmployeeCode", userHierarchy.get(lastIndex))).click();

            while (userHierarchy.size() != 2) {
                DriverAction.scrollIntoView(CommonLocators.dataSource("name", userHierarchy.get(lastIndex - 1), "EmployeeCode", userHierarchy.get(lastIndex)));
                DriverAction.scrollToBottom();
                DriverAction.hoverOver(CommonLocators.dataSource("name", userHierarchy.get(lastIndex - 1), "EmployeeCode", userHierarchy.get(lastIndex)));
                DriverAction.scrollIntoView(CommonLocators.downArrowDataSource("name", userHierarchy.get(lastIndex - 1), "EmployeeCode", userHierarchy.get(lastIndex)));
                DriverAction.scrollToBottom();
                DriverAction.scrollIntoView(CommonLocators.downArrowDataSource("name", userHierarchy.get(lastIndex - 1), "EmployeeCode", userHierarchy.get(lastIndex)));
                DriverAction.scrollToBottom();
                DriverAction.getElement(CommonLocators.downArrowDataSource("name", userHierarchy.get(lastIndex - 1), "EmployeeCode", userHierarchy.get(lastIndex))).click();
                userHierarchy.remove(lastIndex);
                userHierarchy.remove(lastIndex - 1);
                lastIndex = userHierarchy.size() - 1;
            }
        }

    }
}
