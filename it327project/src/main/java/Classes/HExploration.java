package Classes;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class HExploration extends Class{
    private String explorationId;
    private ArrayList<String> meetingDays;

    public HExploration(String explorationId, ArrayList<String> meetingDays, ArrayList<DayOfWeek> onDays, int startTime, int endTime, int creditHours, String classType){
        super(onDays, startTime, endTime, creditHours, classType);
        this.explorationId = explorationId;
        this.meetingDays = meetingDays;
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

}