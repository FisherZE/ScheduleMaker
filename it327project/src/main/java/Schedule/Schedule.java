package Schedule;

import java.util.ArrayList;
import Classes.Class;
import GUI.ScheduleGUI;

public class Schedule{
 private ArrayList<Class> classes;
 private ArrayList<Event> events;
 private int credithours;

    public Schedule(){
        classes = new ArrayList<Class>();
        events = new ArrayList<Event>();
    }

    public void display(){
        ScheduleGUI gui = new ScheduleGUI(this);
    }

    public ArrayList<Class> getClasses(){
        return classes;
    }
    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }
    public ArrayList<Event> getEvents() {
        return events;
    }
    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
    public int getCredithours() {
        return credithours;
    }
    public void setCredithours(int credithours) {
        this.credithours = credithours;
    }

    
}
