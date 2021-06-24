package setup;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverHelper {
    public static DriverHelper get() {
        DriverHelper driverHelper = new DriverHelper();
        return driverHelper;
    }
    public WebDriver driver;
    private static final String BROWSER = System.getProperty("selenium.browser", "remote");
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public WebDriver getDriver(){
        if (driverThread.get() == null) {
            switch (BROWSER) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver",
                            "/Users/varshovhannisyan/Documents/ApiLesson/src/main/resources/chromedriver");
                    driver = new ChromeDriver();
                    driverThread.set(driver);
                    break;

                case "remote":
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName("chrome");
                    try{
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
                     }catch(MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driverThread.set(driver);
                    break;
//                case "firefox":
//                    System.setProperty("webdriver.gecko.driver",
//                            "/Users/varshovhannisyan/Documents/ApiLesson/src/main/resources/geckodriver");
//                    driver = new FirefoxDriver();
//                    driverThread.set(driver);
//                    break;
            }
        }
        return driverThread.get();
    }

    public void quitDriver(WebDriver driver) {
        driver.quit();
        driverThread.remove();
    }
}