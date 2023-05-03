package Classes;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class HExploration extends Class{
    private String explorationId;
    private ArrayList<String> meetingDays;
    private ArrayList<DayOfWeek> onDays;
    private int startTime;
    private int endTime;
    private int creditHours;
    private String classType;

    public HExploration(String explorationId, ArrayList<String> meetingDays, ArrayList<DayOfWeek> onDays, int startTime, int endTime, int creditHours, String classType){
        this.explorationId = explorationId;
        this.meetingDays = meetingDays;
        this.onDays = onDays;
        this.startTime = startTime;
        this.endTime = endTime;
        this.creditHours = creditHours;
        this.classType = classType;
    }

    @Override
    public String getIdentifier(){
        return this.explorationId;
    }

    public String getExplorationId() {
        return explorationId;
    }

    public void setExplorationId(String explorationId) {
        this.explorationId = explorationId;
    }

    public ArrayList<String> getMeetingDays() {
        return meetingDays;
    }

    public void setMeetingDays(ArrayList<String> meetingDays) {
        this.meetingDays = meetingDays;
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

}