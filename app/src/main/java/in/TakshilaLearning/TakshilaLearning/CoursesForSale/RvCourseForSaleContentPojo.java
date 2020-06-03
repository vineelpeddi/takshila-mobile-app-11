package in.TakshilaLearning.TakshilaLearning.CoursesForSale;

import java.util.ArrayList;


public class RvCourseForSaleContentPojo {
    private String instructor;
    private String description;
    private String price;
    private String demoUrl;
    private String material;

    private ArrayList<RvCourseForSaleContentPojo> SalePojo =new ArrayList<>();
    public RvCourseForSaleContentPojo() {
    }

    public String getInstructor() {
        return instructor;
    }
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) { this.description = description; }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) { this.price = price; }
    public String getDemoUrl() {
        return demoUrl;
    }
    public void  setDemoUrl(String demoUrl) { this.demoUrl = demoUrl;}
    public String getMaterial() { return  material; }
    public void setMaterial(String material) { this.material = material;}
}