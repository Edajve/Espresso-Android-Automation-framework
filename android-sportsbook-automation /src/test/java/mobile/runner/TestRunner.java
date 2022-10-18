package mobile.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources"
        , glue = {"mobile/steps"}
        , monochrome = true
        , plugin = {"pretty", "html:target/cucumber-reports/mobile"}
        , tags = "@Regression"
        , dryRun = false
)
public class TestRunner {
}