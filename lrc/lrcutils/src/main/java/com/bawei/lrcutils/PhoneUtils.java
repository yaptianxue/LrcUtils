package com.bawei.lrcutils;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneUtils {
    public static final int STATE_IN = 0;
    public static final int STATE_OUT = 1;
   public static void checkState(Context context, final PhoneStateCallBack phoneStateCallBack){
     TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
     telephonyManager.listen(new PhoneStateListener(){
         @Override
         public void onCallStateChanged(int state, String phoneNumber) {
             super.onCallStateChanged(state, phoneNumber);
             switch (state){
                 case TelephonyManager.CALL_STATE_RINGING://来电话
                     phoneStateCallBack.onCallStateChanged(STATE_IN);
                     break;

                 case TelephonyManager.CALL_STATE_IDLE://挂断
                     phoneStateCallBack.onCallStateChanged(STATE_OUT);
                     break;

                 case TelephonyManager.CALL_STATE_OFFHOOK://童话

                     break;

             }
         }
     },PhoneStateListener.LISTEN_CALL_STATE);

 }
    interface PhoneStateCallBack{
        void onCallStateChanged(int state);
    }
}
