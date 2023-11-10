package com.PageObjects;

import com.Automation.BaseObjectOperations;
import com.Automation.DriverFactory;
import com.Helper.ActionsHelper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SingleProductPage extends BaseObjectOperations {

    public SingleProductPage(DriverFactory driverFactory) {
        super(driverFactory);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-BACK TO PRODUCTS\"]/android.widget.TextView")
    @iOSXCUITFindBy(accessibility = "test-BACK TO PRODUCTS")
    private WebElement backToProducts_Button;

    @AndroidFindBy(accessibility = "test-ADD TO CART")
    @iOSXCUITFindBy(accessibility = "test-ADD TO CART")
    private WebElement addToCart;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
    private WebElement productName_Text;

    @Step("Click on Back Button")
    public void BackToProductsPageButton() {
        ClickElement(backToProducts_Button);
    }

    public String GetProductPrice() {
        WebElement elem;
        elem = driver.findElement(AppiumBy.accessibilityId("test-Price"));
        return getAttributeValue(elem, "value");
    }

    @Step("Add product into the cart")
    public SingleProductPage AddProductToCart() {
        if (driverFactory.isAndroidPlatform())
            ActionsHelper.Scroll("ADD TO CART", (AndroidDriver) driver);

        clickElementWithoutWait(addToCart);
        return this;
    }

    @Step("Validate if correct product opened")
    public SingleProductPage VerifyProductName(String productName) {
        if (driverFactory.isAndroidPlatform()) {
            Assert.assertEquals(productName, getAttributeValue(productName_Text, "text"));
        } else {
            try {
                driver.findElement(AppiumBy.accessibilityId(productName));
            } catch (NoSuchElementException e) {
                Assert.fail(String.format("Expected to open %s product page", productName));
            }
        }

        return this;
    }

}
