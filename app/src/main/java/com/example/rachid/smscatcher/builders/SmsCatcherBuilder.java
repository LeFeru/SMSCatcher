package com.example.rachid.smscatcher.builders;

import android.app.Application;

import com.example.rachid.smscatcher.models.SmsModel;

/**
 * Created by rachid on 22/03/17.
 */
public class SmsCatcherBuilder extends Application {

    private SmsModel smsModel;
    @Override
    public void onCreate() {
        smsModel = new SmsModel(this);
        super.onCreate();
    }

    public SmsModel getSmsModel(){
        return smsModel;
    }
}
