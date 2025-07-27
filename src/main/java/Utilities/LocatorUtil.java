package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LocatorUtil {

    private static final Logger LOG = LogManager.getLogger(LocatorUtil.class);

    private LocatorUtil() {
        // Utility class – no public constructor
    }

    public static By getLocator(String locator) throws Exception {
        String[] parts = locator.split("\\|\\|");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid locator format: " + locator + ". Expected format: TYPE||value");
        }

        String locatorType = parts[0].trim().toLowerCase();
        String locatorValue = parts[1].trim();

        switch (locatorType) {
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "classname":
            case "class":
                return By.className(locatorValue);
            case "tagname":
            case "tag":
                return By.tagName(locatorValue);
            case "linktext":
            case "link":
                return By.linkText(locatorValue);
            case "partiallinktext":
                return By.partialLinkText(locatorValue);
            case "cssselector":
            case "css":
                return By.cssSelector(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            default:
                String msg = "Unknown locator type: '" + locatorType + "'";
                LOG.error(msg);
                throw new Exception(msg);
        }
    }

    public static void switchToIframe(WebDriver driver, String iframeLocator) throws Exception {
        String[] parts = iframeLocator.split("\\|\\|");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid iframe locator format: " + iframeLocator);
        }

        String iframeType = parts[0].trim().toLowerCase();
        String iframeValue = parts[1].trim();

        if (iframeType.equals("index")) {
            driver.switchTo().frame(Integer.parseInt(iframeValue));
        } else {
            driver.switchTo().frame(driver.findElement(getLocator(iframeLocator)));
        }
    }
}
