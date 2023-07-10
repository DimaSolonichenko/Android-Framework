package org.prog;

import org.prog.manager.Car;
import org.prog.manager.CodeDemo;
import org.prog.manager.PoliceStation;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ThirdTest {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("This is executed before all tests");
    }

    @Test(dataProvider = "data")
    public void myTest(String plateNumber, String errorText) {
        try{
            CodeDemo codeDemo = new CodeDemo();
            codeDemo.init();
            PoliceStation policeStation = codeDemo.getStation();

            policeStation.printOwnerOfACarWithCertainPlateNumbers(plateNumber);

        } catch (RuntimeException e){
            System.out.println(plateNumber);
            Assert.assertEquals(e.getMessage(), errorText);
            //System.out.println();
        }


    }

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"AA0000AA", "Car with  EE0000EE is not assigned"},
                {"EE0000EE", "Car with  EE0000EE is not assigned"},
                {"AA0002AA", "No such plate number found! AA0002AA"}
        };
    }

    @AfterSuite
    public void afterAll() {
        System.out.println("This is executed after all tests");
    }
}