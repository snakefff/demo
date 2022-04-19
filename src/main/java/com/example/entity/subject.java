package com.example.entity;

public class subject {
    private int id;
    private String subject_name;
    private String english_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    @Override
    public String toString() {
        return "subject{" +
                "id=" + id +
                ", subject_name='" + subject_name + '\'' +
                ", english_name='" + english_name + '\'' +
                '}';
    }
}
