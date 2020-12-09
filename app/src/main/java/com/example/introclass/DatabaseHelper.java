package com.example.introclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME1 = "USERS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_PHONE = "PHONE";

    public static final String DB_NAME = "user.db";
    public static final int DB_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME1 + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_EMAIL + " TEXT, " + COLUMN_PHONE + " TEXT)";
        db.execSQL(createTable);
    }

    public boolean addUser(UserModel user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL, user.getEmail());
        cv.put(COLUMN_PHONE, user.getPhone());

        long insert = db.insert(TABLE_NAME1, null, cv);
        if(insert == -1){
            return false;
        }
        db.close();
        return true;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<UserModel> getUsers(){
        List<UserModel> userList = new ArrayList<>();

        String userListQuery = String.format("SELECT * FROM %s", TABLE_NAME1);
//        String userListQuery = "SELECT * from " + TABLE_NAME1 + " WHERE " + COLUMN_ID + " > ?";
        SQLiteDatabase db = getReadableDatabase();
//        String[] values = {"1"};
        Cursor cursor = db.rawQuery(userListQuery, null);

        if(cursor.moveToFirst()){
            do{
                int ID = cursor.getInt(0);
                String email = cursor.getString(1);
                String phone = cursor.getString(2);

                UserModel currentUser = new UserModel(ID, email, phone);
                userList.add(currentUser);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;
    }

    public boolean deleteUser(int ID){
        String deleteQuery = "DELETE FROM " + TABLE_NAME1 + " WHERE " + COLUMN_ID + " = ?";
        SQLiteDatabase db = getWritableDatabase();
//        String[]
        String[] values = {String.valueOf(ID)};
        Cursor cursor = db.rawQuery(deleteQuery, values);
        return cursor.moveToFirst();
    }
}
