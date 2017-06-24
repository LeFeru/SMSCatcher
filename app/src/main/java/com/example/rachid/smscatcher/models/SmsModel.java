package com.example.rachid.smscatcher.models;

import android.content.Context;
import android.util.Log;

import com.example.rachid.smscatcher.controllers.SmsModelObserver;
import com.example.rachid.smscatcher.dal.ParticipationAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by rachid on 22/03/17.
 */
public class SmsModel {
    private List<ParticipationDto> participationDtos;
    private List<SmsModelObserver> toNotify = new ArrayList<SmsModelObserver>();
    private ParticipationAdapter participationAdapter;
    public SmsModel(Context context){
        participationAdapter = new ParticipationAdapter(context);
        participationDtos = new ArrayList<ParticipationDto>();
        participationDtos.addAll(participationAdapter.selectAll());
    }

    public List<ParticipationDto> getDixDerniers(){
        if(participationDtos == null || participationDtos.isEmpty()){
            return new ArrayList<ParticipationDto>();
        }
        if(participationDtos.size() <= 10){
            return Collections.unmodifiableList(participationDtos);
        }
        List<ParticipationDto> toReturn = new ArrayList<ParticipationDto>();
        for(int i = participationDtos.size()-10; i < participationDtos.size(); i++){
            toReturn.add(participationDtos.get(i));
        }
        return toReturn;
    }

    public ParticipationDto getDernier(){
        if(participationDtos.isEmpty()){
            return null;
        }
        return participationDtos.get(participationDtos.size()-1);
    }

    public void notifyNewSmsReceived(String bodyMessage) {
        StringTokenizer strTok = new StringTokenizer(bodyMessage," ;");
        ParticipationDto participation = new ParticipationDto(strTok.nextToken(),strTok.nextToken(),strTok.nextToken(),strTok.nextToken());
        int rowId = (int)participationAdapter.insert(participation);
        if(rowId >= 0){
            participation.setNumero(rowId);
            participationDtos.add(participation);
            Log.i("Insert","success");
            notifySmsModelChange();
        }
        else{
            Log.i("Insert","fail");
        }
    }

    public void notifySmsModelChange(){
        for(SmsModelObserver listener : toNotify){
            listener.onSmsReceived();
        }
    }

    public void ajouterSmsModelChangeListener(SmsModelObserver listener) {
        toNotify.add(listener);
    }
}
