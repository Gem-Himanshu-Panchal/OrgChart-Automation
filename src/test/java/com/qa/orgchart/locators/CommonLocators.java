package com.qa.orgchart.locators;

import org.openqa.selenium.By;


public class CommonLocators {
    public static By loader = By.xpath("//div[@class='loader-circle ng-star-inserted']");
    public static By loginButton = By.xpath("//button[contains(text(),' Login with Gemini Mail ')]");
    public static By companyLogo = By.xpath("//img[@class='company-logo']");
    public static By chartContainer = By.xpath("//div[@id='chart-container']");
    public static By searchField = By.xpath("//div[contains(text(),'Search Employee')]");

    public static By dropdownBox = By.xpath("//div[@class='ng-select-container ng-has-value']");
    public static By infoCard = By.xpath("//div[@class='nsm-body']");
    public static By employeeProfile = By.xpath("//div[@class='nsm-body']//img");
    public static By crossIcon = By.xpath("//span[@class='myclose']");

    public static By viewValue(String viewValue) {
        return By.xpath("//span[contains(text(), '" + viewValue + "') and contains(@class, 'ng-option-label ng-star-inserted')]");
    }

    public static By dataSource(String key1, String empName,String key2, String code) {
//        return By.xpath("//tr[@class='nodes']//div[contains(@data-source, '" + key + "\":\"" + value + "')]");
        return By.xpath("//tr[@class='nodes']//div[contains(@data-source, '"+key1+"\":\""+empName+"') and contains(@data-source, '"+key2+"\":\""+code+"')]");
    }

    public static By downArrowDataSource(String key1, String empName,String key2, String code) {
        return By.xpath("//div[contains(@data-source, '"+key1+"\":\""+empName+"') and contains(@data-source, '"+key2+"\":\""+code+"')]//i[contains(@class,'bottomEdge fa fa-chevron-circle-down')]");
    }
    public static By employeeDataSet1(String name){
        return By.xpath("//div[@class='nsm-body']//div[contains(text(),'"+name+"')]");
    }
    public static By employeeDataSet2(String name){
        return By.xpath("//div[@class='nsm-body']//span[contains(text(),'"+name+"')]");
    }
    public static By employeeDataSet3 (String value){
        return By.xpath("//span[contains(text(),'"+value+" : ')]//..//span[2]");
    }
    public static By employeeDataSet4 (String value){
        return By.xpath("//span[contains(text(),'"+value+": ')]//..//span[2]");
    }

    public static By employeeDiv(String key1, String empName,String key2, String code){
        return By.xpath("//tr[@class='nodes']//div[contains(@data-source, '"+key1+"\":\""+empName+"') and contains(@data-source,'"+key2+"\":\""+code+"')]//div");
    }
}
