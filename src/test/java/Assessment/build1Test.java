package Assessment;



import Utilities.Utils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.Elements;
import Utilities.CommonUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import Utilities.CommonUtil;
import pageObjects.Elements;

public class build1Test {
    WebDriver driver;
    public Properties AutoPropertiesFile = new Properties();
    public InputStream input = null;
    public String[][] readData;
    public Utilities.WebdriverUtil driverUtil;
    public Utilities.Utils Utils;
    protected CommonUtil commonUtil;
//    public Utilities.CommonUtil CommonUtil;
    static ExtentTest test;
    static ExtentReports report;
    Elements elements = new Elements();

    JavascriptExecutor js = (JavascriptExecutor) driver;

    @BeforeTest
    public void setUp() throws IOException {
        input = new FileInputStream("src/test/resources/selenium.properties");
        AutoPropertiesFile.load(input);
        String baseUrl = AutoPropertiesFile.getProperty("baseUrl");

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
        report = new ExtentReports(System.getProperty("user.dir") + "\\Reports\\TestCase1" + timeStamp + ".html", false);

//        Browser setup
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
//        WebDriverManager.chromedriver().setup(); //handles driver binaries to test it later
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println(driver.getTitle());
        System.out.println("Chrome launched successfully:" + " " + driver.getCurrentUrl());
//        driver.navigate().to("https://adactinhotelapp.com/");

        commonUtil = new CommonUtil(driver); //initialsed commonUtil
        Utils util=new Utils(driver);

    }

    @Test
    public void logIn() throws Exception {
        String username = AutoPropertiesFile.getProperty("username");
        String password=AutoPropertiesFile.getProperty("password");
        commonUtil.waitForElement(elements.username);


        System.out.println(elements.username);
        commonUtil.typeOnElement(elements.username, username);
        commonUtil.typeOnElement(elements.password,"BBP306");
        commonUtil.clickOnElement(elements.signBtn);
        String searchPage=commonUtil.GetCurrentUrl();

        Assert.assertEquals("https://adactinhotelapp.com/SearchHotel.php",searchPage);
    }

    @Test
    public void seachHotel() throws Exception {
        commonUtil.waitForElement(elements.searchBtn);
        String date1=commonUtil.getFutureDate(20);
        String date2=commonUtil.getFutureDate(30);
        System.out.println(date2);
        System.out.println("test 2");




        commonUtil.Dropdown_Select(elements.locationDrp, elements.locationDrp, "London");
        commonUtil.Dropdown_Select(elements.hotelsDrp, elements.hotelsDrp, "Hotel Creek");
        commonUtil.Dropdown_Select(elements.roomTypeDrp, elements.roomTypeDrp, "Double");
        commonUtil.Dropdown_Select(elements.numberOfRooms, elements.numberOfRooms, "2");

        //I will rework this to get date from separate class
        commonUtil.typeOnElement(elements.checkInDate,"28/07/2025");
        commonUtil.typeOnElement(elements.checkOutDate,"29/07/2025");

        commonUtil.Dropdown_Select(elements.adultPerRoomDrp,elements.adultPerRoomDrp,"2");
        commonUtil.Dropdown_Select(elements.childrenPeRoomDrp,elements.childrenPeRoomDrp,"1");
        commonUtil.clickOnElement(elements.searchBtn);
    }

    @Test
    public void selectHotel() throws Exception {

        commonUtil.waitForElement(elements.continueBtn);
        commonUtil.selectCheckboxForMatchingRow(driver,elements.table2,"Hotel Creek");
        commonUtil.clickOnElement(elements.continueBtn);
    }


//    @AfterTest
//    public void closeBrowser() {
//        // Close browser after test
//        driver.quit();
//    }


}
