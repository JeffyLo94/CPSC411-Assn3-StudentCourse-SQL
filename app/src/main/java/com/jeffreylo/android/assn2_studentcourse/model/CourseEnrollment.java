package com.jeffreylo.android.assn2_studentcourse.model;

public class CourseEnrollment {
    private String mCourseID;
    private String mGrade;

    public CourseEnrollment(String courseID, String grade) {
        mCourseID = courseID;
        mGrade = grade;
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
}
