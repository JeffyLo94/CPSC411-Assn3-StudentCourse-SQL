package com.jeffreylo.android.assn2_studentcourse.adapter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.jeffreylo.android.assn2_studentcourse.R;
import com.jeffreylo.android.assn2_studentcourse.StudentDetailActivity;
import com.jeffreylo.android.assn2_studentcourse.model.CourseEnrollment;
import com.jeffreylo.android.assn2_studentcourse.model.Student;
import com.jeffreylo.android.assn2_studentcourse.model.StudentDB;

import java.util.ArrayList;

public class CoursesLVAdapter extends BaseAdapter {

    protected Student mStudent;
//    protected EditText courseView;
//    protected EditText gradeView;

    // Helper Class
    private class CustomWatcher implements TextWatcher
    {
        private CourseEnrollment item;
        private int pos;
        private boolean isCourseID;

        public CustomWatcher(CourseEnrollment item, int pos, boolean isCourseID){
            this.item = item;
            this.pos = pos;
            this.isCourseID = isCourseID;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2){
            Log.d("CourseLVAdapter", "text changed");

            if (isCourseID) {
                // update course
                getItem(pos).setCourseID(charSequence.toString());
            }
            else {
                // update grade
                getItem(pos).setGrade(charSequence.toString());
            }
        }

        @Override
        public void afterTextChanged(Editable editable){
        }
    }

    public CoursesLVAdapter(Student student) {
        mStudent = student;
//        ArrayList<CourseEnrollment> courses = mStudent.retriveCoursesFromDB(db);
//        Log.d("CourseLVAdapter", "courses: "+ Integer.toString(courses.size()));
    }

    public Student getStudent() {
        return mStudent;
    }

    @Override
    public int getCount() {
        return mStudent.getCourses().size();
    }

    @Override
    public CourseEnrollment getItem(int position) {
        return mStudent.getCourses().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row_view;

        if (convertView == null) {
            // cnt++;
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            row_view = inflater.inflate(R.layout.grade_row, parent, false);
        } else row_view = convertView;

        CourseEnrollment course = getItem(position);
        final int pos = position;

        final EditText courseView = (EditText) row_view.findViewById(R.id.course_id);
        final EditText gradeView = (EditText) row_view.findViewById(R.id.grade_id);

        CustomWatcher oldCourseWatcher = (CustomWatcher)courseView.getTag();
        if(oldCourseWatcher != null) {
            courseView.removeTextChangedListener(oldCourseWatcher);
        }
        CustomWatcher oldGradeWatcher = (CustomWatcher)gradeView.getTag();
        if(oldGradeWatcher != null) {
            gradeView.removeTextChangedListener(oldGradeWatcher);
        }


        courseView.setText(course.getCourseID());
        gradeView.setText(course.getGrade());
        row_view.setTag(new Integer(position));

        // Change Detectors
        CustomWatcher newCourseWatcher = new CustomWatcher(course, pos, true);
        courseView.setTag(newCourseWatcher);
        courseView.addTextChangedListener(newCourseWatcher);

        CustomWatcher newGradeWatcher = new CustomWatcher(course, pos, false);
        gradeView.setTag(newGradeWatcher);
        gradeView.addTextChangedListener(newGradeWatcher);



        return row_view;
    }
}

