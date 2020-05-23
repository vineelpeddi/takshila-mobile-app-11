package in.TakshilaLearning.TakshilaLearning.CourseContentList;

import java.util.ArrayList;

public class RvCourseContentPojo {
    private String instructor;
    private String course;
    private String title;
    private ArrayList<RvCourseContentPojo> customPojo =new ArrayList<>();
    public RvCourseContentPojo() {

    }
    public String getInstructor() {
        return instructor;
    }
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
