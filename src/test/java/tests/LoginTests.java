package tests;

import data.DataProviderLogin;
import dto.UserDTO;
import dto.UserDTOWith;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends BaseTest{

    @AfterMethod
    public void postconditionsLogin() {
        if(flagPopUp) {
            flagPopUp = false;
            app.getUserHelper().clickOkPopUpSuccessLogin();
        }
        if(flagLogin) {
            flagLogin = false;
            app.getUserHelper().logout();
        }
    }

    @Test(priority = 1, invocationCount = 2)
    public void positiveLoginUserDTO() {
        UserDTO userDTO = new UserDTO("qwerty@qwer.ty", "Qwerty!1");
        app.getUserHelper().login(userDTO);
        flagLogin = true;
        flagPopUp = true;

        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test(groups={"smoke"})
    public void positiveLoginUserDTOWith() {
        UserDTOWith userDTOWith = new UserDTOWith()
                .withEmail("qwerty@qwer.ty")
                .withPassword("Qwerty!1");
        app.getUserHelper().login(userDTOWith);
        flagLogin = true;
        flagPopUp = true;

        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test(dataProvider = "loginCSV", dataProviderClass = DataProviderLogin.class, groups = {"smoke"})
    public void positiveLogin(UserDtoLombok user) {
        app.getUserHelper().loginUserDtoLombok(user);
        flagLogin = true;
        flagPopUp = true;

        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test(dataProvider = "positiveDataLogin", dataProviderClass = DataProviderLogin.class)
    public void positiveLogin2(UserDtoLombok user) {
        app.getUserHelper().loginUserDtoLombok(user);
        flagLogin = true;
        flagPopUp = true;

        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test(dataProvider = "negativePasswordDataLogin", dataProviderClass = DataProviderLogin.class)
    public void negativePasswordWithoutSymbol(UserDtoLombok user) {

        app.getUserHelper().loginUserDtoLombok(user);
        flagPopUp = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test(priority = 5)
    public void negativePasswordWithoutNumbers() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("qwerty@qwer.ty")
                .password("Qwerty!!")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        flagPopUp = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test(priority = 6)
    public void negativePasswordWithoutLetters() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("qwerty@qwer.ty")
                .password("12345678!")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        flagPopUp = true;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }
}