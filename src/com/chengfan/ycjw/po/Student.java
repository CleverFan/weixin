package com.chengfan.ycjw.po;

public class Student {
    private String userweinxinname;

    private String studentid;

    private String ycpassword;

    public String getUserweinxinname() {
        return userweinxinname;
    }

    public void setUserweinxinname(String userweinxinname) {
        this.userweinxinname = userweinxinname == null ? null : userweinxinname.trim();
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid == null ? null : studentid.trim();
    }

    public String getYcpassword() {
        return ycpassword;
    }

    public void setYcpassword(String ycpassword) {
        this.ycpassword = ycpassword == null ? null : ycpassword.trim();
    }
}