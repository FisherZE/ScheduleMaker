import java.util.ArrayList;

public class Course extends Class{
    private String courseId;
    private String secId;
    private ArrayList<DayOfWeek> onDays;
    private int startTime;
    private int endTime;
    private int creditHours;
    private String courseType;
    private String classType;

    public Course(String courseId, String secId, ArrayList<DayOfWeek> onDays, int startTime, int endTime, int creditHours, String courseType, String classType){
        this.courseId = courseId;
        this.secId = secId;
        this.onDays = onDays;
        this.startTime = startTime;
        this.endTime = endTime;
        this.creditHours = creditHours;
        this.courseId = courseId;
        this.classType = classType;
    }

    public ArrayList<DayOfWeek> getOnDays(){
        return onDays;
    }

    public String getIdentifier(){
        return courseId;
    }

    public int getStartTime(){
        return startTime;
    }

    public int getEndTime(){
        return endTime;

    }

    public int getCreditHours(){
        return creditHours;

    }
}