package Core;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;


//@RunWith(Cucumber.class)
    @CucumberOptions(
            strict = true,
            monochrome = true,
            features = "./src/test/Java/Script",
            glue = "Business",
            plugin = {
                    "html:target/cucumber-html-report",
                    "json:target/cucumber.json",
                    "pretty:target/cucumber-pretty.txt",
                    "usage:target/cucumber-usage.json",
                    "junit:target/cucumber-results.xml",
                    "progress:target/cucumber-progress.txt"
            }
            , tags = {},
            dryRun= false,
            snippets= SnippetType.CAMELCASE)
    public class ExcelRun extends AbstractTestNGCucumberTests {

    }

