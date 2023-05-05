package Classes;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class Course extends Class{
    private String courseId;
    private String secNo;
    private String courseType;

    public Course(String courseId, String secNo, ArrayList<DayOfWeek> onDays, int startTime, int endTime, int creditHours, String courseType, String classType){
        super(onDays, startTime, endTime, creditHours, classType);
        this.courseId = courseId;
        this.secNo = secNo;
        this.courseId = courseId;
    }

    @Override
    public String toString(){
        return this.courseId + " " + this.secNo;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getsecNo() {
        return secNo;
    }

    public void setsecNo(String secNo) {
        this.secNo = secNo;
    }
    
    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getIdentifier(){
        return this.courseId + this.secNo;
    }
}