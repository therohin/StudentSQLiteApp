package com.example.rohintak.studentsqliteapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by rohin.tak on 10-Mar-17.
 */

public class DBDude extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "StudentDB.db";
    public static final String TABLE_NAME = "student";
    public static final String COLUMN_ROLLNO = "rollno";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MARKS = "marks";

    public SQLiteDatabase db = this.getWritableDatabase();

    public DBDude(Context context) {
        super(context, DATABASE_NAME, null, 1);
        }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR, name VARCHAR, marks VARCHAR);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS student");
        onCreate(db);
    }

    public boolean insertStudent( String rollno, String name, String marks)
    {
        db.execSQL("INSERT INTO student VALUES('"+rollno+"','"+name+
                "','"+marks+"');");
        return  true;
    }

    public boolean deleteStudent( String rollno)
    {
        Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+rollno+"'", null);
        if(c.moveToFirst())
        {
            db.execSQL("DELETE FROM student WHERE rollno='"+rollno+"'");
            return  true;
        }
        else
        {
            return false;
        }
    }

    public boolean updateStudent(String rollno, String name, String marks) {
        Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+rollno+"'", null);
        if(c.moveToFirst())
        {
            db.execSQL("UPDATE student SET name='"+name+"',marks='"+marks+
                "' WHERE rollno='"+rollno+"'");
            return true;
        }
        else
        {
            return  false;
        }
    }


    public Student getStudent(String rollno) {
        Student student = new Student();
        Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+rollno+"'", null);
        if(c.moveToFirst())
        {
            student.ROLLNO = c.getString(c.getColumnIndex(COLUMN_ROLLNO));
            student.NAME = c.getString(c.getColumnIndex(COLUMN_NAME));
            student.MARKS = c.getString(c.getColumnIndex(COLUMN_MARKS));
            return  student;
        }
        else
        {
            return null;
        }
    }

    public ArrayList<Student> getAllStudent() {
        ArrayList<Student> allStudent = new ArrayList<>();
        Cursor c=db.rawQuery("SELECT * FROM student", null);
        if(c.getCount()==0)
        {
            return null;
        }
        else
        {
            while(c.moveToNext()) {
                Student student = new Student();
                student.ROLLNO = c.getString(c.getColumnIndex(COLUMN_ROLLNO));
                student.NAME = c.getString(c.getColumnIndex(COLUMN_NAME));
                student.MARKS = c.getString(c.getColumnIndex(COLUMN_MARKS));
                allStudent.add(student);
            }
            return  allStudent;
        }
    }
}
