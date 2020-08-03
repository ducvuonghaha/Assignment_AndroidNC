package com.example.myapplication.model;

public class RegistedCourse {

    public String registed;
    public String content;

    public RegistedCourse() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public RegistedCourse(String registed, String content) {
        this.registed = registed;
        this.content = content;
    }

    public String getRegisted() {
        return registed;
    }

    public void setRegisted(String registed) {
        this.registed = registed;

    }
}
