package com.jeffreylo.android.assn2_studentcourse.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CourseEnrollment extends PersistentObject {
    protected String mCourseID;
    protected String mGrade;
    protected int mCWID;

    public CourseEnrollment() {}

    public CourseEnrollment(String courseID, String grade, int CWID) {
        mCourseID = courseID;
        mGrade = grade;
        mCWID = CWID;
    }

    public String getCourseID() {
        return mCourseID;
    }

    public void setCourseID(String courseID) {
        mCourseID = courseID;
    }

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }

    public int getCWID() {
        return mCWID;
    }

    public void setCWID(int CWID) {
        mCWID = CWID;
    }


    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues v = new ContentValues();
        v.put("CourseID", mCourseID);
        v.put("Grade", mGrade);
        v.put("CWID", mCWID);
        db.execSQL("DELETE FROM CourseEnrollment WHERE CourseID=? AND CWID=?", new String[]{ mCourseID, Integer.toString(mCWID)});
        db.insert("CourseEnrollment", null, v);
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS CourseEnrollment (CourseID Text, Grade Text, CWID INTEGER)");
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor c) {
        mCourseID = c.getString(c.getColumnIndex("CourseID"));
        mGrade = c.getString(c.getColumnIndex("Grade"));
        mCWID = c.getInt(c.getColumnIndex("CWID"));
    }
}
