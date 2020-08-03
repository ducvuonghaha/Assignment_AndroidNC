package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.Maps;

import java.util.ArrayList;
import java.util.List;

public class PositionDao {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db, dc;
    public String TABLE_NAME_POSITION = "Position";
    public static final String CREATE_TABLE_POSITION = "Create table Position" +
            "(diaChi text priamry key,kinhDo text,viDo text)";

    public PositionDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
        dc = databaseHelper.getReadableDatabase();
    }


    public boolean insertPosition(Maps maps) {
        ContentValues values = new ContentValues();
        values.put("diaChi", maps.getDiaChi());
        values.put("kinhDo", maps.getKinhDo());
        values.put("viDo", maps.getViDo());

        long result = db.insert(TABLE_NAME_POSITION, null, values);

        try {
            if (result < 0) {
                return false;
            }
        } catch (Exception e) {
            Log.e("abc", "INSERT MAPS" + e.toString());
            return false;
        }
        return true;
    }

    public List<Maps> getAllPosition() {
        List<Maps> list = new ArrayList<>();
        String SELECT = "SELECT * FROM " + TABLE_NAME_POSITION;
        Cursor cursor = db.rawQuery(SELECT, null);
        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            do {

                Maps maps = new Maps();
                maps.setDiaChi(cursor.getString(0));
                maps.setKinhDo(cursor.getString(1));
                maps.setViDo(cursor.getString(2));

                list.add(maps);
                cursor.moveToNext();
            }while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }

    public Maps getAllPositionByName(String data) {
        Maps maps = new Maps();
        String SELECT = "SELECT * FROM " + TABLE_NAME_POSITION + " WHERE diaChi = ?";
        Cursor cursor = dc.rawQuery(SELECT, new String[]{data});
        cursor.moveToFirst();
        maps.setDiaChi(cursor.getString(0));
        maps.setKinhDo(cursor.getString(1));
        maps.setViDo(cursor.getString(2));


        cursor.close();
        return maps;
    }
}
