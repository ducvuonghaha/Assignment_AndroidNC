package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.myapplication.dao.PositionDao.CREATE_TABLE_POSITION;

public class DatabaseHelper extends SQLiteOpenHelper {

    //khóa học

    //tên bảng
    public static final String TABLE_COURSE = "Khoahoc" ;

    //tên cột
    public static final String COURSE_NAME = "TenKhoaHoc";
    public static final String COURSE_CONTENT = "NDKhoaHoc";
    public static final String COURSE_START = "NgayBatDau";
    public static final String COURSE_END = "NgayKetThuc";
    public static final String COURSE_FEE = "HocPhi";


    //câu lệnh tạo bảng
    public static final String CREATE_COURSE_TABLE
            = "CREATE TABLE " + TABLE_COURSE + "(" +
            "" + COURSE_NAME + " TEXT PRIMARY KEY," +
            "" + COURSE_CONTENT + " TEXT," +
            "" + COURSE_START + " TEXT," +
            "" + COURSE_END + " TEXT," +
            "" + COURSE_FEE + " TEXT" +
            ")";


    //khóa học đã đăng ký

    //tên bảng
    public static final String TABLE_REGISTED = "KhoaHocRegisted" ;

    //tên cột
    public static final String NAME_REGISTED = "TenkhoahocRegisted";
    public static final String CONTENT_REGISTED = "NDkhoahocRegisted";

    //tạo bảng
    public static final String CREATE_TABLE_REGISTED
            = "CREATE TABLE " + TABLE_REGISTED + "(" +
            "" + NAME_REGISTED + " TEXT PRIMARY KEY," +
            "" + CONTENT_REGISTED + " TEXT" +
            ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Khoahoc.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_COURSE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE_REGISTED);
        sqLiteDatabase.execSQL(CREATE_TABLE_POSITION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTED);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "Position");
        onCreate(sqLiteDatabase);
    }
}
