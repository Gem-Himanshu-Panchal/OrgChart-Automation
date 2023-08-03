package com.qa.orgchart.stepDefinitions;

import java.util.HashMap;
import java.util.List;

public class getEmployeeIndex {
    public static int getIndex(String ecMentor, String code) {
        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList2();
//        String name = "Hrithik Arora";
//        String code = "GSI G 1058";
        int index = 0;
        for(int i=0;i<hashMapList.size();i++){
            if(hashMapList.get(i).get("EmployeeName").equalsIgnoreCase(ecMentor) && hashMapList.get(i).get("EmployeeCode").equalsIgnoreCase(code)){
                index = i;break;
            }
        }

       return index;
    }
    public static int getIndex2(String ecMentor, String code) {
        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList2();

        int index = 0;
        for(int i=0;i<hashMapList.size();i++){
            if(hashMapList.get(i).get("EmployeeName").equalsIgnoreCase(ecMentor) && hashMapList.get(i).get("EmployeeCode").equalsIgnoreCase(code)){
                index = i;break;
            }
        }

        return index;
    }
}
