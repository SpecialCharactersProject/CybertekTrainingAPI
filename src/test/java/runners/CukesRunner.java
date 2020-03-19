package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)           //This line forces CukesRunner class to run with Cucumber
@CucumberOptions(


        plugin = {"html:target/cucumber-reports", "json:target/cucumber-reports.json"},

        features = "src/test/resources/parallel",    //you DO NOT want to change this; it will look into features directory and it will be recognized by tags
        glue = "parallel",                    //you DO NOT want to change this; it will look into features directory and it will be recognized by tags
        //features and glue are MANDATORY to run the program

        dryRun = false,                 //when it is true, it does not open the browser, but checks the steps
                                        //when it is false, it does open the browser and checks the steps

       tags = "@studentAPI, @teacherAPI"            //by doing that, you are saying run this group of cases
                                        //you can pass more that 1 tag:  tags = "@smokeTest", "@Regression"
)

public class CukesRunner {
}
