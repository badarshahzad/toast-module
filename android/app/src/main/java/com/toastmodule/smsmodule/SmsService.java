package com.toastmodule.smsmodule;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.ReactContext;
import com.toastmodule.MainApplication;

public class SmsService extends IntentService  {

    private static final String TAG = "SmsService";
    public static final String AUTO_MESSAGE = "";
    public static final String DEFAULT_MESSAGE = "Integrated Logics Predeine Message";

    public static SharedPreferences sharedPreferences ;


    // Predefine Message
    private static String message = "Integrated Logics Predeine Message";

    public SmsService() {
        super("SendSmsAutoReply");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("TAG", "Hehehe");
        // String message = intent.getStringExtra("msg");
        // String sender = intent.getStringExtra("number");



        MainApplication application = (MainApplication) this.getApplication();

        ReactNativeHost reactNativeHost = application.getReactNativeHost();
        ReactInstanceManager reactInstanceManager = reactNativeHost.getReactInstanceManager();
        ReactContext reactContext = reactInstanceManager.getCurrentReactContext();


        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Log.i(TAG, "onHandleIntent: ");
            // get sms objects
//            Object[] pdus = (Object[]) bundle.get("pdus");
//            if (pdus.length == 0) {
//                return;
//            }
//            // large message might be broken into many
//            SmsMessage[] messages = new SmsMessage[pdus.length];
//            // StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < pdus.length; i++) {
//                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
//                // sb.append(messages[i].getMessageBody());
//            }
//            String sender = messages[0].getOriginatingAddress();
//            // String message = sb.toString();
//            Log.i("TAG", sender);
//            SmsManager sms = SmsManager.getDefault();
//            Log.i("TAG", sender);
//            if (sender.equals("Ufone")) {
//                return;
//            }
//
//
//            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(reactContext);
//            message = sharedPreferences.getString(AUTO_MESSAGE,"Integrated Logics Predeine Message");
//            sms.sendTextMessage(sender, null, message, null, null);
//            Log.i(TAG, "onHandleIntent: Message is sent!");

            //TODO: when the app is close it should work which is not wokring.
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
            Intent customEvent= new Intent("my-custom-event");
            customEvent.putExtra("sms-response-key", "sms-recevied-response");
            customEvent.putExtra("bundle",bundle);
            localBroadcastManager.sendBroadcast(customEvent);

        }

    }
}
