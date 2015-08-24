package com.example.gq.lifeapp.model.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.gq.lifeapp.app.MyApplication;
import com.example.gq.lifeapp.model.NoteModelIntf;
import com.example.gq.lifeapp.model.bean.NoteBean;

import java.util.ArrayList;

/**
 * Created by gq on 2015/8/19.
 */
public class NoteModel implements NoteModelIntf {

    private static NoteDatabaseHelper dbHelper = new NoteDatabaseHelper(MyApplication.getContext(), "Note", null, 1);;
    private SQLiteDatabase db = dbHelper.getWritableDatabase();

    private static NoteModel instance = new NoteModel();

    public static NoteModel getInstance() {
        return instance;
    }

    @Override
    public void add(NoteBean noteBean) {
        ContentValues values = new ContentValues();
        Log.d("TAG",noteBean.getKeyword());
        values.put("id", noteBean.getId());
        values.put("time", noteBean.getTime());
        values.put("keyword", noteBean.getKeyword());
        values.put("content", noteBean.getContent());
        db.insert("Note", null, values);
        values.clear();
    }

    @Override
    public void delete(int id) {
        db.delete("Note", "id = ?", new String[]{String.valueOf(id)});
        setRealID(id);
    }

    @Override
    public void change(int id, NoteBean noteBean) {
        ContentValues values = new ContentValues();
        values.put("time", noteBean.getTime());
        values.put("keyword", noteBean.getKeyword());
        values.put("content", noteBean.getContent());
        db.update("Note", values, "id = ?", new String[]{String.valueOf(id)});
        values.clear();
    }

    @Override
    public NoteBean query(int id) {
        Cursor cursor = db.query("Note", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        NoteBean noteBean = new NoteBean();
        if (cursor.moveToFirst()){
            noteBean.setId(cursor.getInt(cursor.getColumnIndex("id")));
            noteBean.setTime(cursor.getString(cursor.getColumnIndex("time")));
            noteBean.setKeyword(cursor.getString(cursor.getColumnIndex("keyword")));
            noteBean.setContent(cursor.getString(cursor.getColumnIndex("content")));
        }
        return noteBean;
    }

    @Override
    public ArrayList<NoteBean> queryAll() {
        Cursor cursor = db.query("Note", null, null, null, null, null, null);
        ArrayList<NoteBean> arrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                NoteBean noteBean = new NoteBean();
                noteBean.setId(cursor.getInt(cursor.getColumnIndex("id")));
                noteBean.setTime(cursor.getString(cursor.getColumnIndex("time")));
                noteBean.setKeyword(cursor.getString(cursor.getColumnIndex("keyword")));
                noteBean.setContent(cursor.getString(cursor.getColumnIndex("content")));
                arrayList.add(noteBean);
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    private void setRealID(int id) {
        Cursor cursor = db.query("Note", null, "id > ?", new String[]{String.valueOf(id)}, null, null, null);
        ContentValues value = new ContentValues();
        if (cursor.moveToFirst()) {
            do {
                int realID = cursor.getInt(cursor.getColumnIndex("id"));
                value.put("id", realID - 1);
                db.update("Note", value, "id = ?", new String[]{String.valueOf(realID)});
                value.clear();
            } while (cursor.moveToNext());
        }
    }

    public int getMaxID(){
        Cursor cursor = db.query("Note", new String[]{"id"}, null, null, null, null, null);
        int maxID = 0;
        if (cursor.moveToFirst()) {
            do {
                maxID = cursor.getInt(cursor.getColumnIndex("id"));
            } while (cursor.moveToNext());
        }
        return maxID;
    }
}
