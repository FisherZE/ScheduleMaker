import java.time.DayOfWeek;

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

    public boolean getSemesterHalf(){
        return this.semesterHalf;
    }

    public ArrayList<DayOfWeek> onDays(){
        return this.onDays;
    }

    public String getIdentifier(){
        return this.seminarId + this.secId;
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
}