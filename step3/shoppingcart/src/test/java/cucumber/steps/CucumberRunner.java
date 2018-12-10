package cucumber.steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"build/resources/test/cucumber/steps/"}
    , glue = {"cucumber.steps"}
)
public class CucumberRunner {

    @BeforeClass
    public static void setUp(){
        StepDef.setup();
    }

    @AfterClass
    public static void tearDown(){
        StepDef.tearDown();
    }
}