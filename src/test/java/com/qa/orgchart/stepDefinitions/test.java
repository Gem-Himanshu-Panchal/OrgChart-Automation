package com.qa.orgchart.stepDefinitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class test {
    public static void main(String[] args) {
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
        System.out.println(hierarchy);


    }
}
