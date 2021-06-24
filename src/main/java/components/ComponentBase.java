package components;

import base.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.SlowLoadableComponent;

import java.time.Clock;

public abstract class ComponentBase  <T extends SlowLoadableComponent<T>> extends SlowLoadableComponent<T> {

    private static final Logger LOGGER = Logger.getLogger(BasePage.class);
    protected WebDriver driver;

    public ComponentBase(Clock clock, int timeOutInSeconds, WebDriver driver) {
        super(clock,timeOutInSeconds);
        this.driver = driver;
    }

    public WebElement find(By location) {
        LOGGER.info("Finding  element->" + location.toString());

        return driver.findElement(location);
    }

    public boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }


    public void click(WebElement element) {
        element.click();
    }
}
