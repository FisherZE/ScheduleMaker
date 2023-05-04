package Classes;

import java.time.DayOfWeek;
import java.util.ArrayList;

public abstract class Class{

    private ArrayList<DayOfWeek> onDays;
    private int startTime;
    private int endTime;
    private int creditHours;
    private String classType;

    public Class(ArrayList<DayOfWeek> days, int start, int end, int credit, String classType){
        this.onDays = days;
        this.startTime = start;
        this.endTime = end;
        this.creditHours = credit;
        this.classType = classType;
    }

    public abstract String getIdentifier();

    public ArrayList<DayOfWeek> getOnDays() {
        return onDays;
    }

    public void setOnDays(ArrayList<DayOfWeek> onDays) {
        this.onDays = onDays;
    }

    public void addDay(DayOfWeek day){
        this.onDays.add(day);
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

    
}
