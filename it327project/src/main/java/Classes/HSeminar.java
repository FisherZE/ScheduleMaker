package Classes;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class HSeminar extends Class{
    private String seminarId;
    private String secId;
    private boolean semesterHalf;
    private ArrayList<DayOfWeek> onDays;
    private int startTime;
    private int endTime;
    private int creditHours;
    private String classType;

    public HSeminar(String seminarId, String secId, boolean semesterHalf, ArrayList<DayOfWeek> onDays, int startTime, int endTime, int creditHours, String classType){
        this.seminarId = seminarId;
        this.secId = secId;
        this.semesterHalf = semesterHalf;
        this.onDays = onDays;
        this.startTime = startTime;
        this.endTime = endTime;
        this.creditHours = creditHours;
        this.classType = classType;
    }

    public String getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(String seminarId) {
        this.seminarId = seminarId;
    }

    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

    public boolean isSemesterHalf() {
        return semesterHalf;
    }

    public void setSemesterHalf(boolean semesterHalf) {
        this.semesterHalf = semesterHalf;
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

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getIdentifier(){
        return this.seminarId + this.secId;
    }

}