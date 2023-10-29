package tests;

import data.DataProviderLogin;
import dto.UserDTO;
import dto.UserDTOWith;
import dto.UserDtoLombok;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends BaseTest{

    @BeforeClass(alwaysRun = true)
    public void preconditionsBeforeClass(){
        if(app.ispageUrlHome()){
            app.getUserHelper().openLoginPage();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void preconditionsBeforeMethod() throws InterruptedException {
Thread.sleep(1000);
            preconditionForLoginAndRegTests();

    }

//    @AfterTest
//    public void postconditions(){
//
//        logoutIfLogin();
//
//    }

//    @AfterMethod(alwaysRun = true)
//    public void postconditionsLogin() {
//        app.getUserHelper().clickOkPopUpSuccessLogin();
////        try {
////            Thread.sleep(2000);
////        } catch (InterruptedException e) {
////            throw new RuntimeException(e);
////        }
//    }

    @Test
    public void positiveLoginUserDTO() {
        UserDTO userDTO = new UserDTO("qwerty@qwer.ty", "Qwerty!1");
        app.getUserHelper().login(userDTO);
        flagIsUserLogin = true;
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());

    }

    @Test
    public void positiveLoginUserDTOWith() {
        UserDTOWith userDTOWith = new UserDTOWith()
                .withEmail("qwerty@qwer.ty")
                .withPassword("Qwerty!1");
        app.getUserHelper().login(userDTOWith);
        flagIsUserLogin = true;
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());

    }

    @Test(dataProvider = "logInReg", dataProviderClass = DataProviderLogin.class)
    public void positiveLogin(UserDtoLombok userDP) {
        app.getUserHelper().loginUserDtoLombok(userDP);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test
    public void negativePasswordWithoutSymbol() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("qwerty@qwer.ty")
                .password("Qwerty11")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test
    public void negativePasswordWithoutNumbers() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("qwerty@qwer.ty")
                .password("Qwerty!!")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test
    public void negativePasswordWithoutLetters() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("qwerty@qwer.ty")
                .password("12345678!1")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }
}