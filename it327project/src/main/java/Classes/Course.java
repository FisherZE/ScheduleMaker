package Classes;

import java.time.DayOfWeek;
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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

    public ArrayList<DayOfWeek> getOnDays() {
        return onDays;
    }

    public void setOnDays(ArrayList<DayOfWeek> onDays) {
        this.onDays = onDays;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getIdentifier(){
        return this.courseId + this.secId;
    }
}