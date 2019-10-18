package com.jeffreylo.android.assn2_studentcourse;

import android.os.Bundle;
import android.widget.EditText;

import com.jeffreylo.android.assn2_studentcourse.model.Student;
import com.jeffreylo.android.assn2_studentcourse.model.StudentDB;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_detail);

        int studInd = getIntent().getIntExtra("StudentIndex", 0);

        Student studObj = StudentDB.getInstance().getStudents().get(studInd);
        EditText fNameView = (EditText) findViewById(R.id.first_name_val_id);
        EditText lNameView = (EditText) findViewById(R.id.last_name_val_id);
        fNameView.setText(studObj.getFirstName());
        lNameView.setText(studObj.getLastName());
    }
}
