package cucumber.options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/featureFiles", plugin = "json:target/jsonReports/cucumber-report.json", glue = {
        "stepDefinitions"})
public class TestRunner {
}
