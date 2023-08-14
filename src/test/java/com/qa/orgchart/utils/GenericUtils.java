package com.qa.orgchart.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.orgchart.locators.CommonLocators;
import com.qa.orgchart.stepDefinitions.jsonToHash;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qa.orgchart.stepDefinitions.getEmployeeIndex.getIndex;

public class GenericUtils {
    public static void waitUntilElementDisappear(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 60);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitUntilLoaderDisappear() {
        if (DriverAction.isExist(CommonLocators.loader)) {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 60);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(CommonLocators.loader));
        }
    }

    public static void waitUntilElementAppear(By locator) {
        if (DriverAction.isExist(locator)) {
            WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(60));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
    }

    public static List<String> getHierarchy(int index) {
        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList2();

        HashMap<String, String> employee = hashMapList.get(index);
        String name = "";
        String code = "";
        List<String> hierarchy = new ArrayList<>();
        hierarchy.add(employee.get("EmployeeName"));
        hierarchy.add(employee.get("EmployeeCode"));
        name = employee.get("ReportingManager");
        code = employee.get("ManagerCode");
        while (!name.equalsIgnoreCase("Vishal Malik")) {
            for (int i = 0; i < hashMapList.size(); i++) {
                HashMap<String, String> hashMap = hashMapList.get(i);
                if (hashMap.containsKey("EmployeeName") && hashMap.containsKey("EmployeeCode")
                        && hashMap.get("EmployeeName").equals(name) && hashMap.get("EmployeeCode").equals(code)) {
                    hierarchy.add(hashMap.get("EmployeeName"));
                    hierarchy.add(hashMap.get("EmployeeCode"));
                    name = hashMap.get("ReportingManager");
                    code = hashMap.get("ManagerCode");
                    break;
                }
            }
        }
        return hierarchy;
    }

    public static List<String> getECHierarchy(int index) {
        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList2();
        assert hashMapList != null;
        HashMap<String, String> employee = hashMapList.get(index);

        String name = "";
        String code = "";
        String ecTech = employee.get("ECTech");
        List<String> hierarchy = new ArrayList<>();
        hierarchy.add(employee.get("EmployeeName"));
        hierarchy.add(employee.get("EmployeeCode"));
        name = employee.get("ECMentorName");
        code = employee.get("ECMentorId");
        int flag = getIndex(name, code);
        HashMap<String, String> nextHash = hashMapList.get(flag);
        if (nextHash.get("ECTech").equalsIgnoreCase(ecTech) && !nextHash.get("ECTech").equalsIgnoreCase("NA") && !name.equalsIgnoreCase("Shabnam Gandhi")) {
            Outerloop:
            while (!name.equalsIgnoreCase("Anil Pahal") && !name.equalsIgnoreCase("Anil Singh") && !name.equalsIgnoreCase("Vishal Malik")
                    && !name.equalsIgnoreCase("Neeraj Yadav") && !name.equalsIgnoreCase("NA")) {
                for (int i = 0; i < hashMapList.size(); i++) {
                    HashMap<String, String> hashMap = hashMapList.get(i);
                    if (hashMap.get("EmployeeName").equalsIgnoreCase(name) && hashMap.get("EmployeeCode").equalsIgnoreCase(code) && hashMap.get("ECTech").equalsIgnoreCase(ecTech)) {
                        hierarchy.add(hashMap.get("EmployeeName"));
                        hierarchy.add(hashMap.get("EmployeeCode"));
                        name = hashMap.get("ECMentorName");
                        code = hashMap.get("ECMentorId");
                        flag = getIndex(name, code);
                        HashMap<String, String> nextHash2 = hashMapList.get(flag);
                        if (!nextHash2.get("ECTech").equalsIgnoreCase(ecTech))
                            break Outerloop;
                        if (name == null)
                            break Outerloop;
                    } else if (!hashMap.containsKey("ECMentorName") && name.equalsIgnoreCase("Lovish Sanghvi")) {
                        hierarchy.add("Lovish Sanghvi");
                        hierarchy.add("GSU 117");
                        break Outerloop;
                    } else if (!hashMap.containsKey("ECMentorName") && name.equalsIgnoreCase("Akshay Gupta")) {
                        hierarchy.add("Akshay Gupta");
                        hierarchy.add("GSU 146");
                        break Outerloop;
                    }
                }
            }
        }

        if (ecTech.equalsIgnoreCase("Infrastructure/IT") && name.equalsIgnoreCase("Prashank Chaudhary")) {
            hierarchy.add("Prashank Chaudhary");
            hierarchy.add("GSI N 015");
        } else if (ecTech.equalsIgnoreCase("Training") && name.equalsIgnoreCase("Amit Kumar Tomar")) {
            hierarchy.add("Amit Kumar Tomar");
            hierarchy.add("GSI G 1306");
        } else if (ecTech.equalsIgnoreCase("Data Engineering") && hierarchy.contains("Ashish Agrawal")) {
            int lastIndex = hierarchy.size() - 1;
            hierarchy.remove(lastIndex);
            hierarchy.remove(lastIndex - 1);
        }
        hierarchy.add(ecTech);
        return hierarchy;
    }

    public static List<String> getDCHierarchy(int index) {
        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList2();

        HashMap<String, String> employee = hashMapList.get(index);
        String name = "";
        String code = "";
        String dcTech = employee.get("DCTech");
        List<String> hierarchy = new ArrayList<>();
        hierarchy.add(employee.get("EmployeeName"));
        hierarchy.add(employee.get("EmployeeCode"));
        name = employee.get("ReportingManager");
        code = employee.get("ManagerCode");
        while (!name.equalsIgnoreCase("Vishal Malik")) {
            for (int i = 0; i < hashMapList.size(); i++) {
                HashMap<String, String> hashMap = hashMapList.get(i);
                if (hashMap.containsKey("EmployeeName") && hashMap.containsKey("EmployeeCode")
                        && hashMap.get("EmployeeName").equals(name) && hashMap.get("EmployeeCode").equals(code)) {
                    hierarchy.add(hashMap.get("EmployeeName"));
                    hierarchy.add(hashMap.get("EmployeeCode"));
                    name = hashMap.get("ReportingManager");
                    code = hashMap.get("ManagerCode");
                    break;
                }
            }
        }
        if (hierarchy.contains("Anil Singh") && hierarchy.contains("GSI G 818")) {
            hierarchy.remove("Anil Singh");
            hierarchy.remove("GSI G 818");
        }
        if (hierarchy.contains("Neeraj Yadav") && hierarchy.contains("GSI N 002")) {
            hierarchy.remove("Neeraj Yadav");
            hierarchy.remove("GSI N 002");
        }
        hierarchy.add(dcTech);
        return hierarchy;
    }

    public static List<String> getPimcoDCHierarchy(int index) {
//        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList();
//        List<HashMap<String, String>> hashMapListFull = jsonToHash.getHashList2();
//
//        HashMap<String, String> employee = hashMapList.get(index);
//
//        String name = "";
//        String code = "";
//        String dcTech = employee.get("DCTech");
//        String secondaryDC = employee.get("SecondaryDCs");
//        List<String> hierarchy = new ArrayList<>();
//        int flag;
//        if (dcTech.contains("Pimco") || secondaryDC.contains("Pimco")) {
//            hierarchy.add(employee.get("EmployeeName"));
//            hierarchy.add(employee.get("EmployeeCode"));
//            name = employee.get("ReportingManager");
//            code = employee.get("ManagerCode");
//            Outerloop:
//            while (!name.equalsIgnoreCase("Vishal Malik")) {
//                for (int i = 0; i < hashMapList.size(); i++) {
//                    HashMap<String, String> hashMap = hashMapList.get(i);
//
//                    if (hashMap.get("EmployeeName").equalsIgnoreCase(name) && hashMap.get("EmployeeCode").equalsIgnoreCase(code) && hashMap.get("DCTech").equalsIgnoreCase(dcTech)) {
//                        hierarchy.add(hashMap.get("EmployeeName"));
//                        hierarchy.add(hashMap.get("EmployeeCode"));
//                        name = hashMap.get("ReportingManager");
//                        code = hashMap.get("ManagerCode");
//                        flag = getIndex(name, code);
//                        HashMap<String, String> nextHash2 = hashMapListFull.get(flag);
////                        if(nextHash2.containsKey("SecondaryDCs") && !nextHash2.get("SecondaryDCs").equalsIgnoreCase(dcTech)){
////                            break Outerloop;
////                        }
//                        if (!nextHash2.get("DCTech").equalsIgnoreCase(dcTech)){
//                            break Outerloop;
//                        }
//
//                        if (name == null)
//                            break Outerloop;
//                    }
//                }
//            }
//
//            if(dcTech.equalsIgnoreCase("Pimco RiskOps") && !hierarchy.contains("Munit Goyal")){
//                hierarchy.add("Munit Goyal");
//                hierarchy.add("GSI G 036");
//
//            }
//            if (hierarchy.contains("Anil Singh") && hierarchy.contains("GSI G 818")) {
//                hierarchy.remove("Anil Singh");
//                hierarchy.remove("GSI G 818");
//            }
//            if (hierarchy.contains("Neeraj Yadav") && hierarchy.contains("GSI N 002")) {
//                hierarchy.remove("Neeraj Yadav");
//                hierarchy.remove("GSI N 002");
//            }
//            if (hierarchy.contains("Amit Kumar Tomar")) {
//                int lastIndex = hierarchy.size() - 1;
//                hierarchy.remove(lastIndex);
//                hierarchy.remove(lastIndex - 1);
//            }
//
//            if (dcTech.contains("Pimco Portfolio Analytics Support")) {
//                hierarchy.add("Sudhanshu Malhotra");
//                hierarchy.add("GSI G 1176");
//
//            }
//            if (dcTech.contains("Pimco")) {
//                hierarchy.add(dcTech);
//            } else if (secondaryDC.contains("Pimco")) {
//                hierarchy.add(secondaryDC);
//            }
//        }
//
//        System.out.println(hierarchy);


        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList2();
        List<HashMap<String, String>> hashMapListFull = jsonToHash.getHashList2();

        HashMap<String, String> employee = hashMapList.get(index);

        String name = "";
        String code = "";
        String dcTech = employee.get("DCTech");
        String secondaryDC = employee.get("SecondaryDCs");
        List<String> hierarchy = new ArrayList<>();
        int flag = 0;

        if (dcTech.contains("Pimco")) {
            hierarchy.add(employee.get("EmployeeName"));
            hierarchy.add(employee.get("EmployeeCode"));
            name = employee.get("ReportingManager");
            code = employee.get("ManagerCode");

            Outerloop:
            while (!name.equalsIgnoreCase("Vishal Malik")) {
                for (int i = 0; i < hashMapList.size(); i++) {
                    HashMap<String, String> hashMap = hashMapList.get(i);
                    if (name.equalsIgnoreCase("Prashank Chaudhary") && dcTech.contains("Pimco Infrastructure")) {
                        break Outerloop;
                    }
                    if (hashMap.get("EmployeeName").equalsIgnoreCase(name) && hashMap.get("EmployeeCode").equalsIgnoreCase(code) && hashMap.get("DCTech").equalsIgnoreCase(dcTech)) {
                        flag = getIndex(name, code);
                        HashMap<String, String> nextHash2 = hashMapListFull.get(flag);

                        if (nextHash2.containsKey("DCTech") && !nextHash2.get("DCTech").equalsIgnoreCase(dcTech)) {
                            if (nextHash2.containsKey("SecondaryDCs") && !nextHash2.get("SecondaryDCs").contains(dcTech)) {
                                break Outerloop;
                            }
                            break Outerloop;
                        }

                        hierarchy.add(hashMap.get("EmployeeName"));
                        hierarchy.add(hashMap.get("EmployeeCode"));
                        name = hashMap.get("ReportingManager");
                        code = hashMap.get("ManagerCode");

                        if (name.equalsIgnoreCase("Neeraj Yadav")) {
                            break Outerloop;
                        }
                        if (name == null) break Outerloop;
                    } else if (hashMap.get("EmployeeName").equalsIgnoreCase(name) && hashMap.get("EmployeeCode").equalsIgnoreCase(code) && hashMap.get("SecondaryDCs").equalsIgnoreCase(dcTech)) {
                        hierarchy.add(hashMap.get("EmployeeName"));
                        hierarchy.add(hashMap.get("EmployeeCode"));
                        name = hashMap.get("ReportingManager");
                        code = hashMap.get("ManagerCode");
                        flag = getIndex(name, code);
                        HashMap<String, String> nextHash2 = hashMapListFull.get(flag);
                        if ((nextHash2.containsKey("SecondaryDCs") && !nextHash2.get("SecondaryDCs").equalsIgnoreCase(dcTech)) ||
                                !nextHash2.get("DCTech").equalsIgnoreCase(dcTech)) {
                            break Outerloop;
                        }

                        if (name == null) break Outerloop;
                    }
                }
            }

        } else if (secondaryDC.contains("Pimco")) {
            hierarchy.add(employee.get("EmployeeName"));
            hierarchy.add(employee.get("EmployeeCode"));
            name = employee.get("ReportingManager");
            code = employee.get("ManagerCode");

            Outerloop:
            while (!name.equalsIgnoreCase("Vishal Malik")) {
                for (int i = 0; i < hashMapList.size(); i++) {
                    HashMap<String, String> hashMap = hashMapList.get(i);
                    if (hashMap.get("EmployeeName").equalsIgnoreCase(name) && hashMap.get("EmployeeCode").equalsIgnoreCase(code) && hashMap.get("DCTech").equalsIgnoreCase(secondaryDC)) {
                        hierarchy.add(hashMap.get("EmployeeName"));
                        hierarchy.add(hashMap.get("EmployeeCode"));
                        name = hashMap.get("ReportingManager");
                        code = hashMap.get("ManagerCode");
                        flag = getIndex(name, code);
                        HashMap<String, String> nextHash2 = hashMapListFull.get(flag);
                        if (!nextHash2.get("DCTech").equalsIgnoreCase(secondaryDC)) {
                            break Outerloop;
                        }

                        if (name == null) break Outerloop;
                    } else if (hashMap.get("EmployeeName").equalsIgnoreCase(name) && hashMap.get("EmployeeCode").equalsIgnoreCase(code) && hashMap.get("SecondaryDCs").equalsIgnoreCase(secondaryDC)) {
                        hierarchy.add(hashMap.get("EmployeeName"));
                        hierarchy.add(hashMap.get("EmployeeCode"));
                        name = hashMap.get("ReportingManager");
                        code = hashMap.get("ManagerCode");
                        flag = getIndex(name, code);
                        HashMap<String, String> nextHash2 = hashMapListFull.get(flag);
                        if ((nextHash2.containsKey("SecondaryDCs") && !nextHash2.get("SecondaryDCs").equalsIgnoreCase(secondaryDC)) ||
                                !nextHash2.get("DCTech").equalsIgnoreCase(secondaryDC)) {
                            break Outerloop;
                        }

                        if (name == null) break Outerloop;
                    }
                }
            }

        }
        if (dcTech.equalsIgnoreCase("Pimco Infrastructure")) {
            hierarchy.add("Prashank Chaudhary");
            hierarchy.add("GSI N 015");
        }
        if (hierarchy.contains("Anil Singh") && hierarchy.contains("GSI G 818")) {
            hierarchy.remove("Anil Singh");
            hierarchy.remove("GSI G 818");
        }
        if (hierarchy.contains("Neeraj Yadav") && hierarchy.contains("GSI N 002")) {
            hierarchy.remove("Neeraj Yadav");
            hierarchy.remove("GSI N 002");
        }
        if (hierarchy.contains("Amit Kumar Tomar")) {
            int lastIndex = hierarchy.size() - 1;
            hierarchy.remove(lastIndex);
            hierarchy.remove(lastIndex - 1);
        }

        if (dcTech.contains("Pimco Portfolio Analytics Support")) {
            hierarchy.add("Sudhanshu Malhotra");
            hierarchy.add("GSI G 1176");

        }
        if (dcTech.contains("Pimco")) {
            hierarchy.add(dcTech);
        } else if (secondaryDC.contains("Pimco")) {
            hierarchy.add(secondaryDC);
        }

        return hierarchy;
    }


    public static String getDcTech(String name, String code) {
        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList2();
        assert hashMapList != null;
        for (HashMap<String, String> hm : hashMapList) {
            if (hm.containsKey("EmployeeName") && hm.get("EmployeeName").equalsIgnoreCase(name) &&
                    hm.containsKey("EmployeeCode") && hm.get("EmployeeCode").equalsIgnoreCase(code)) {
                return hm.get("DCTech");
            }
        }
        return null;
    }

    public static String getSecondaryDcTech(String name, String code) {
        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList2();
        assert hashMapList != null;
        for (HashMap<String, String> hm : hashMapList) {
            if (hm.containsKey("EmployeeName") && hm.get("EmployeeName").equalsIgnoreCase(name) &&
                    hm.containsKey("EmployeeCode") && hm.get("EmployeeCode").equalsIgnoreCase(code) && hm.containsKey("SecondaryDCs")) {
                return hm.get("SecondaryDCs");
            }
        }
        return "Nil";
    }

    public static List<String> verifyEmployeeDetails(HashMap<String, String> hashMap) {
        boolean passed = false;
        String str = hashMap.get("DateOfJoining");
        str = str.substring(0, 10);
        LocalDate date = LocalDate.parse(str);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(formatter);
        hashMap.put("DateOfJoining", formattedDate);

        HashMap<String, String> extractData = new HashMap<>();
        extractData.put("ImagePath", DriverAction.getAttributeName(CommonLocators.employeeProfile, "src"));
        extractData.put("EmployeeName", DriverAction.getElementText(CommonLocators.employeeDataSet1(hashMap.get("EmployeeName"))));
        extractData.put("EmployeeCode", DriverAction.getElementText(CommonLocators.employeeDataSet1(hashMap.get("EmployeeCode"))).substring(1, DriverAction.getElementText(CommonLocators.employeeDataSet1(hashMap.get("EmployeeCode"))).length() - 1));
        extractData.put("Designation", DriverAction.getElementText(CommonLocators.employeeDataSet1(hashMap.get("Designation"))));
        extractData.put("EmailId", DriverAction.getElementText(CommonLocators.employeeDataSet3("Email")));
        extractData.put("MobileNumber", DriverAction.getElementText(CommonLocators.employeeDataSet3("Phone Number")));
        extractData.put("Location", DriverAction.getElementText(CommonLocators.employeeDataSet3("Location")));
        extractData.put("DateOfJoining", DriverAction.getElementText(CommonLocators.employeeDataSet3("Date of Joining")));
        String[] temp = DriverAction.getElementText(CommonLocators.employeeDataSet4("Experience")).split(" ");
        extractData.put("OverallExp", temp[0]);
        extractData.put("ECTech", DriverAction.getElementText(CommonLocators.employeeDataSet3("Engineering Council")));
        extractData.put("DCTech", DriverAction.getElementText(CommonLocators.employeeDataSet3("Delivery Council")));
        if (hashMap.containsKey("PrimarySkills") && hashMap.containsKey("SecondarySkills"))
            extractData.put("BothSkills", DriverAction.getElementText(CommonLocators.employeeDataSet3("Skills")));
        else if (hashMap.containsKey("PrimarySkills"))
            extractData.put("PrimarySkills", DriverAction.getElementText(CommonLocators.employeeDataSet3("Skills")));
        else if (hashMap.containsKey("SecondarySkills"))
            extractData.put("SecondarySkills", DriverAction.getElementText(CommonLocators.employeeDataSet3("Skills")));

        extractData.put("ECMentorName", DriverAction.getElementText(CommonLocators.employeeDataSet3("EC Mentor")));


        String wrongValue = "";
        for (Map.Entry<String, String> entry : extractData.entrySet()) {
            String key = entry.getKey();
            String value1 = entry.getValue();
            String value2 = "";
            if (key.equalsIgnoreCase("PrimarySkills")) {
                value2 = hashMap.get(key);
            } else if (key.equalsIgnoreCase("SecondarySkills")) {
                value2 = hashMap.get(key);
            } else if (key.equalsIgnoreCase("BothSkills")) {
                value2 = hashMap.get("PrimarySkills") + ", " + hashMap.get("SecondarySkills");
            } else {
                value2 = hashMap.get(key);
            }
            if (value1.equals(value2) || value1.equalsIgnoreCase("NA")) {
                passed = true;
            } else {
                passed = false;
                wrongValue = key;
                break;
            }

        }
        List<String> li = new ArrayList<>();
        if (passed) {
            li.add("True");
        } else li.add("False");
        li.add(wrongValue);
        return li;
    }

    public static boolean isEmployeeInFirstRow(List<WebElement> empList, String empName, String empCode) {
        for (int i = 0; i < empList.size(); i++) {
            String dataSource = empList.get(i).getAttribute("data-source");
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(dataSource);
                String name = jsonNode.get("name").asText();
                String employeeCode = jsonNode.get("EmployeeCode").asText();
                if (employeeCode.length() > 10)
                    employeeCode = employeeCode.substring(0, employeeCode.length() - 4);
                if (name.equalsIgnoreCase(empName) && empCode.equalsIgnoreCase(employeeCode))
                    return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
