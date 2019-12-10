package com.jeffreylo.android.assn2_studentcourse.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudentDB {
//    private static final StudentDB mInstance = new StudentDB();

    protected ArrayList<Student> mStudents;
    public SQLiteDatabase mSQLiteDatabase;

    public StudentDB(Context ctxt) {
        File dbFile = ctxt.getDatabasePath("Student.db");
        // Create or open db file - Requirement 2
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);

//        mSQLiteDatabase.execSQL("DROP TABLE IF EXISTS Student");
//        mSQLiteDatabase.execSQL("DROP TABLE IF EXISTS CourseEnrollment");


        // Create Tables if dont exist - Requirement 1 and 2
        new Student().createTable(mSQLiteDatabase);
        new CourseEnrollment().createTable(mSQLiteDatabase);
    }

//    public static StudentDB getInstance() {
//        return mInstance;
//    }

    public ArrayList<Student> getStudents() {
        return mStudents;
    }

    public void setStudents(ArrayList<Student> students) {
        mStudents = students;
    }

    public ArrayList<Student> retriveStudentObjects() {
        mStudents = new ArrayList<>();
        Cursor c = mSQLiteDatabase.query("Student", null, null, null, null, null, null);

        if (c.getCount() > 0) {
            Log.d("RetrieveStudentObjects", "count: "+Integer.toString(c.getCount()));
            while(c.moveToNext()) {
                Student s = new Student();
                s.initFrom(mSQLiteDatabase, c);
                Log.d("RetrieveStudentObjects", "student courses: "+Integer.toString(s.mCourses.size()));
                mStudents.add(s);
            }
        }

        return mStudents;
    }

    public void createSampleStudentObjs() {
        int s1CWID = 12345678;
        Student s1 = new Student("Jeffrey", "Lo", s1CWID);
        ArrayList<CourseEnrollment> courses = new ArrayList<>();
        courses.add(new CourseEnrollment("CPSC-411","A", s1CWID));
        courses.add(new CourseEnrollment("CPSC-483","C", s1CWID));
        courses.add(new CourseEnrollment("CPSC-386","B", s1CWID));
        s1.setCourses(courses);
        s1.insert(mSQLiteDatabase);

        int s2CWID = 10000000;
        Student s2 = new Student("John", "Smithy", s2CWID);
        courses = new ArrayList<>();
        courses.add(new CourseEnrollment("CPSC-411","B", s2CWID));
        courses.add(new CourseEnrollment("CPSC-483","C", s2CWID));
        courses.add(new CourseEnrollment("CPSC-386","D", s2CWID));
        s2.setCourses(courses);
        s2.insert(mSQLiteDatabase);

        mStudents = new ArrayList<>();
        mStudents.add(s1);
        mStudents.add(s2);
    }
}
