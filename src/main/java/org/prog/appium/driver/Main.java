package org.prog.appium.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("appPackage", "com.example.myapplication");
            capabilities.setCapability("appActivity", "com.example.myapplication.MainActivity");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("automationName", AutomationName.ANDROID_UIAUTOMATOR2);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");

            AppiumDriver driver = new AppiumDriver(new URL("http://127.0.0.1:4723/"), capabilities);

            WebElement element = driver.findElement(By.id("btn_next"));
            element.click();

            WebElement secondText = driver.findElement(By.xpath("//android.widget.TextView"));
            System.out.println(secondText.getText());
            driver.navigate().back();
            WebElement firstText = driver.findElement(By.xpath("//android.widget.TextView"));
            System.out.println(firstText.getText());
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
