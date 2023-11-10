package com.Automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseObjectOperations {
    private static final int NO_WAITING_TIMEOUT_INT=0;
    private static final int DEFAULT_WAITING_TIMEOUT_INT=60;

    private static final int LONG_WAITING_TIMEOUT_INT=120;
    private static final int SHORT_WAITING_TIMEOUT_INT=30;

    public AppiumDriver driver;
    private WebDriverWait defaultwait;
    private WebDriverWait longWait;
    private WebDriverWait ShortWait;


    public static final Duration NO_WAITING_TIMEOUT= Duration.ofSeconds(NO_WAITING_TIMEOUT_INT);
    public  DriverFactory driverFactory;

    public BaseObjectOperations(DriverFactory driverFactory){
        this.driver=driverFactory.getDriver();
        this.driverFactory=driverFactory;
        PageFactory.initElements(new AppiumFieldDecorator(driver,NO_WAITING_TIMEOUT),this);
        defaultwait=new WebDriverWait(driver,Duration.ofSeconds(DEFAULT_WAITING_TIMEOUT_INT));
        longWait=new WebDriverWait(driver,Duration.ofSeconds(LONG_WAITING_TIMEOUT_INT));
        ShortWait=new WebDriverWait(driver,Duration.ofSeconds(SHORT_WAITING_TIMEOUT_INT));

    }
    public void ClickElement(WebElement element){
        waitforElement(element,defaultwait);
        element.click();
    }

    private void waitforElement(WebElement element, WebDriverWait wait) {
       wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
    }
    public WebDriverWait getDefaultWait() {
        return defaultwait;
    }

    public String gettext(WebElement element){
        waitforElement(element,defaultwait);
        return element.getText();
    }

    public boolean isDisplayed(WebElement element){
        waitforElement(element,defaultwait);
        return element.isDisplayed();
    }

    public void SendKeys(WebElement element,String text){
        waitforElement(element,defaultwait);
        element.clear();
        element.sendKeys(text);

    }
    public void waitForElement(WebElement element,WebDriverWait wait){
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
    }

    public String getAttributeValue(WebElement element, String attribute){
        //waitForElement(element,defaultWait);
        return element.getAttribute(attribute);
    }

    public void clickElementWithoutWait(WebElement element){
        element.click();
    }
}
