package com.jeffreylo.android.assn2_studentcourse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeffreylo.android.assn2_studentcourse.model.CourseEnrollment;
import com.jeffreylo.android.assn2_studentcourse.model.Student;
import com.jeffreylo.android.assn2_studentcourse.model.StudentDB;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    protected LinearLayout root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createStudents();
        setContentView(R.layout.student_summary);

        root = findViewById(R.id.student_summary);

        ArrayList<Student> studList = StudentDB.getInstance().getStudents();
        for (int i=0; i<studList.size(); i++) {
            Student s = studList.get(i);
            LayoutInflater inflater = LayoutInflater.from(this);
            View row_view = inflater.inflate(R.layout.student_row, root, false);
            TextView firstNameView = (TextView) row_view.findViewById(R.id.first_name);
            firstNameView.setText(s.getFirstName());
            TextView lastNameView = (TextView) row_view.findViewById(R.id.last_name);
            lastNameView.setText(s.getLastName());
            root.addView(row_view);
        }

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
