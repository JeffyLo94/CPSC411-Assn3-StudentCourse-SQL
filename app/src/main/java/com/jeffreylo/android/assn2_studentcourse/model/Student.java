package com.jeffreylo.android.assn2_studentcourse.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Student extends PersistentObject {
    protected String mFirstName;
    protected String mLastName;
    protected int mCWID;
    protected ArrayList<CourseEnrollment> mCourses;

    public Student() {
        mFirstName = "";
        mLastName = "";
        mCWID = -1;
        mCourses = new ArrayList<>();
    }

    public Student(String firstName, String lastName, int CWID) {
        mFirstName = firstName;
        mLastName = lastName;
        mCWID = CWID;
        mCourses = new ArrayList<>();
    }

    public void set(String firstName, String lastName, int CWID) {
        mFirstName = firstName;
        mLastName = lastName;
        mCWID = CWID;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public int getCWID() {
        return mCWID;
    }

    public void setCWID(int CWID) {
        mCWID = CWID;
    }

    public ArrayList<CourseEnrollment> retriveCoursesFromDB(SQLiteDatabase db) {
        mCourses = new ArrayList<>();
        Cursor cursor = db.query("CourseEnrollment", null, "CWID=?", new String[]{new Integer(mCWID).toString()}, null, null, null);
        if (cursor.getCount() >0) {
            while (cursor.moveToNext()) {
                CourseEnrollment vObj = new CourseEnrollment();
                vObj.initFrom(db, cursor);
            }
        }
        return mCourses;
    }

    public ArrayList<CourseEnrollment> getCourses() {

        return mCourses;
    }

    public void setCourses(ArrayList<CourseEnrollment> courses) {
        mCourses = courses;
    }

    public void addCourse( CourseEnrollment c ) {
        mCourses.add(c);
    }

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("FirstName", mFirstName);
        vals.put("LastName", mLastName);
        vals.put("CWID", mCWID);
        // make cwid unique by deleting
        db.execSQL("DELETE FROM Student WHERE CWID=?", new String[]{Integer.toString(mCWID)});
        db.insert("Student", null, vals);
        Log.d("Student", "Inserting courses to db: "+Integer.toString(mCourses.size()));

        for (int i=0; i < mCourses.size(); i++) {
            mCourses.get(i).insert(db);
        }
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Student (FirstName Text, LastName Text, CWID INTEGER)");
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor c) {
        mFirstName = c.getString(c.getColumnIndex("FirstName"));
        mLastName = c.getString(c.getColumnIndex("LastName"));
        mCWID = c.getInt(c.getColumnIndex("CWID"));

        // construct the courses objects owned by the person
        mCourses = new ArrayList<>();
        Cursor cursor = db.query("CourseEnrollment", null, "CWID=?", new String[]{Integer.toString(mCWID)}, null, null, null);
        if (cursor.getCount() >0) {
            Log.d("Student", "course count: "+Integer.toString(c.getCount()));

            while (cursor.moveToNext()) {
                CourseEnrollment cObj = new CourseEnrollment();
                cObj.initFrom(db, cursor);
            }
        }
    }
}
