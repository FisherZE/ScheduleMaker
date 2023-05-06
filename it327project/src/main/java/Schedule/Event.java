package Schedule;
import java.util.ArrayList;
import java.time.DayOfWeek;

public class Event {
    private String name;
    private int startTime;
    private int endTime;
    private ArrayList<DayOfWeek> onDays;

    public Event(String n, int st, int et){
        name = n;
        startTime = st;
        endTime = et;
        onDays = new ArrayList<DayOfWeek>();
    }
    public int getStartTime(){
        return startTime;
    }
    public int getEndTime(){
        return endTime;

    }
    public String getName(){
        return name;
    }
    public ArrayList<DayOfWeek> getOnDays(){
        return onDays;
    }
    public void addOnDay(DayOfWeek day){
        onDays.add(day);
    }
}
