package TestPackage;

import com.Automation.BaseTest;
import com.Helper.MessageConstants;
import com.PageObjects.LoginPage;
import com.PageObjects.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTestCases extends BaseTest {
    private LoginPage page;
    private ProductsPage productsPage;

    @BeforeClass(alwaysRun = true)
     public void setup(){
        page=new LoginPage(driverFactory);
        productsPage=new ProductsPage(driverFactory);
    }

//    @Test
//    public void loginTest_Positive(){
//        page.LoginAction("standard_user","secret_sauce");
//        Assert.assertTrue(productsPage.ProductsPageOpened(),"productPageOpened");
//
//    }

    @Test
    public void loginTest_Incorrectcredentials(){
        page.LoginAction("standard_user","secret_sauce1");
        Assert.assertTrue(page.iserrormessaagedisplayed(),"ErroMessageisdisplayed");
        Assert.assertEquals(page.getErrorMessageText(), MessageConstants.InvalidUsernamepasswordmessage);

    }

    @Test
    public void loginTest_lockedoutuser(){
        page.LoginAction("locked_out_user","secret_sauce");
        Assert.assertTrue(page.iserrormessaagedisplayed(),"Lockedoutuser");
        Assert.assertEquals(page.getErrorMessageText(), MessageConstants.LockedUser);

    }
    @AfterClass
    public void teardown() throws InterruptedException {
    Thread.sleep(3000);
        super.tearDown();
    }
}
