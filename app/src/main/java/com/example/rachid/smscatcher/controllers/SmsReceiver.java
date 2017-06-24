package com.example.rachid.smscatcher.controllers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.rachid.smscatcher.builders.SmsCatcherBuilder;
import com.example.rachid.smscatcher.models.SmsModel;

public class SmsReceiver extends BroadcastReceiver {
    private final String TAG = "SmsReceiver";
    private final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ACTION)){
            StringBuilder sb = new StringBuilder();
            Bundle bundle = intent.getExtras();
            if(bundle != null){
                SmsMessage []messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                for(SmsMessage currentMessage : messages){
                   traiterSMS(context,currentMessage.getMessageBody());
                }
            }
        }
    }
    private void traiterSMS(Context context,String bodyMessage){
        if(bodyMessage == null || bodyMessage.isEmpty())
            return;
        if(bodyMessage.matches("^#LaFete ;(\\w)+ ;(\\w)+ ;(\\w| ){3,40}$")){
                Toast.makeText(context, bodyMessage, Toast.LENGTH_LONG).show();
                SmsCatcherBuilder app = (SmsCatcherBuilder) context.getApplicationContext();
                SmsModel smsModel = app.getSmsModel();
                smsModel.notifyNewSmsReceived(bodyMessage);
        }
    }

}
