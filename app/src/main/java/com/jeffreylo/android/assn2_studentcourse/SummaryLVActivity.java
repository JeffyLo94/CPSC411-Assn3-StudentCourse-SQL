package com.jeffreylo.android.assn2_studentcourse;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.jeffreylo.android.assn2_studentcourse.adapter.SummaryLVAdapter;
import com.jeffreylo.android.assn2_studentcourse.model.CourseEnrollment;
import com.jeffreylo.android.assn2_studentcourse.model.Student;
import com.jeffreylo.android.assn2_studentcourse.model.StudentDB;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class SummaryLVActivity extends Activity {
    protected ListView mSummaryView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sumary_listview);
        createStudents();
        mSummaryView = findViewById(R.id.summary_list_view_id);
        SummaryLVAdapter ad = new SummaryLVAdapter();
        mSummaryView.setAdapter(ad);
    }

    protected void createStudents() {
        Student s1 = new Student("Jeffrey", "Lo", 12345678);
        ArrayList<CourseEnrollment> courses = new ArrayList<>();
        courses.add(new CourseEnrollment("CPSC-411","A"));
        courses.add(new CourseEnrollment("CPSC-483","C"));
        courses.add(new CourseEnrollment("CPSC-386","B"));
        s1.setCourses(courses);

        Student s2 = new Student("John", "Smith", 00000001);
        courses = new ArrayList<>();
        courses.add(new CourseEnrollment("CPSC-411","B"));
        s2.setCourses(courses);

        Student s3 = new Student("Lena", "Turner", 00000002);
        courses = new ArrayList<>();
        courses.add(new CourseEnrollment("CPSC-411","C"));
        s3.setCourses(courses);

        ArrayList<Student> studList = new ArrayList<>();
        studList.add(s1);
        studList.add(s2);
        studList.add(s3);

        StudentDB.getInstance().setStudents(studList);
    }
}
