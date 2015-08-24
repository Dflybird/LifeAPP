package com.example.gq.lifeapp.model.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gq on 2015/8/19.
 */
public class NoteDatabaseHelper extends SQLiteOpenHelper {

    private static final String CREAT_NOTE = "create table Note("
            + "id integer primary key ,"
            + "time text,"
            + "keyword text,"
            + "content text)";

    public NoteDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
