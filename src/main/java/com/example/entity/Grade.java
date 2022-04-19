package com.example.entity;

public class Grade {
    private int id;
    private int student_id;
    private int subject_id;
    private int year;
    private String term;
    private int exam_type;
    private float grade;
    private int ranking;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getExam_type() {
        return exam_type;
    }

    public void setExam_type(int exam_type) {
        this.exam_type = exam_type;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", subject_id=" + subject_id +
                ", year=" + year +
                ", term='" + term + '\'' +
                ", exam_type=" + exam_type +
                ", grade=" + grade +
                ", ranking=" + ranking +
                '}';
    }
}
