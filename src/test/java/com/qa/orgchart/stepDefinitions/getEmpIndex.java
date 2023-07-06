package com.qa.orgchart.stepDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class getEmpIndex {
    public static void main(String[] args) {

            List<HashMap<String, String>> hashMapList = jsonToHash.getHashList();
            int index = 0;
        String name = "Abhinav Aggarwal";
        String code = "GSI C 1268";
            for(int i = 0; i< Objects.requireNonNull(hashMapList).size(); i++){
                if(hashMapList.get(i).get("EmployeeName").equalsIgnoreCase(name) && hashMapList.get(i).get("EmployeeCode").equalsIgnoreCase(code)){
                    index = i;break;
                }
            }
        System.out.println(index);

    }
}
