package com.qa.orgchart.stepDefinitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.qa.orgchart.stepDefinitions.getEmployeeIndex.getIndex;

public class test {
    public static void main(String[] args) {
        List<HashMap<String, String>> hashMapList = jsonToHash.getHashList();
        List<HashMap<String, String>> hashMapListFull = jsonToHash.getHashList2();

        HashMap<String, String> employee = hashMapList.get(0);

        String name = "";
        String code = "";
        String dcTech = employee.get("DCTech");
        String secondaryDC = employee.get("SecondaryDCs");
        List<String> hierarchy = new ArrayList<>();
        int flag;
        if (dcTech.contains("Pimco")) {
            hierarchy.add(employee.get("EmployeeName"));
            hierarchy.add(employee.get("EmployeeCode"));
            name = employee.get("ReportingManager");
            code = employee.get("ManagerCode");
            Outerloop:
            while (!name.equalsIgnoreCase("Vishal Malik")) {
                for (int i = 0; i < hashMapList.size(); i++) {
                    HashMap<String, String> hashMap = hashMapList.get(i);

                    if (hashMap.get("EmployeeName").equalsIgnoreCase(name) && hashMap.get("EmployeeCode").equalsIgnoreCase(code) && hashMap.get("DCTech").equalsIgnoreCase(dcTech)) {
                        flag = getIndex(name, code);
                        HashMap<String, String> nextHash2 = hashMapListFull.get(flag);
                        if (!nextHash2.get("DCTech").equalsIgnoreCase(dcTech)){
                            break Outerloop;
                        }
                        hierarchy.add(hashMap.get("EmployeeName"));
                        hierarchy.add(hashMap.get("EmployeeCode"));
                        name = hashMap.get("ReportingManager");
                        code = hashMap.get("ManagerCode");

//                        if(nextHash2.containsKey("SecondaryDCs") && !nextHash2.get("SecondaryDCs").equalsIgnoreCase(dcTech)){
//                            break Outerloop;
//                        }


                        if (name == null)
                            break Outerloop;
                    }
                }
            }

if(dcTech.equalsIgnoreCase("Pimco RiskOps") && !hierarchy.contains("Munit Goyal")){
    hierarchy.add("Munit Goyal");
    hierarchy.add("GSI G 036");

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
        }


        System.out.println(hierarchy);

    }
}