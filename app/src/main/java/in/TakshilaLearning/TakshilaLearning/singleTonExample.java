package in.TakshilaLearning.TakshilaLearning;

public class singleTonExample {
    String editValue;
    private static final singleTonExample ourInstance = new singleTonExample();
    public static singleTonExample getInstance() {
        return ourInstance;
    }
    private singleTonExample() { }
    public void setText(String editValue) {
        this.editValue = editValue;
    }
    public String getText() {
        return editValue;
    }
}