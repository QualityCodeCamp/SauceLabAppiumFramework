package com.Automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private AppiumDriver mobileDriver =null;
    private String targetPlatform =null;
    private String deviceID =null;
    private String deviceName=null;

    public static InheritableThreadLocal<AppiumDriver> staticMobDriver =new InheritableThreadLocal<>();

    public synchronized void setTargetPlatform(String platform){
        targetPlatform=platform;
    }

    public void setDeviceID(String deviceID){
        System.out.println("Setting deviceId"+ deviceID);
        this.deviceID=deviceID;

    }

    public void setDeviceName(String deviceName){
        System.out.println("Setting deviceId"+ deviceID);
        this.deviceName=deviceName;

    }

    public boolean isAndroidPlatform(){
        if (targetPlatform != null && targetPlatform.equalsIgnoreCase("Android")) {

            return true;
        }
        else
        {
            return System.getProperty("PLATFORM") != null && System.getProperty("PLATFORM").equalsIgnoreCase("Android");
        }
    }

    public void navigateBack(){
        System.out.println("Navigating back");
        this.getDriver().navigate().back();
    }

    public AppiumDriver getDriver(){
        return mobileDriver;
    }

    public String getDeviceID(){
        return  deviceID;
    }

    public String getDeviceName(){
        return deviceName;
    }


    public void setMobileDriver(String deviceName, String deviceId, String driverURL, String platform, String appName) throws MalformedURLException {
        if(platform.equalsIgnoreCase("Android")){
            targetPlatform=platform;
            mobileDriver=new AndroidDriver(new URL(driverURL),CapabilityManager.getAndroidCapability(deviceName,deviceId,appName));

        }
        else if (platform.equalsIgnoreCase("iOS")){
            mobileDriver=new IOSDriver(new URL(driverURL),CapabilityManager.getIOSCapability(deviceName,appName));

        }
        staticMobDriver.set(mobileDriver);

    }
    public void terminateapp(){
        if(isAndroidPlatform()){
            ((AndroidDriver)getDriver()).terminateApp("com.swaglabsmobileapp");

        }
        else {
            ((IOSDriver)getDriver()).terminateApp("com.saucelabs.SwagLabsMobileApp");
        }

    }

    public void activeapp(){
        if(isAndroidPlatform()){
            ((AndroidDriver)getDriver()).activateApp("com.swaglabsmobileapp");

        }
        else {
            ((IOSDriver)getDriver()).activateApp("com.saucelabs.SwagLabsMobileApp");
        }

    }

}
