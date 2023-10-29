package manager;

import dto.UserDTO;
import dto.UserDTOWith;
import dto.UserDtoLombok;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant.*;

public class UserHelper extends BaseHelper{

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    By btnLoginNavigatorMenu = By.xpath("//a[contains(@href, '/login')]");
    By inputEmailLoginForm = By.xpath("//input[@id='email']");
    By inputPasswordLoginForm = By.xpath("//input[@id='password']");
    By btnYallaLoginForm = By.xpath("//button[@type='submit']");
    By textSuccessLoginPopUp = By.xpath("//h2[@class='message']");
    By btnOpenRegForm = By.xpath("//a[contains(@href, '/registration')]");
    By inputNameReg = By.xpath("//input[@id='name']");
    By inputLastNameReg = By.xpath("//input[@id='lastName']");
    By inputEmailReg = By.xpath("//input[@id='email']");
    By inputPasswordReg = By.xpath("//input[@id='password']");
    String btnRegNewUser = "document.querySelector('#terms-of-use').click();";
    String btnOkPopUpStr = "document.querySelector(`[type='button']`).click();";
    By checkBoxReg = By.xpath("//label[@for='terms-of-use']");
    By btnYallaReg = By.xpath("//button[@type='submit']");
    By textPopUpSuccessRegH1 = By.xpath("//div[@class='dialog-container']//h1[@class='title']");
   // By btnLogout = By.xpath("//a[contains(@href, 'logout')]");

    By btnLogout = By.xpath("//a[@ng-reflect-query-params='[object Object]']");
    By btnOkPopUp = By.xpath("//button[@type='button']");
    By btnNotOkPopUp = By.xpath("//button[text()='Ok']");

    By errorMessageWrongLogin = By.xpath("//h2[@class='message']");
    By errorMessageWrongEmailReg = By.xpath("//input[@autocomplete='email']/..//div//div");
    By errorMessageIncorrectPasswordReg = By.xpath("//input[@autocomplete='new-password']/..//div//div");

    public void login(UserDTO userDTO) {
       // clickBase(btnLoginNavigatorMenu);
        typeTextBase(inputEmailLoginForm, userDTO.getEmail());
        typeTextBase(inputPasswordLoginForm, userDTO.getPassword());
        clickBase(btnYallaLoginForm);
    }

    public void login(UserDTOWith userDTO) {
      //  clickBase(btnLoginNavigatorMenu);
        typeTextBase(inputEmailLoginForm, userDTO.getEmail());
        typeTextBase(inputPasswordLoginForm, userDTO.getPassword());
        clickBase(btnYallaLoginForm);
    }

    public void loginUserDtoLombok(UserDtoLombok user) {
      //  clickBase(btnLoginNavigatorMenu);
        typeTextBase(inputEmailLoginForm, user.getEmail());
        typeTextBase(inputPasswordLoginForm, user.getPassword());
        clickBase(btnYallaLoginForm);
    }

    public void openLoginPage() {
        clickBase(btnLoginNavigatorMenu);
    }

    public void openRegistrationPage(){
        clickBase(btnOpenRegForm);
    }

    public boolean validatePopUpMessageSuccessAfterLogin() {
        return isTextContainsGet2Strings("LOGGED IN SUCCESS", getTextBase(textSuccessLoginPopUp));
    }

    public boolean validatePopUpMessageLoginIncorrect() {
        return isTextContainsGet2Strings("LOGIN OR PASSWORD INCORRECT", getTextBase(errorMessageWrongLogin));
    }

    public void fillRegistrationForm(UserDtoLombok user) {
       clickBase(btnOpenRegForm);
        typeTextBase(inputNameReg, user.getName());
        typeTextBase(inputLastNameReg, user.getLastName());
        typeTextBase(inputEmailReg, user.getEmail());
        typeTextBase(inputPasswordReg, user.getPassword());
        //clickBase(checkBoxReg);
        clickByXY(checkBoxReg, 5,15);
        //jsClickBase(btnRegNewUser);
        clickBase(btnYallaReg);
    }

    public boolean validatePopUpMessageSuccessAfterRegistration() {
        String expectedResult = "Registered".toUpperCase();
        return isTextEqual(textPopUpSuccessRegH1, expectedResult);
    }

    public boolean btnLogoutExist() {
        return isElementExist(btnLogout);
    }

    public void logout() {
        clickBase(btnLogout);
    }

    public void logout2(){
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnLogout));
            clickBase(btnLogout);

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }

    public void clickOkPopUpSuccessLogin() {
        clickBase(btnOkPopUp);
       // typeTextBase(textPopUpSuccessRegH1, String.valueOf(Keys.ESCAPE));
        //jsClickBase(btnOkPopUpStr);
        //clickByXY(btnOkPopUp, 38, 503);
        //      clickBase(textPopUpSuccessRegH1);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

//        Actions actions = new Actions(driver);
//        // Use the sendKeys method to simulate pressing the "Enter" key on the active element
//        actions.sendKeys(Keys.TAB).perform();
//        actions.sendKeys(Keys.ESCAPE).perform();
    }

    public void clickOkPopUpNotLogin() {
        clickBase(btnNotOkPopUp);

    }

    public boolean validateMessageIncorrectEmailReg() {
        return isTextEqual(errorMessageWrongEmailReg, "Wrong email format");
    }

    public boolean validateMessageWrongPasswordReg() {
        return isTextEqual(errorMessageIncorrectPasswordReg,
                "PASSWORD MUST CONTAIN 1 UPPERCASE LETTER, 1 LOWERCASE LETTER, 1 NUMBER AND ONE SPECIAL SYMBOL OF [@$#^&*!]");
    }

    public boolean validateErrorEmptyEmailReg() {
        return isTextEqual(errorMessageWrongEmailReg, "Email is required");
    }

    public void refreshPage() {
        driver.navigate().refresh();;
    }

}