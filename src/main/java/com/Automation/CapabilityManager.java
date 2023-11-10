package com.Automation;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.Capabilities;

import java.time.Duration;

public class CapabilityManager {
    public static Capabilities getAndroidCapability(String deviceName, String deviceId, String appName) {
        UiAutomator2Options androidcapabilities=new UiAutomator2Options();
        androidcapabilities.setUdid(deviceId);
        androidcapabilities.setDeviceName(deviceName);
        androidcapabilities.setApp(appName);
        androidcapabilities.setCapability("appPackage","com.swaglabsmobileapp");
        androidcapabilities.setCapability("appActivity","com.swaglabsmobileapp.MainActivity");
        androidcapabilities.setCapability("autoGrantPermissions","true");
        return androidcapabilities;
    }

    public static Capabilities getIOSCapability(String deviceName, String appName) {
        XCUITestOptions ioscapabilities=new XCUITestOptions();
        ioscapabilities.setDeviceName(deviceName);
        ioscapabilities.setApp(appName);
        ioscapabilities.setWdaLaunchTimeout(Duration.ofSeconds(20));
        return ioscapabilities;
    }
}
