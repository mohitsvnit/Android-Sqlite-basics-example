package com.example.mohit.androidsqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohit on 22/5/16.
 */
public class Studentdb extends SQLiteOpenHelper {

    public static final String DataBase_Name = "Studentdb.db";
    public static final String Table_Name = "Student";
    public static final String Col_Id = "StudentId";
    public static final String Col_First_Name = "FirstName";
    public static final String Col_Last_Name = "LastName";

    public Studentdb(Context context) {

        super(context, DataBase_Name, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "Create Table " + Table_Name + "(" +
                Col_Id + " integer primary key," +
                Col_First_Name + " text," +
                Col_Last_Name + " text)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db = this.getReadableDatabase();
        String sql = "Drop table if exist " + Table_Name;
        db.execSQL(sql);

    }

    public void addStudent(Student student){
        SQLiteDatabase db = this.getWriteableDatabase();
        ContentValues values = new ContentValues();

        values.put(Col_Id,student.getId());
        values.put(Col_First_Name,student.getFname());
        values.put(Col_Last_Name,student.getLname());

        db.insert(Table_Name,null,values);
        //Toast.makeText(g,"adapter Updated",Toast.LENGTH_SHORT);
        db.close();
    }

    public List<Student> getAllStudent(){
        List<Student> data = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + Table_Name;
        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                Student tmp = new Student(cursor.getString(1),cursor.getString(2),cursor.getInt(0));
                data.add(tmp);
            }while (cursor.moveToNext());
        };


        return data;
    }

    public void clearall(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "Delete from " + Table_Name;
        db.execSQL(sql);
        db.close();
    }
}
