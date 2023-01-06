package com.example.festival2.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MaBaseSQLite extends SQLiteOpenHelper {
    private final static String CREATE_TABLE_ARTISTES = "create table artistes (" +
            "artiste varchar(50) primary key," +
            "texte BLOB default null," +
            "web varchar(250) default null," +
            "image varchar(250) default null," +
            "date timestamp default CURRENT_TIMESTAMP," +
            "jour varchar(10)," +
            "heure varchar(5)," +
            "duree int," +
            "place varchar(50) default 'Rock en Seine'," +
            "scene varchar(50)" +
            ");";

    private final static String CREATE_TABLE_FAVORIS = "create table favoris (" +
            "artiste varchar(50) primary key," +
            "favori int default 1" +
            ");";

    private final static String CREATE_TABLE_PARTICIPES = "create table participes (" +
            "artiste varchar(50) primary key," +
            "participe int default 1," +
            "eventId int" +
            ");";

    private final static String CREATE_VIEW_INFORMATIONS = "create view informations AS " +
            "SELECT " +
            "a.*, b.favori, c.participe, c.eventId " +
            "FROM artistes a " +
            "LEFT JOIN favoris b ON a.artiste=b.artiste " +
            "LEFT JOIN participes c ON a.artiste=c.artiste;";

    public MaBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_ARTISTES);
        db.execSQL(CREATE_TABLE_FAVORIS);
        db.execSQL(CREATE_TABLE_PARTICIPES);
        db.execSQL(CREATE_VIEW_INFORMATIONS);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        db.execSQL("drop table artistes;");
        db.execSQL("drop table favoris;");
        db.execSQL("drop table particpes;");
        db.execSQL("drop table informations;");
    }
}