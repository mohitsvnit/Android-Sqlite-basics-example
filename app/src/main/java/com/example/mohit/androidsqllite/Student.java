package com.example.mohit.androidsqllite;

/**
 * Created by mohit on 22/5/16.
 */
public class Student {
    private String fname;
    private String lname;
    private int id;

    Student(String fname,String lname,int id){
        this.fname = fname;
        this.lname = lname;
        this.id = id;
    }

    public String getFname(){
        return this.fname;
    }

    public String getLname(){
        return this.lname;
    }

    public int getId(){
        return this.id;
    }

    public void setFname(String fname){
        this.fname = fname;
    }

    public void setLname(String lname){
        this.lname = lname;
    }

    public void setId(int id){
        this.id = id;
    }
}
