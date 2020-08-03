package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.Course;


import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.database.DatabaseHelper.COURSE_CONTENT;
import static com.example.myapplication.database.DatabaseHelper.COURSE_END;
import static com.example.myapplication.database.DatabaseHelper.COURSE_FEE;
import static com.example.myapplication.database.DatabaseHelper.COURSE_NAME;
import static com.example.myapplication.database.DatabaseHelper.COURSE_START;
import static com.example.myapplication.database.DatabaseHelper.TABLE_COURSE;

public class CourseDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public CourseDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public long insertCourse(Course course) {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_NAME, course.nameCourse);
        contentValues.put(COURSE_CONTENT, course.contentCourse);
        contentValues.put(COURSE_START, course.startCourse);
        contentValues.put(COURSE_END, course.endCourse);
        contentValues.put(COURSE_FEE, course.feeCourse);
        long result = sqLiteDatabase.insert(TABLE_COURSE, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public List<Course> getAllCourse() {
        List<Course> courseList = new ArrayList<>();
        courseList.clear();
        String query = "SELECT * FROM " + TABLE_COURSE;
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String NAME_ = cursor.getString(cursor.getColumnIndex(COURSE_NAME));
                    String CONTENT_ = cursor.getString(cursor.getColumnIndex(COURSE_CONTENT));
                    String START_ = cursor.getString(cursor.getColumnIndex(COURSE_START));
                    String END_ = cursor.getString(cursor.getColumnIndex(COURSE_END));
                    String FEE_ = cursor.getString(cursor.getColumnIndex(COURSE_FEE));
                    Course course = new Course(NAME_,CONTENT_, START_,END_,FEE_);
                    courseList.add(course);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return courseList;
    }
}
