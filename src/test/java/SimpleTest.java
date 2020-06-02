import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleTest {

    private AndroidDriver driver;

    @BeforeMethod
    public void firstSimpleTest() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Emulator");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("fullReset", true);
        capabilities.setCapability("settings[enableMultiWindows]", true);
        capabilities.setCapability("app", "C:\\Users\\User16\\IdeaProjects\\ApiDemos-debug.apk"); //set full path to apk
        capabilities.setCapability("autoGrantPermissions", true);

        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4724/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(description = "alarm/controller (toast)")
    public void controllerToastTest() {
        MobileElement appElement = (MobileElement) driver.findElement(MobileBy.AccessibilityId("App"));
        appElement.click();

        MobileElement alarmElement = (MobileElement) driver.findElement(MobileBy.AccessibilityId("Alarm"));
        alarmElement.click();

        MobileElement alarmController = (MobileElement) driver.findElement(MobileBy.AccessibilityId("Alarm Controller"));
        alarmController.click();

        MobileElement oneShotElement = (MobileElement) driver.findElement(MobileBy.id("one_shot"));
        oneShotElement.click();

        MobileElement toastMessage = (MobileElement) driver.findElement(MobileBy.xpath("//android.widget.Toast"));
        String toastText = "One-shot alarm will go off in 30 seconds based on the real time clock. Try changing the current time before then!";
        assertThat("Text is equal",toastMessage.getText(),is(toastText));

    };
    @Test(description = "alert dialogs (dialog window)")
    public void alertDialogs() {
        MobileElement appElement = (MobileElement) driver.findElement(MobileBy.AccessibilityId("App"));
        appElement.click();

        MobileElement alertDialog = (MobileElement) driver.findElement(MobileBy.AccessibilityId("Alert Dialogs"));
        alertDialog.click();

        MobileElement OkCancelDialogWithMessage = (MobileElement) driver.findElement(MobileBy.AccessibilityId("OK Cancel dialog with a message"));
        OkCancelDialogWithMessage.click();

        MobileElement dialogMessage = (MobileElement) driver.findElement(MobileBy.id("android:id/alertTitle"));
        String dialogText = "Lorem ipsum dolor sit aie consectetur adipiscing\nPlloaso mako nuto siwuf cakso dodtos anr koop.";
        assertThat("Text is equal",dialogMessage.getText(),is(dialogText));
    };
/*    @Test(description = "alarm/controller (toast)", enabled=false)
    public void contextMenu() {
        MobileElement appElement = (MobileElement) driver.findElement(MobileBy.AccessibilityId("App"));
        appElement.click();

        MobileElement fragmentElement = (MobileElement) driver.findElement(MobileBy.AccessibilityId("Fragment"));
        fragmentElement.click();

        MobileElement contextMenuElement = (MobileElement) driver.findElement(MobileBy.AccessibilityId("Context Menu"));
        contextMenuElement.click();

        MobileElement longPressButton = (MobileElement) driver.findElement(MobileBy.AccessibilityId("Long press me"));
        TouchAction action=new TouchAction(driver);
        action.longPress(element(longPressButton));
        action.perform();

        MobileElement toastMessage = (MobileElement) driver.findElement(MobileBy.xpath("//android.widget.Toast"));
        String toastText = "One-shot alarm will go off in 30 seconds based on the real time clock. Try changing the current time before then!";
        assertThat("Text is equal",toastMessage.getText(),is(toastText));
    };

    @Test(description = "Views/popup menu (menu)")
    public void popupMenu() {
    MobileElement popUpElement = (MobileElement) driver.findElement(MobileBy.AccessibilityId("Popup Menu"));
    popUpElement.click();

    MobileElement popUpDialog = (MobileElement) driver.findElement(MobileBy.AccessibilityId("Make a Popup!"));
    popUpDialog.click();

    MobileElement dialogMessage = (MobileElement) driver.findElement(MobileBy.id("android:id/alertTitle"));
};

 */
}
