package in.TakshilaLearning.TakshilaLearning.CoursesForSale;

import java.util.ArrayList;


public class RvCourseForSaleContentPojo {
    private String instructor;
    private String Description;
    private String price;
    private String DemoUrl;
    private String material;

    private ArrayList<RvCourseForSaleContentPojo> SalePojo =new ArrayList<>();
    public RvCourseForSaleContentPojo() {
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDescription() {
        return Description;
    }

    public String getPrice() {
        return price;
    }

    public String getDemoUrl() {
        return DemoUrl;
    }

}