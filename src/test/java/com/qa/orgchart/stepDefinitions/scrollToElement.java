package com.qa.orgchart.stepDefinitions;

import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.orgchart.locators.CommonLocators;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;

public class scrollToElement {
    public static void scrollToElement(String name, String code){
        WebDriver driver= DriverManager.getWebDriver();
        WebElement element = DriverAction.getElement(CommonLocators.employeeDiv(name, code));
        long windowWidth = (long) ((JavascriptExecutor) driver).executeScript("return window.innerWidth;");
        long windowHeight = (long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight;");

        long elementX = element.getLocation().getX();
        long elementY = element.getLocation().getY();

        // Get the dimensions of the element.
        long elementWidth = element.getSize().getWidth();
        long elementHeight = element.getSize().getHeight();

        // Calculate the center coordinates of the element relative to the top-left corner of the page.
        long centerX = elementX + elementWidth / 2;
        long centerY = elementY + elementHeight / 2;

        // Calculate the scroll positions needed to center the element in the viewport.
        long scrollX = centerX - windowWidth / 2;
        long scrollY = centerY - windowHeight / 2;

      
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(" + scrollX + ", " + scrollY + ");");
    }
}
