package com.toastmodule.callmodule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import android.content.Context;
import android.util.Log;

import java.util.Date;

public class CallReceiver extends PhonecallReceiver {

    private static final String TAG = "CallReceiver";

    @Override
    protected void onIncomingCallStarted(Context ctx, String number, Date start) {
        Log.i(TAG, "onIncomingCallStarted: "+number);
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        Log.i(TAG, "onIncomingCallEnded: "+number);
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {
        Log.i(TAG, "onMissedCall: "+number);

        //Call send messsage
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(ctx);
        Intent customEvent= new Intent("my-custom-event");
        customEvent.putExtra("sms-response-key", "missed-call-response");
        customEvent.putExtra("sender-number",number);
        localBroadcastManager.sendBroadcast(customEvent);
    }

}
