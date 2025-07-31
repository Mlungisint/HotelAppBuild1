package Assessment;


import Utilities.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.Elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import Utilities.CommonUtil;
import testData.DataSource;

public class build1Test {
    WebDriver driver;
    public Properties AutoPropertiesFile = new Properties();
    public InputStream input = null;
    public String[][] readData;
    public Utilities.WebdriverUtil driverUtil;
    public Utilities.Utils Utils;
    protected CommonUtil commonUtil;
    static ExtentTest test;
    static ExtentReports report;
    protected ExtentReports extent;
    //    protected ExtentTest test;
    Elements elements = new Elements();

    JavascriptExecutor js = (JavascriptExecutor) driver;

    @BeforeTest
    public void setUp() throws IOException {
        input = new FileInputStream("src/test/resources/selenium.properties");
        AutoPropertiesFile.load(input);
        String baseUrl = AutoPropertiesFile.getProperty("baseUrl");
        extent = ExtentManager.getInstance();

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());

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
        Utils util = new Utils(driver);

    }

    @Test
    public void logIn() throws Exception {

        test = extent.createTest("Login with valid credentials");
        String username = AutoPropertiesFile.getProperty("username");
        String password = AutoPropertiesFile.getProperty("password");
        commonUtil.waitForElement(elements.username);


        System.out.println(elements.username);
        commonUtil.typeOnElement(elements.username, username);
        commonUtil.typeOnElement(elements.password, "BBP306");
        commonUtil.clickOnElement(elements.signBtn);
        String searchPage = commonUtil.GetCurrentUrl();

        System.out.println(searchPage);

        Assert.assertEquals("https://adactinhotelapp.com/SearchHotel.php", searchPage);
        if (searchPage == "https://adactinhotelapp.com/SearchHotel.php") {
            test.pass("Test Passed successfully");
        } else {
            test.fail("Test failed");
        }
    }

    @Test(dataProvider = "loginCSVData", dataProviderClass = DataSource.class)
    public void seachHotel(String testCase, String Username, String Password, String location, String hotel, String roomType, String numRooms) throws Exception {
        test = extent.createTest("Search hotel screen");
        commonUtil.waitForElement(elements.searchBtn);
        String date1 = commonUtil.getFutureDate(20);
        String date2 = commonUtil.getFutureDate(30);

        System.out.println(testCase + " | " + Username + " | " + Password + " | " + location + " | " + hotel + " | " + roomType + " | " + numRooms);


        commonUtil.Dropdown_Select(elements.locationDrp, elements.locationDrp, location);
        commonUtil.Dropdown_Select(elements.hotelsDrp, elements.hotelsDrp, hotel);
        commonUtil.Dropdown_Select(elements.roomTypeDrp, elements.roomTypeDrp, roomType);
        commonUtil.Dropdown_Select(elements.numberOfRooms, elements.numberOfRooms, numRooms);

        //I will rework this to get date from separate class
        commonUtil.clear(elements.checkInDate);
        commonUtil.typeOnElement(elements.checkInDate, date1);
        commonUtil.clear(elements.checkOutDate);
        commonUtil.typeOnElement(elements.checkOutDate, date2);

        commonUtil.Dropdown_Select(elements.adultPerRoomDrp, elements.adultPerRoomDrp, "2");
        commonUtil.Dropdown_Select(elements.childrenPeRoomDrp, elements.childrenPeRoomDrp, "1");
        commonUtil.clickOnElement(elements.searchBtn);
        if (commonUtil.isPresent(elements.locationDrp) == true) {
            test.pass("Test Passed successfully");
        } else {
            test.fail("Test failed");
        }
    }

    @Test(dataProvider = "loginCSVData", dataProviderClass = DataSource.class)
    public void selectHotel(String testCase, String Username, String Password, String location, String hotel, String roomType, String numRooms) throws Exception {
        test = extent.createTest("Booking data validation");
        commonUtil.waitForElement(elements.continueBtn);
//        Need to fix the method for table iteration
        commonUtil.selectCheckboxForMatchingRow(driver, elements.table2, hotel);

//        click rado button (temp solution)
        commonUtil.clickOnElement(elements.radioBtn);
        commonUtil.clickOnElement(elements.continueBtn);


//        Assert results
        String reservedNumOfRms = commonUtil.getText(elements.reservedNumOfRooms);
        String hotelName = commonUtil.getText(elements.reservedHotel);

        System.out.println(reservedNumOfRms);
        System.out.println(hotelName);


        Assert.assertEquals(reservedNumOfRms, numRooms);
        Assert.assertEquals(hotel, hotelName);
        if (reservedNumOfRms == numRooms) {test.pass("Test Passed successfully");
        } else {test.fail("Test failed");}

        if (hotel == hotelName) {test.pass("Test Passed successfully");
        } else {test.fail("Test failed");}

    }

    @Test
    public void CompleteBooking(){


    }


    @AfterTest
    public void closeBrowser() {
        // Close browser after test
//        driver.quit();
        extent.flush();

    }
}

