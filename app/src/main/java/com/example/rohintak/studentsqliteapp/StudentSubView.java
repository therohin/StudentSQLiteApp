package com.example.rohintak.studentsqliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class StudentSubView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sub_view);
        Intent intent = getIntent();
        String rollNo = intent.getStringExtra("rollNo");
        DBDude dbDude = new DBDude(this);
        setTitle("Student Details: "+rollNo);
        TextView txtstudentDetail = (TextView) findViewById(R.id.textStudentDetails);

        //get User details from DB
        Student student = dbDude.getStudent(rollNo);

        StringBuffer buffer=new StringBuffer();
        buffer.append("Rollno: "+student.ROLLNO+"\n");
        buffer.append("Name: "+student.NAME+"\n");
        buffer.append("Marks: "+student.MARKS);

        //fill textView with Student Details
        txtstudentDetail.setText(buffer);
    }
}
