package com.qa.orgchart.stepDefinitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.qa.orgchart.stepDefinitions.getEmployeeIndex.getIndex;

public class rehg {
    public static void main(String[] args) {

        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList2();
        System.out.println(hashMapList.size());
        for (HashMap<String, String> hashMap : hashMapList) {
            if (hashMap.get("DCTech").contains("Pimco Analytics Support")
                    || (hashMap.containsKey("SecondaryDCs") &&
                    hashMap.get("SecondaryDCs") != null &&
                    hashMap.get("SecondaryDCs").contains("Pimco Analytics Support"))) {

                System.out.println(hashMap.get("EmployeeName"));
            }
        }
    }
}
