package isu.schedule;

import java.time.DayOfWeek;
import java.util.ArrayList;
import Classes.Class;
import Classes.Course;
import GUI.HomeGUI;
import Schedule.Event;
import Schedule.Schedule;
import Schedule.ScheduleMaker;

import org.junit.Test;

public class FullUITest {
    @Test
    public void testFull(){
        ScheduleMaker.initialize();
        Class class3 = new Course("IT 351", "001", new ArrayList<DayOfWeek>(){{add(DayOfWeek.FRIDAY);}}, 9, 10, 2, "Undergrad", "Course");
        Class class1 = new Course("IT 351", "002", new ArrayList<DayOfWeek>(){{add(DayOfWeek.MONDAY);add(DayOfWeek.WEDNESDAY);}}, 10, 11, 3, "Undergrad", "Course");
        Class class2 = new Course("IT 326", "002", new ArrayList<DayOfWeek>(){{add(DayOfWeek.TUESDAY);add(DayOfWeek.THURSDAY);}}, 11, 12, 4, "Undergrad", "Course");
        Class class4 = new Course("IT 353", "002", new ArrayList<DayOfWeek>(){{add(DayOfWeek.WEDNESDAY);add(DayOfWeek.FRIDAY);}}, 14, 15, 3, "Undergrad", "Course");
        Class class5 = new Course("IT 378", "002", new ArrayList<DayOfWeek>(){{add(DayOfWeek.MONDAY);add(DayOfWeek.THURSDAY);}}, 13, 14, 4, "Undergrad", "Course");
        ArrayList<Class> classes = new ArrayList<>();
        classes.add(class1);
        classes.add(class2);
        classes.add(class3);
        classes.add(class4);
        classes.add(class5);
        Event event = new Event("Die", 12, 13);
        event.addOnDay(DayOfWeek.MONDAY);
        ArrayList<Event> events = new ArrayList<>();
        events.add(event);
        ArrayList<Schedule> scheds = new ArrayList<>();
        Schedule sched = new Schedule();
        scheds.add(sched);
        sched.setEvents(events);
        sched.setClasses(classes);
        ScheduleMaker.setScheduleList(scheds);
        HomeGUI gui = new HomeGUI();
        System.out.println("Hi");
    }
}
