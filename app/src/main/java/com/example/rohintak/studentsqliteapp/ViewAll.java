package com.example.rohintak.studentsqliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewAll extends AppCompatActivity {

    ListView listView;
    List<String> studentNameList;
    List<String> studentRollNoList;
    List<Student> allStudents;
    DBDude dbDude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        listView = (ListView) findViewById(R.id.listViewStudent);
        setTitle("List Of Students");

        //Create DBHelper object
        dbDude = new DBDude(this);

        allStudents = dbDude.getAllStudent();
        // Checking if no records found
       if(allStudents==null)
        {
            Toast.makeText(getApplicationContext(), "No records found",
                    Toast.LENGTH_LONG).show();
            return;
        }
        // Appending records to a string buffer
        studentNameList = new ArrayList<>();
        studentRollNoList = new ArrayList<>();

        for (Student s: allStudents) {
            studentNameList.add(s.NAME);
            studentRollNoList.add(s.ROLLNO);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                studentNameList );

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent studentIntent = new Intent(ViewAll.this, StudentSubView.class); // when a row is tapped, load SubView.class
                String rollNo = studentRollNoList.get(position); // get the value from employeIdArray which corrosponds to the 'position' of the selected row
                studentIntent.putExtra("rollNo", rollNo); // add rollNo to the Intent
                startActivity(studentIntent);

            }
        });
    }


}
