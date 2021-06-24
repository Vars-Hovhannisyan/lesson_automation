package pageobjects;

import base.BasePage;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import wait.WaitHelper;

public class ProjectPage extends BasePage {
    @FindBy(css = ".intro-title .project-name")
    private WebElement projectName;
    private final JsonObject myProject;

    public ProjectPage(JsonObject projectJson) {
        myProject = projectJson;
        open(getURL());
        PageFactory.initElements(driver, this);
    }

    public String getProjectName() {
        WaitHelper.getInstance().waitForElementToDisplayed(projectName);
        return projectName.getText();
    }

    @Override
    public String getURL() {
        return BASE_URL + "/project/" + myProject.get("slug").getAsString();
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        WaitHelper.getInstance().waitForElementToDisplayed(projectName);
    }
}