package experiments;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestReports {
    public static void main(String[] args) {

        // Initialize the HtmlReporter
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("reports/htmlreporter.html");


        // Initialize ExtentReports and attach the reporter
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        ExtentTest feature = extent.createTest(Feature.class, "Refund Item");
        ExtentTest scenario = feature.createNode(Scenario.class, "Jeff retuns a faulty microwave.");

        scenario.createNode(Given.class, "Given").pass("");
        scenario.createNode(And.class, "And").fail("");
        scenario.createNode(When.class, "When").fail("");
        scenario.createNode(Then.class, "Then").pass("");

        extent.flush();
    }
}
