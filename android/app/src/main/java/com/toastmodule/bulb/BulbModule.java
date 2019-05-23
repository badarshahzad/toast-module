package com.toastmodule.bulb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.toastmodule.smsmodule.SmsService;

public class BulbModule extends ReactContextBaseJavaModule {
    private static Boolean isOn = false;
    private ReactContext reactContex;
    private LocalBroadcastReceiver mLocalBroadcastReceiver;
    private static final String TAG = "BulbModule";
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getReactApplicationContext());
    public BulbModule(ReactApplicationContext reactContext) {

        super(reactContext);
        this.reactContex = reactContext;
        this.mLocalBroadcastReceiver = new LocalBroadcastReceiver();
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(reactContext);
        localBroadcastManager.registerReceiver(mLocalBroadcastReceiver, new IntentFilter("my-custom-event"));
        Log.i(TAG, "BulbModule: Broadcast event is register now");

    }




    @ReactMethod
    public void getStatus(Callback successCallback) {
        successCallback.invoke("error", isOn);
    }


    @ReactMethod
    public void setPredeineMessage(String message){


        if(sharedPreferences.getString(SmsService.AUTO_MESSAGE,"null").equals("null")){
            sharedPreferences.edit().putString(SmsService.AUTO_MESSAGE,message).apply();
            Log.i(TAG, "setPredeineMessage: First message "+message);
        }else{
            Log.i(TAG, "setPredeineMessage: Old msg is: "+sharedPreferences.getString(SmsService.AUTO_MESSAGE,"Auto reply"));
            Log.i(TAG, "setPredeineMessage: New Message is "+ message);
//            sharedPreferences.edit().remove(SmsService.AUTO_MESSAGE);
            sharedPreferences.edit().putString(SmsService.AUTO_MESSAGE,message).apply();
        }



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


    public class LocalBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            String key = intent.getStringExtra("sms-response-key");
            /**
             * Sms received Response with message
             */
            if(key.equals("sms-recevied-response")){

               Bundle bundle = intent.getBundleExtra("bundle");
                // get sms objects
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0) {
                    return;
                }
                // large message might be broken into many
                SmsMessage[] messages = new SmsMessage[pdus.length];
                // StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    // sb.append(messages[i].getMessageBody());
                }

                String sender = messages[0].getOriginatingAddress();
                sendMessage(sender);
            }

            /**
             * Missed call Response with message
             */
            if(key.equals("missed-call-response")){

                String senderNumber = intent.getStringExtra("sender-number");
                sendMessage(senderNumber);
            }

        }
        public void sendMessage(String senderNumber){
            SmsManager sms = SmsManager.getDefault();
            String message = sharedPreferences.getString(SmsService.AUTO_MESSAGE,"Integrated Logics Predeine Message");
            sms.sendTextMessage(senderNumber, null, message, null, null);

            Log.i(TAG, "onReceive: Store message is: "+message);
        }
    }
}