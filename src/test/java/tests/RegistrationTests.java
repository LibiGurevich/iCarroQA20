package tests;

import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegistrationTests extends BaseTest{

    @BeforeClass(alwaysRun = true)
    public void preconditionsBeforeClass() {
        if(app.ispageUrlHome()) {
            app.getUserHelper().openRegistrationPage();
        }
    }


//    @BeforeTest(alwaysRun = true)
//    public void preconditionsLogin() {
//        //  app.navigateToMainPage();
//        logoutIfLogin();
//
//        // user login
//        // user open web not login
//    }

    @AfterMethod(alwaysRun = true)
    public void preconditionsBeforeMethod() {
        try {
            preconditionForLoginAndRegTests();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    public void positiveRegistration() {
        String email = randomUtils.generateEmail(7);

        UserDtoLombok user = UserDtoLombok.builder()
                .email(email)
                .password("123456Aa$")
                .lastName("abdfg")
                .name("test")
                .build();

        app.getUserHelper().fillRegistrationForm(user);

        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterRegistration());
        app.getUserHelper().clickOkPopUpSuccessLogin();
    }

    @Test
    public void negativeRegistrationWrongEmail() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("abc@")
                .password("123456Aa$")
                .lastName("abdfg")
                .name("test")
                .build();

        app.getUserHelper().fillRegistrationForm(user);
        Assert.assertTrue(app.getUserHelper().validateMessageIncorrectEmailReg());
    }

    @Test
    public void negativeRegistrationWrongPassword() {
        String email = randomUtils.generateEmail(7);

        UserDtoLombok user = UserDtoLombok.builder()
                .email(email)
                .password("123456Aa")
                .lastName("abdfg")
                .name("test")
                .build();

        app.getUserHelper().fillRegistrationForm(user);
        Assert.assertTrue(app.getUserHelper().validateMessageWrongPasswordReg());
    }

    @Test
    public void negativeRegistrationBlankEmail() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("")
                .password("123456Aa$")
                .lastName("abdfg")
                .name("test")
                .build();

        app.getUserHelper().fillRegistrationForm(user);
        Assert.assertTrue(app.getUserHelper().validateErrorEmptyEmailReg());
    }
}