package extent.reports;

import java.awt.Desktop;
import java.io.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;

public class T2_ExtentReports extends T1_Drivers{
    
    private ExtentReports report;
    private ExtentSparkReporter sparkReporter;
    String filePath = "D:\\JKS\\Education\\Personal\\Online courses\\Naresh\\"+
    "Java Interview Questions\\Java Selenium\\extent_reports\\reports\\loginReport.html";
    File file = new File(filePath);
    String screenShotFilesPath = "D:\\JKS\\Education\\Personal\\Online courses\\Naresh"+
    "\\Java Interview Questions\\Java Selenium\\extent_reports\\ScreenShots\\";

    

    public String getScreenShot(String screenShotName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();

        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenShotFilesPath + screenShotName + ".png");

        try {
            FileUtils.copyFile(sourceFile, destFile);
        } catch (Exception e) {
            System.out.println("Error while Saving Screenshot" + e.getMessage());
        }

        System.out.println("Screen Shot saved Successfully");
        return destFile.getAbsolutePath();
    }
    
    
    public void extentReport(String reportName, String extentTitle) {
        report = new ExtentReports();
        sparkReporter = new ExtentSparkReporter(file);
        ExtentSparkReporterConfig config = sparkReporter.config();
        config.setReportName(reportName);
        config.setDocumentTitle(extentTitle);

        report.attachReporter(sparkReporter);
        //return report;
    }

    public void testPass(String testName, String testInfo, String categoryNames,
     String deviceNames, String statusInfo) {
        report
        .createTest(testName, testInfo)
        .assignCategory(categoryNames)
        .assignDevice(deviceNames)
        .pass(statusInfo);
    }

    public void testFail(String testName, String testInfo, String categoryNames, 
    String deviceNames, String statusInfo, String screenShotName) {
        
        String path = getScreenShot(screenShotName);
        Throwable t = new Exception(statusInfo);
        report
        .createTest(testName, testInfo)
        .assignCategory(categoryNames)
        .assignDevice(deviceNames)
        .fail(t, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
    }

    public void result(boolean status, String testName, String testInfo,String categoryNames, 
    String deviceNames, String passInfo, String failInfo, String screenShotName) {
        try {
            if (status) {
                testPass(testName, testInfo, categoryNames, deviceNames, passInfo);
            } else {
                testFail(testName, testInfo, categoryNames, deviceNames, failInfo, screenShotName);
            }
        } catch (Exception e) {
            //testFail(testName, testInfo, categoryNames, deviceNames, failInfo);
            e.printStackTrace();
        }

        
    }

    public void showReport() throws Exception{
        report.flush();
        Desktop.getDesktop().browse(new File(filePath).toURI());
    }
}
