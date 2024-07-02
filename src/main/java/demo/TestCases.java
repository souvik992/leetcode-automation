package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://leetcode.com/");
        if (driver.getCurrentUrl().contains("leetcode")) {
            System.out.println("Url contains leetcode");
        } else {
            System.out.println("failed!");
        }
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() {
        try {
            System.out.println("Start Test case: testCase02");
            driver.get("https://leetcode.com/");

            WebElement questionsLink = driver.findElement(By.cssSelector("a[href='/problemset/']"));
            questionsLink.click();
            Thread.sleep(5000);
            if(driver.getCurrentUrl().contains("problemset")){
                System.out.println("url contains \"problemset");
            }else{
                System.out.println("Url doesn't contain \"problemset");
            }

            List<WebElement> questions = driver.findElements(By.xpath("//div[@class='truncate']/a"));

            for (int i = 1; i < 6; i++) {

                String expectedUrlTitle = questions.get(i).getText();
                questions.get(i).click();
                Thread.sleep(3000);
                String currentUrlTitle = driver.getTitle().trim().toLowerCase();
                if (currentUrlTitle.contains(expectedUrlTitle.replaceFirst("^\\d+\\.\\s*", "").trim().toLowerCase())) {
                    System.out.println("Url contains the same title!");
                }
                System.out.println(expectedUrlTitle);
                driver.navigate().back();
                Thread.sleep(5000);
                questions = driver.findElements(By.xpath("//div[@class='truncate']/a"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testCase03() {
        try {
            System.out.println("Start Test case: testCase03");
            driver.get("https://leetcode.com/");

            WebElement questionsLink = driver.findElement(By.cssSelector("a[href='/problemset/']"));
            questionsLink.click();
            Thread.sleep(5000);

            WebElement twoSum = driver.findElement(By.xpath("(//div[@class='truncate'])[2]/a"));
            twoSum.click();
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("two-sum")) {
                System.out.println("Url contains two-sum");
            }
            System.out.println("End Test case: testCase03");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testCase04() {
        try {
            System.out.println("Start Test case: testCase04");
            driver.get("https://leetcode.com/");

            WebElement questionsLink = driver.findElement(By.cssSelector("a[href='/problemset/']"));
            questionsLink.click();
            Thread.sleep(5000);

            WebElement twoSum = driver.findElement(By.xpath("(//div[@class='truncate'])[2]/a"));
            twoSum.click();

            WebElement submissionsTab = driver.findElement(By.xpath("(//div[@class='flexlayout__tab_button_content'])[4]"));
            submissionsTab.click();
            WebElement loginRequired = driver.findElement(By.xpath("//a[contains(text(),'Register or Sign In')]"));
            if(loginRequired.getText().contains("Register or Sign In")){
                System.out.println("displays \"Register or Sign In");
            }else{
                System.out.println("Not diplaying \"Register or Sign In");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
