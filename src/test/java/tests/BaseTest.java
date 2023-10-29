package tests;

import dto.UserDtoLombok;
import manager.ApplicationManager;
import manager.TestNGListener;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.RandomUtils;

import java.lang.reflect.Method;

@Listeners(TestNGListener.class)

public class BaseTest {

    Logger logger = LoggerFactory.getLogger(BaseTest.class);

    boolean flagIsAlertPresent = false;
    boolean flagIsUserLogin = false;
    static ApplicationManager app = new ApplicationManager();
    RandomUtils randomUtils = new RandomUtils();

    UserDtoLombok userDtoLombok = UserDtoLombok.builder()
            .email("testqa20@gmail.com")
            .password("123456Aa$")
            .build();

    @BeforeSuite(alwaysRun = true)
    public void setup() {
        app.init ();
    }

    @AfterSuite(alwaysRun = true)
    public void stop() {
        app.tearDown();
    }

    public void logoutIfLogin() {
        if(app.getUserHelper().btnLogoutExist()) {
            app.getUserHelper().logout();
        }
    }


    @BeforeMethod(alwaysRun = true)
    public void loggerBe4Method(Method method){
        logger.info("start method: " + method.getName());
    }

    @BeforeMethod(alwaysRun = true)
    public void loggerAfterMethod(Method method){
        logger.info("stop method: " + method.getName());
    }

    public void preconditionForLoginAndRegTests() throws InterruptedException {
        if(flagIsAlertPresent) {
            flagIsAlertPresent = false;
            app.getUserHelper().clickOkPopUpNotLogin();
        }
        if (flagIsUserLogin) {
            flagIsUserLogin = false;
            app.getUserHelper().logout2();
            }
           // logoutIfLogin();
        }
    }

