package com.example.rohintak.studentsqliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editRollNo;
    EditText editName;
    EditText editMarks;
    Button btnAdd;
    Button btnDelete;
    Button btnUpdate;
    Button btnView;
    Button btnViewAll;
    DBDude dbDude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Welcome to Student System");
        //Initializing controls
        editRollNo = (EditText) findViewById(R.id.editRollNo);
        editName= (EditText) findViewById(R.id.editName);
        editMarks= (EditText) findViewById(R.id.editMarks);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnView = (Button) findViewById(R.id.btnView);
        btnViewAll = (Button) findViewById(R.id.btnViewAll);


        //Registering OnClickListeners

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);

        //Create DBHelper object
        dbDude = new DBDude(this);
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId()) {
            case R.id.btnAdd:
            {
                if(editRollNo.getText().toString().trim().length()==0||
                        editName.getText().toString().trim().length()==0||
                        editMarks.getText().toString().trim().length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Please fill all values",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                dbDude.insertStudent(editRollNo.getText().toString(),editName.getText().toString(),editMarks.getText().toString());
                Toast.makeText(getApplicationContext(), "Records successfully added",
                        Toast.LENGTH_LONG).show();
                clearText();
            }
                break;
            case R.id.btnDelete:
            {
                if(editRollNo.getText().toString().trim().length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Please enter RollNo",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                boolean result = dbDude.deleteStudent(editRollNo.getText().toString());
                if(result)
                {
                    Toast.makeText(getApplicationContext(), "Record deleted successfully",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "RollNo does not exist",
                            Toast.LENGTH_LONG).show();
                }
                clearText();
            }
                break;
            case R.id.btnUpdate:
            {
                if(editRollNo.getText().toString().trim().length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Please enter RollNo",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                boolean result = dbDude.updateStudent(editRollNo.getText().toString(),editName.getText().toString(),editMarks.getText().toString());

                if(result)
                {
                      Toast.makeText(getApplicationContext(), "Record modified successfully",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "RollNo does not exist",
                            Toast.LENGTH_LONG).show();
                }
                clearText();
            }
                break;
            case R.id.btnView:
            {
                if(editRollNo.getText().toString().trim().length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Please enter RollNo",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Student studentDetails =  dbDude.getStudent(editRollNo.getText().toString());
                if(studentDetails!=null)
                {
                    // Displaying record if found
                    editName.setText(studentDetails.NAME);
                    editMarks.setText(studentDetails.MARKS);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "RollNo does not exist",
                            Toast.LENGTH_LONG).show();
                    clearText();
                }
            }
                break;
            case R.id.btnViewAll:
            {
                Intent intent = new Intent(this,ViewAll.class);
                startActivity(intent);
            }
                break;
        }
    }

    private void clearText() {
        editRollNo.setText("");
        editName.setText("");
        editMarks.setText("");
        editRollNo.requestFocus();
    }
}
