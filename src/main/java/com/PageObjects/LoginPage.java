package com.PageObjects;

import com.Automation.BaseObjectOperations;
import com.Automation.DriverFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseObjectOperations {
    @iOSXCUITFindBy(accessibility = "test-Username")
    @AndroidFindBy(accessibility = "test-Username")
    private WebElement Username_textfield;
    @iOSXCUITFindBy(accessibility = "test-Password")
    @AndroidFindBy(accessibility = "test-Password")
    private WebElement password_textfield;

    @iOSXCUITFindBy(accessibility = "test-LOGIN")
    @AndroidFindBy(xpath ="//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]/android.widget.TextView")
    private WebElement Loginbutton;

    @iOSXCUITFindBy(accessibility = "test-Error message")
    @AndroidFindBy(xpath ="//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    private WebElement errormessage;



    public LoginPage(DriverFactory driverFactory) {
        super(driverFactory);
    }


    @Step("Login into the app")
    public void LoginAction(String username,String password){
    SendKeys(Username_textfield,username);
    SendKeys(password_textfield,password);
    ClickElement(Loginbutton);
    }

    public boolean iserrormessaagedisplayed(){
        return isDisplayed(errormessage);
    }

    public String getErrorMessageText(){
        return gettext(errormessage);
    }

}
