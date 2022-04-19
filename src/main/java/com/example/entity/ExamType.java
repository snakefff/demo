package com.example.entity;

public class ExamType {
    private int id;
    private String exam_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExam_type() {
        return exam_type;
    }

    public void setExam_type(String exam_type) {
        this.exam_type = exam_type;
    }

    @Override
    public String toString() {
        return "ExamType{" +
                "id=" + id +
                ", exam_type='" + exam_type + '\'' +
                '}';
    }
}
