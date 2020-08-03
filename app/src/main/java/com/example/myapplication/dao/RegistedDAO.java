package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.RegistedCourse;

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.database.DatabaseHelper.CONTENT_REGISTED;
import static com.example.myapplication.database.DatabaseHelper.NAME_REGISTED;
import static com.example.myapplication.database.DatabaseHelper.TABLE_REGISTED;

public class RegistedDAO {

    private SQLiteDatabase sqLiteDatabase ;
    private DatabaseHelper databaseHelper;

    public RegistedDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public long insertRegisted(RegistedCourse registedCourse) {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues() ;
        contentValues.put(NAME_REGISTED, registedCourse.registed);
        contentValues.put(CONTENT_REGISTED, registedCourse.content);
        long result = sqLiteDatabase.insert(TABLE_REGISTED, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long deleteRegisted(String name_registed) {
        long result = -1 ;
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        result = sqLiteDatabase.delete(TABLE_REGISTED, NAME_REGISTED + "=?", new String[]{name_registed});
        return result;
    }

    public List<RegistedCourse> getAllRegisted() {
        List<RegistedCourse> registedCourseList = new ArrayList<>();
        registedCourseList.clear();
        String query = "SELECT * FROM " + TABLE_REGISTED;
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if (cursor != null) {
            if (cursor.getCount()>0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String NAME_ = cursor.getString(cursor.getColumnIndex(NAME_REGISTED));
                    String CONTENT_ = cursor.getString(cursor.getColumnIndex(CONTENT_REGISTED));
                    RegistedCourse registedCourse = new RegistedCourse(NAME_,CONTENT_);
                    registedCourseList.add(registedCourse);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return registedCourseList;
    }
}
