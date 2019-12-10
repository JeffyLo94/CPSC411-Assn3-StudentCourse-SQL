package com.jeffreylo.android.assn2_studentcourse;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.jeffreylo.android.assn2_studentcourse.adapter.CoursesLVAdapter;
import com.jeffreylo.android.assn2_studentcourse.adapter.SummaryLVAdapter;
import com.jeffreylo.android.assn2_studentcourse.model.CourseEnrollment;
import com.jeffreylo.android.assn2_studentcourse.model.Student;
import com.jeffreylo.android.assn2_studentcourse.model.StudentDB;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailActivity extends AppCompatActivity {
    protected Toolbar mToolbar;
    protected ListView mCourses;
    protected Student studObj;
    protected CoursesLVAdapter ad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int studInd = getIntent().getIntExtra("StudentIndex", -1);

        setContentView(R.layout.activity_student_detail);
        final EditText fNameView = (EditText) findViewById(R.id.first_name_val_id);
        final EditText lNameView = (EditText) findViewById(R.id.last_name_val_id);
        final EditText cwidView = (EditText) findViewById(R.id.cwid_val_id);

        mToolbar = findViewById(R.id.toolbar);
        setActionBar(mToolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        TextView tb_title = findViewById(R.id.title);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish(); // close this activity and return to preview activity (if there is any)
            }
        });

        final StudentDB studDB = new StudentDB(this);
        ArrayList<Student> studs = studDB.retriveStudentObjects();

        Log.d("Student Detail Activity", "studIndex "+Integer.toString(studInd));

        if (studInd >= 0){
            tb_title.setText("Edit Student");
            studObj = studs.get(studInd);
            Log.d("Student Detail Activity", "student object: "+studObj.getFirstName());
            Log.d("Student Detail Activity", "student object courses: "+Integer.toString(studObj.retriveCoursesFromDB(studDB.mSQLiteDatabase).size()));


        } else {
            tb_title.setText("Add Student");
            studObj = new Student();
            CourseEnrollment c = new CourseEnrollment("","",studObj.getCWID());
            studObj.addCourse(c);
        }

        // Set Course Info
        mCourses = findViewById(R.id.courses_list);
        ad = new CoursesLVAdapter(studObj, studDB.mSQLiteDatabase);
        mCourses.setAdapter(ad);
        Log.d("Student Detail Activity", "courses:"+Integer.toString(ad.getCount()));


        // Course Button
        Button add_course_btn = findViewById(R.id.add_course_btn);
        add_course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studObj.addCourse(new CourseEnrollment("","", studObj.getCWID()));
                Log.d("Student Detail Activity", "added a course, total: "+studObj.getCourses().size());
                ad.notifyDataSetChanged();
            }
        });

        // Set student info
        if (studInd < 0) {
            fNameView.setText("");
            lNameView.setText("");
            cwidView.setText("");
        } else {

            fNameView.setText(studObj.getFirstName());
            lNameView.setText(studObj.getLastName());
            cwidView.setText(Integer.toString(studObj.getCWID()));
        }

        // DONE BUTTON
        Button done_btn = findViewById(R.id.add_btn);
        done_btn.setText("Done");
        done_btn.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (studInd < 0) {

                        // Add new Student
                        String fName = fNameView.getText().toString();
                        String lName = lNameView.getText().toString();
                        int cwid = Integer.parseInt(cwidView.getText().toString());
                        studObj.set(fName, lName, cwid);

                        ad.saveCoursesToDB();

                        studObj.insert(studDB.mSQLiteDatabase);
                        // Add Courses
                        // may already be handled by adapter

                        // Add Student to DB
//                        ArrayList<Student> studList = studDB.retriveStudentObjects();

                    } else {
                        // Update existing Student
                        String fName = fNameView.getText().toString();
                        String lName = lNameView.getText().toString();
                        int cwid = Integer.parseInt(cwidView.getText().toString());
                        studObj.set(fName, lName, cwid);

                        // Add Courses
//                        ad.saveCoursesToDB();
                        // may already be handled by adapter

                        // Update Student in DB
                        studObj.insert(studDB.mSQLiteDatabase);
//                        ArrayList<Student> studList = studDB.retriveStudentObjects();
                    }

                    // Finish Activity
                    onBackPressed();
                    finish();
                }
            }
        );





    }

}
