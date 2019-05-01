package com.toastmodule.bulb;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class BulbModule extends ReactContextBaseJavaModule  {
    private static Boolean isOn = false;
    public BulbModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void getStatus(
            Callback successCallback) {
        successCallback.invoke("error", isOn);

    }

    @ReactMethod
    public void turnOn() {
        isOn = true;
        System.out.println("BulbModule is turn ON");
    }
    @ReactMethod
    public void turnOff() {
        isOn = false;
        System.out.println("BulbModule is turn OFF");
    }

    @Override
    public String getName() {
        return "BulbModule";
    }

}