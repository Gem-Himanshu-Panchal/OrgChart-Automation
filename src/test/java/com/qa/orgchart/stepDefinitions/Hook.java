package com.qa.orgchart.stepDefinitions;

import com.gemini.generic.exception.GemException;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.Before;

public class Hook {
    @Before
    public void setup() throws GemException {
        DriverManager.setUpBrowser();
    }
}
