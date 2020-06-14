package in.TakshilaLearning.TakshilaLearning.CoursesForSale;

import java.util.ArrayList;


public class RvCourseForSaleContentPojo{
    private String id;
    private String name;
    private String description;
    private String price;
//    private String demoUrl;
    private String teacher;

    private ArrayList<RvCourseForSaleContentPojo> SalePojo =new ArrayList<>();
    public RvCourseForSaleContentPojo() {
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name ) { this.name = name; }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) { this.description = description; }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) { this.price = price; }

  //  public String getDemoUrl() { return demoUrl; }
    public String getTeacher() { return  teacher; }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}