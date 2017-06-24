package com.example.rachid.smscatcher.dal;

/**
 * Created by rachid on 28/03/17.
 */
public final class ParticipationContract {

    private ParticipationContract() {}

    public static final String TABLE_NAME = "participations";
    public static final String COLUMN_NAME_NUMERO = "numero";
    public static final String COLUMN_NAME_NOM = "nom";
    public static final String COLUMN_NAME_PRENOM = "prenom";
    public static final String COLUMN_NAME_BOISSON = "boisson";
    public static final String COLUMN_NAME_DATE = "date";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ParticipationContract.TABLE_NAME + " (" +
                    ParticipationContract.COLUMN_NAME_NUMERO + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    ParticipationContract.COLUMN_NAME_NOM + " TEXT NOT NULL," +
                    ParticipationContract.COLUMN_NAME_PRENOM + " TEXT NOT NULL," +
                    ParticipationContract.COLUMN_NAME_BOISSON + " TEXT NOT NULL,"+
                    ParticipationContract.COLUMN_NAME_DATE + " TEXT NOT NULL,"+
                    "UNIQUE ("+ParticipationContract.COLUMN_NAME_NOM+","+ParticipationContract.COLUMN_NAME_PRENOM+"));";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ParticipationContract.TABLE_NAME;

    public static final String QUERY_SELECT_ALL = "SELECT * FROM " + ParticipationContract.TABLE_NAME+" ORDER BY "+COLUMN_NAME_NUMERO+" ASC";
    public static final String QUERY_SELECT_NUMEROS_DESC = "SELECT p."+ParticipationContract.COLUMN_NAME_NUMERO+" FROM "+ParticipationContract.TABLE_NAME+" p ORDER BY p.numero DESC";

}

