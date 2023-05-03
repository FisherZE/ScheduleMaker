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

    public String getExplorationId(){
        return this.explorationId;
    }

    public ArrayList<String> getMettingDays(){
        return this.meetingDays;
    }

    public ArrayList<DayOfWeek> getOnDays(){
        return this.onDays;
    }

    public int getStartTime(){
        return this.startTime;
    }

    public int getEndTime(){
        return this.endTime;
    }

    public int getCreditHours(){
        return this.creditHours;
    }

    public String getClassType(){
        return this.classType;
    }
}