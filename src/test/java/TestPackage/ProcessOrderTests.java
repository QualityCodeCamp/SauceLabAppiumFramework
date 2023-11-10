package TestPackage;

import com.Automation.BaseTest;
import com.Helper.CalculateTotal;
import com.Helper.HelperClass;
import com.Helper.Product;
import com.PageObjects.*;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProcessOrderTests extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private SingleProductPage singleProductPage;
    private CartPage cartPage;
    private TopNavigationBar topNavigationBar;
    private CheckoutPage checkoutPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutComplete checkoutComplete;

    @BeforeClass(alwaysRun = true)
    public void setup() {

        loginPage = new LoginPage(driverFactory);
        productsPage = new ProductsPage(driverFactory);
        singleProductPage = new SingleProductPage(driverFactory);
        cartPage = new CartPage(driverFactory);
        topNavigationBar = new TopNavigationBar(driverFactory);
        checkoutPage = new CheckoutPage(driverFactory);
        checkoutOverviewPage = new CheckoutOverviewPage(driverFactory);
        checkoutPage = new CheckoutPage(driverFactory);
        checkoutComplete = new CheckoutComplete(driverFactory);

    }
    @BeforeMethod
    public void activeapp()
    {
        System.out.println("in Before method");
        driverFactory.activeapp();

    }
    // Performs login
    // Adds 2 products by opening them on individual product page
    // Creates an instance of CalculateTotal
        // takes in a list of products
        // Calculates the total incl tax that is used as expected value later
    // Opens cart
    // Clicks on checkout
    // Fills checkout page
    // Validates total incl tax on overview page
    // Places order

    @Test
    public void ProcessOrderTests_Positive() throws Exception {

        var productData = List.of(
                new Product("Sauce Labs Backpack", 29.99),
                new Product("Sauce Labs Bike Light", 9.99)
        );

        CalculateTotal calculateTotal = new CalculateTotal(productData);

        loginPage.LoginAction("standard_user", "secret_sauce");

        for (Product prod : productData) {
            productsPage.OpenProductByName(prod.getName());

            singleProductPage
                    .VerifyProductName(prod.getName())
                    .AddProductToCart()
                    .BackToProductsPageButton();
        }

        topNavigationBar.OpenCart();

        cartPage.Checkout();

        checkoutPage
                .FillInformation("Name", "LName", "12345")
                .clickContinue();

        var actualTotal = HelperClass.ConvertCurrencyPriceToInt(checkoutOverviewPage.GetTotal());
        var expectedTotal = calculateTotal.CalculateTotalTaxIncl();
        Assert.assertEquals(actualTotal, expectedTotal);

        checkoutOverviewPage.ClickFinishButton();
        Assert.assertEquals("THANK YOU FOR YOU ORDER", checkoutComplete.GetOrderConfirmation());

    }

    //@Test
//    public void RemoveProductFromCart() throws Exception {
//
//        var productData = List.of(
//                new Product("Sauce Labs Backpack", 29.99),
//                new Product("Sauce Labs Bike Light", 9.99)
//        );
//
//        loginPage
//                .LoginAction("standard_user", "secret_sauce");
//
//        productsPage
//                .OpenProductByName(productData.get(0).getName());
//
//        singleProductPage
//                .VerifyProductName(productData.get(0).getName())
//                .AddProductToCart();
//
//        topNavigationBar
//                .OpenCart();
//
//        cartPage
//                .SwipeLeftOnProduct(productData.get(0).getName())
//                .ClickProductRemove();
//
//        Thread.sleep(5000);
    //}

    @AfterMethod
    public void aftermethod(){
        System.out.println("in after method");
        driverFactory.terminateapp();
    }
}
