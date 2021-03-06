package com.example.teacherreminder.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.teacherreminder.model.CourseListener;

import java.util.ArrayList;


public class MyDatabase {

    private static final String DATABASE_NAME = "itschool.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "LISTENERS";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_INFO = "info";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_NAME = 1;
    private static final int NUM_COLUMN_LOGIN = 2;
    private static final int NUM_COLUMN_EMAIL = 3;
    private static final int NUM_COLUMN_INFO = 4;

    private SQLiteDatabase mDataBase;

    public MyDatabase(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public long insert(String name, String login, String email, String info) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_LOGIN, login);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_INFO, info);
        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    public ArrayList<CourseListener> selectAll() {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<CourseListener> arr = new ArrayList<>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                String name = mCursor.getString(NUM_COLUMN_NAME);
                String login = mCursor.getString(NUM_COLUMN_LOGIN);
                String email = mCursor.getString(NUM_COLUMN_EMAIL);
                String info = mCursor.getString(NUM_COLUMN_INFO);
                arr.add(new CourseListener(id, name, login, email, info));
            } while (mCursor.moveToNext());
        }
        return arr;
    }

    private class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_LOGIN + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_INFO + " TEXT); ";
            db.execSQL(query);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
