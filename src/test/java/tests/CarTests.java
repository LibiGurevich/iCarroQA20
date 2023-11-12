package tests;

import dto.AddCarDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CarTests extends BaseTest{
    @BeforeClass
    public void loginPreConditions() {
        app.navigateToMainPage();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        app.getUserHelper().clickOkPopUpSuccessLogin();
    }

    @Test
    public void addNewCarTest() {
        String serNumber = randomUtils.generateStringDigits(12);
        AddCarDTO car = AddCarDTO.builder()
                .serialNumber(serNumber)
                .manufacture("daihatsu")
                .model("terios")
                .year(2006)
                .fuel("petrol")
                .seats(4)
                .carClass("skfv")
                .pricePerDay(80)
                .city("Krayot")
                .build();

        app.getCarHelper().clickAddNewCar();
        app.getCarHelper().fillFormNewCar(car);
        Assert.assertTrue(app.getCarHelper().validateMessagePopUp());
    }

    @AfterClass
    public void logoutAfterConditions() {
        app.getCarHelper().clickAddNewCarPopUp();
        app.getUserHelper().logout();
        app.navigateToMainPage();
    }
}