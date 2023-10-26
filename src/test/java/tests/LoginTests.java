package tests;

import dto.UserDTO;
import dto.UserDTOWith;
import dto.UserDtoLombok;
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
    public void preconditionsBeforeMethod() {
        preconditionForLoginAndRegTests();
    }

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

//    @Test
//    public void positiveLogin() {
//        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
//    }

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