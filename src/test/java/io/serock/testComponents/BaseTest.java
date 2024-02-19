package io.serock.testComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.serock.pageobjects.LandingPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initializeDriver() {
        try {
            Properties prop = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/main/java/io/serock/resources/GlobalData.properties");
            prop.load(fileInputStream);
            String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
            if(browserName.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("chromeheadless")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                driver = new ChromeDriver(options);
                driver.manage().window().setSize(new Dimension(1440, 900));
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get("https://google.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driver;
    }
    public String getScreenshot(String testCaseName, WebDriver driver) {
        String filepath = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File file = new File(filepath);
            FileUtils.copyFile(source, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filepath;
    }
    public String getScreenshotAsBase64() {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
    }
    public List<HashMap<String, String>> getJsonData(String filepath) throws IOException {
            String JSONcontent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            List<HashMap<String, String>> data = mapper.readValue(JSONcontent, new TypeReference<List<HashMap<String, String>>>() {
            });
            return data;
    }
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApp() {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.open();
        return landingPage;
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
