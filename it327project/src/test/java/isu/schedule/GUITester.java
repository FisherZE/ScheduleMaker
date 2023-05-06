package isu.schedule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.util.ArrayList;

import Classes.Class;
import Classes.Course;
import Schedule.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import GUI.ConfirmGUI;
import GUI.ExportGUI;
import GUI.HomeGUI;
import GUI.ScheduleListGUI;

public class GUITester {
    Schedule sched = new Schedule();

    @Test
    public void HomeGuiTest(){
        HomeGUI gui = new HomeGUI();
        assertTrue(true);
    }

    @Before
    public void setup(){
        Class class3 = new Course("IT351", "2", new ArrayList<DayOfWeek>(){{add(DayOfWeek.FRIDAY);}}, 9, 10, 2, "Undergrad", "Course");
        Class class1 = new Course("IT351", "2", new ArrayList<DayOfWeek>(){{add(DayOfWeek.MONDAY);add(DayOfWeek.WEDNESDAY);}}, 10, 11, 3, "Undergrad", "Course");
        Class class2 = new Course("IT351", "2", new ArrayList<DayOfWeek>(){{add(DayOfWeek.TUESDAY);add(DayOfWeek.THURSDAY);}}, 11, 12, 4, "Undergrad", "Course");
        Class class4 = new Course("IT351", "2", new ArrayList<DayOfWeek>(){{add(DayOfWeek.WEDNESDAY);add(DayOfWeek.FRIDAY);}}, 14, 15, 3, "Undergrad", "Course");
        Class class5 = new Course("IT351", "2", new ArrayList<DayOfWeek>(){{add(DayOfWeek.MONDAY);add(DayOfWeek.THURSDAY);}}, 13, 14, 4, "Undergrad", "Course");
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
        scheds.add(sched);
        sched.setEvents(events);
        sched.setClasses(classes);
        ScheduleMaker.setSchedules(scheds);
    }

    @Test
    public void ScheduleListTest(){
        HomeGUI hgui = new HomeGUI();
        ScheduleListGUI gui = new ScheduleListGUI();
    }

    @Test
    public void ScheduleDisplayTest(){
        sched.display();
    }

    @Test
    public void ExportGUITest(){
        ExportGUI gui = new ExportGUI(sched);
    }

    @Test
    public void ConfirmTest(){
        ConfirmGUI gui = new ConfirmGUI(new Course("IT342", "1", new ArrayList<DayOfWeek>(){{add(DayOfWeek.FRIDAY);}}, 7, 8, 3, "Undergrad", "Course"));
        System.out.print("");
    }

    @Test
    public void ConfirmEventTest(){
        ConfirmGUI gui2 = new ConfirmGUI(new Event("Basket", 11, 12));
        System.out.print("");
    }
}
