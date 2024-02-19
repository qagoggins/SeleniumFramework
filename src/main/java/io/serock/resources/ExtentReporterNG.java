package io.serock.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir")+"/reports/index.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(path);
        spark.config().setReportName("Web Automation Results");
        spark.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Rahul Shetty");
        extent.createTest(path);
        return extent;
    }
}