package step_definitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.LoginPage;
import utilities.Driver;
import utilities.TempStorage;

public class Hooks {

    @Before
    public void setup() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        TempStorage.clear();
        //you can put anything that you want to run before each Scenario, but in our framework we won't really use it
    }


    @After
    public void tearDown(Scenario scenario) {         //everything is stored in the Scenario object after the scenario went through the tests

        if (scenario.isFailed()) {
            byte [] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);

            scenario.embed(screenshot, "image/png");
        }

//        Driver.quitDriver();
    }


}
