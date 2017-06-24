package com.example.rachid.smscatcher.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rachid.smscatcher.models.ParticipationDto;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by rachid on 28/03/17.
 */
public class ParticipationAdapter extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "SmsCatcherApplication.db";
    public long numSeq = 0;

    public ParticipationAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ParticipationContract.SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If you need to add a column
        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE "+ParticipationContract.TABLE_NAME+" ADD COLUMN "+ParticipationContract.COLUMN_NAME_DATE+" TEXT");
        }
        //db.execSQL(ParticipationContract.SQL_DELETE_ENTRIES);
        //onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public void setNumSeq(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(ParticipationContract.QUERY_SELECT_NUMEROS_DESC, null);
        if(cursor.moveToNext()) {
            numSeq = cursor.getLong(cursor.getColumnIndexOrThrow(ParticipationContract.COLUMN_NAME_NUMERO));
            numSeq++;
        }
        cursor.close();
        db.close();
    }
    public long insert(ParticipationDto toInsert){
        setNumSeq();
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(ParticipationContract.COLUMN_NAME_NUMERO,numSeq);
        values.put(ParticipationContract.COLUMN_NAME_NOM,toInsert.getNom());
        values.put(ParticipationContract.COLUMN_NAME_PRENOM,toInsert.getPrenom());
        values.put(ParticipationContract.COLUMN_NAME_BOISSON,toInsert.getBoisson());
        values.put(ParticipationContract.COLUMN_NAME_DATE,toInsert.getDate());
        long rowId = db.insert(ParticipationContract.TABLE_NAME,null,values);
        if(rowId > -1){
            numSeq++;
        }
        db.close();
        return rowId;
    }
    public List<ParticipationDto> selectAll(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor  cursor = db.rawQuery(ParticipationContract.QUERY_SELECT_ALL,null);
        if (cursor.moveToFirst()) {
            List<ParticipationDto> list = new LinkedList<ParticipationDto>();
            while (cursor.isAfterLast() == false) {
                ParticipationDto temp = new ParticipationDto();
                temp.setNumero(cursor.getInt(cursor.getColumnIndexOrThrow(ParticipationContract.COLUMN_NAME_NUMERO)));
                temp.setNom(cursor.getString(cursor.getColumnIndexOrThrow(ParticipationContract.COLUMN_NAME_NOM)));
                temp.setPrenom(cursor.getString(cursor.getColumnIndexOrThrow(ParticipationContract.COLUMN_NAME_PRENOM)));
                temp.setBoisson(cursor.getString(cursor.getColumnIndexOrThrow(ParticipationContract.COLUMN_NAME_BOISSON)));
                temp.setDate(cursor.getString(cursor.getColumnIndexOrThrow(ParticipationContract.COLUMN_NAME_DATE)));
                list.add(temp);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
            return list;
        }
        cursor.close();
        db.close();
        return new LinkedList<ParticipationDto>();
    }


}
