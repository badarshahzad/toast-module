package com.toastmodule;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.support.v4.app.ActivityCompat;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.toastmodule.bulb.BulbPackage;
import com.toastmodule.toastmodule.CustomToastPackage;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(new MainReactPackage(), new BulbPackage(), new CustomToastPackage());
    }

    @Override
    protected String getJSMainModuleName()
    {
      return "index";
    }


  };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();


//    ActivityCompat.requestPermissions(this,
//            new String[]{Manifest.permission.SEND_SMS,Manifest.permission.RECEIVE_SMS},101);

    SoLoader.init(this, /* native exopackage */ false);


  }

}
