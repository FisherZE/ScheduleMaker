package Schedule;

import java.util.ArrayList;
import Classes.Class;
public class Schedule{
 private ArrayList<Class> classes;
 private ArrayList<Event> events;
 private int credithours;
    public Schedule(){
        classes = new ArrayList<Class>();
    }
    public ArrayList<Class> getClasses(){
        return classes;
    }
}