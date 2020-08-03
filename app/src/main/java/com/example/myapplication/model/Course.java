package com.example.myapplication.model;

public class Course {

    public String nameCourse ;
    public String contentCourse;
    public String startCourse ;
    public String endCourse ;
    public String feeCourse ;

    public Course() {

    }

    public Course(String nameCourse, String contentCourse, String startCourse, String endCourse, String feeCourse) {
        this.nameCourse = nameCourse;
        this.contentCourse = contentCourse;
        this.startCourse = startCourse;
        this.endCourse = endCourse;
        this.feeCourse = feeCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getContentCourse() {
        return contentCourse;
    }

    public void setContentCourse(String contentCourse) {
        this.contentCourse = contentCourse;
    }

    public String getStartCourse() {
        return startCourse;
    }

    public void setStartCourse(String startCourse) {
        this.startCourse = startCourse;
    }

    public String getEndCourse() {
        return endCourse;
    }

    public void setEndCourse(String endCourse) {
        this.endCourse = endCourse;
    }

    public String getFeeCourse() {
        return feeCourse;
    }

    public void setFeeCourse(String feeCourse) {
        this.feeCourse = feeCourse;
    }
}
