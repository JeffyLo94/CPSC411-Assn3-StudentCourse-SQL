package com.jeffreylo.android.assn2_studentcourse.model;

import java.util.ArrayList;

public class StudentDB {
    private static final StudentDB mInstance = new StudentDB();

    protected ArrayList<Student> mStudents;

    public static StudentDB getInstance() {
        return mInstance;
    }

    public ArrayList<Student> getStudents() {
        return mStudents;
    }

    public void setStudents(ArrayList<Student> students) {
        mStudents = students;
    }
}
