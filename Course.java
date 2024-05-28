package application;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Course {
    private int courseId;
    private String coursePath;
    private String courseContent;

    public Course(int courseId, String coursePath, String courseContent) {
        this.courseId = courseId;
        this.coursePath = coursePath;
        this.courseContent = courseContent;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCoursePath() {
        return coursePath;
    }

    public void setCoursePath(String coursePath) {
        this.coursePath = coursePath;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }
}
