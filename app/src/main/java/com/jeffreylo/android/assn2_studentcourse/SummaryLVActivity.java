package com.jeffreylo.android.assn2_studentcourse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.jeffreylo.android.assn2_studentcourse.adapter.SummaryLVAdapter;
import com.jeffreylo.android.assn2_studentcourse.model.CourseEnrollment;
import com.jeffreylo.android.assn2_studentcourse.model.Student;
import com.jeffreylo.android.assn2_studentcourse.model.StudentDB;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class SummaryLVActivity extends Activity {
    protected ListView mSummaryView;
    protected Toolbar mToolbar;
    protected SummaryLVAdapter ad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sumary_listview);
//        createStudents();


        mToolbar = findViewById(R.id.toolbar);
        setActionBar(mToolbar);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setDisplayShowHomeEnabled(true);

        TextView tb_title = findViewById(R.id.title);
        tb_title.setText("Students List");
        Button addStudBtn = findViewById(R.id.add_btn);
        addStudBtn.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), StudentDetailActivity.class);
//                        intent.putExtra("StudentIndex", ((Integer)view.getTag()).intValue());
                        v.getContext().startActivity(intent);
                    }
                }
        );


        mSummaryView = findViewById(R.id.summary_list_view_id);
        ad = new SummaryLVAdapter(this);
        mSummaryView.setAdapter(ad);

    }

//    protected void createStudents() {
//        Student s1 = new Student("Jeffrey", "Lo", 12345678);
//        ArrayList<CourseEnrollment> courses = new ArrayList<>();
//        courses.add(new CourseEnrollment("CPSC-411","A"));
//        courses.add(new CourseEnrollment("CPSC-483","C"));
//        courses.add(new CourseEnrollment("CPSC-386","B"));
//        s1.setCourses(courses);
//
//        Student s2 = new Student("John", "Smith", 00000001);
//        courses = new ArrayList<>();
//        courses.add(new CourseEnrollment("CPSC-411","B"));
//        s2.setCourses(courses);
//
//        Student s3 = new Student("Lena", "Turner", 00000002);
//        courses = new ArrayList<>();
//        courses.add(new CourseEnrollment("CPSC-411","C"));
//        s3.setCourses(courses);
//
//        ArrayList<Student> studList = new ArrayList<>();
//        studList.add(s1);
//        studList.add(s2);
//        studList.add(s3);
//
//        StudentDB.getInstance().setStudents(studList);
//    }

    @Override
    protected void onStart() {
        Log.d("Summary Screen", "onStart() called");
        ad.notifyDataSetChanged();
        super.onStart();
    }
}
