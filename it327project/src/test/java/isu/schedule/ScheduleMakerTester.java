import java.time.DayOfWeek;
import java.util.ArrayList;
import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Classes.Class;
import Classes.ClassFactory;
import Schedule.*;

public class ScheduleMakerTester{
    ArrayList<Class> courseLog;
    static ArrayList<DayOfWeek> MWF;
    static ArrayList<DayOfWeek> MW;
    static ArrayList<DayOfWeek> TTh;
       
    @Before
    public void startUp(){
        ScheduleMaker tony = new ScheduleMaker();
        MWF = new ArrayList<DayOfWeek>();
        MWF.add(DayOfWeek.MONDAY);
        MWF.add(DayOfWeek.WEDNESDAY);
        MWF.add(DayOfWeek.FRIDAY);

        MW = new ArrayList<DayOfWeek>();
        MW.add(DayOfWeek.MONDAY);
        MW.add(DayOfWeek.WEDNESDAY);

        TTh = new ArrayList<DayOfWeek>();
        TTh.add(DayOfWeek.TUESDAY);
        TTh.add(DayOfWeek.THURSDAY);

        courseLog = new ArrayList<Class>();
        courseLog.add(ClassFactory.createCourse("IT168", "1",MWF, 900, 950,3,"Undergraduate","Course"));
        courseLog.add(ClassFactory.createCourse("ENG124", "2",MW, 1400, 1515,3,"Undergraduate","Course"));
        courseLog.add(ClassFactory.createCourse("IT383", "1",TTh, 1235, 1350,3,"Undergraduate","Course"));
        courseLog.add(ClassFactory.createCourse("IT386", "1",TTh, 1700, 1800,3,"Undergraduate","Course"));
        courseLog.add(ClassFactory.createCourse("IT326", "1",MW, 1535, 1650,3,"Undergraduate","Course"));
        courseLog.add(ClassFactory.createCourse("MAT146", "1",MWF, 1300, 1415,4,"Undergraduate","Course"));
        courseLog.add(ClassFactory.createCourse("COM110", "1",MWF, 1400, 1450,3,"Undergraduate","Course"));
        courseLog.add(ClassFactory.createCourse("COM110", "3",TTh, 1400, 1450,3,"Undergraduate","Course"));
        ScheduleMaker.addEligibleDay(DayOfWeek.MONDAY);
        ScheduleMaker.addEligibleDay(DayOfWeek.TUESDAY);
        ScheduleMaker.addEligibleDay(DayOfWeek.WEDNESDAY);
        ScheduleMaker.addEligibleDay(DayOfWeek.THURSDAY);
        ScheduleMaker.addEligibleDay(DayOfWeek.FRIDAY);
       
    }

    @After
    public void cleanUp(){
    }
    @Test
    public void OnePossibleSchedule(){
        ScheduleMaker.addClass(courseLog.get(0));
        ScheduleMaker.addClass(courseLog.get(1));
        ScheduleMaker.addClass(courseLog.get(2));
        ScheduleMaker.generateSchedules();
        ArrayList<Schedule> sch = ScheduleMaker.getSchedules();
        boolean assertion = sch.size() == 1;
        assertTrue(assertion);
    }
   
    

    @Test
    public void eventsAllWeek(){
        Event e = new Event("Important Event", 0, 2400);
        e.addOnDay(DayOfWeek.MONDAY);
        e.addOnDay(DayOfWeek.TUESDAY);
        e.addOnDay(DayOfWeek.WEDNESDAY);
        e.addOnDay(DayOfWeek.THURSDAY);
        e.addOnDay(DayOfWeek.FRIDAY);
        ScheduleMaker.addEvent(e);
        for(Class c : courseLog){
            ScheduleMaker.addClass(c);
        }
        ScheduleMaker.generateSchedules();
        ArrayList<Schedule> sch = ScheduleMaker.getSchedules();
        boolean assertion = sch.size() == 0;
        assertTrue(assertion);

    }
    @Test
    public void noPossibleSchedule(){
        ScheduleMaker.addClass(courseLog.get(0));
        ScheduleMaker.addClass(courseLog.get(1));
        ScheduleMaker.generateSchedules();
        ArrayList<Schedule> sch = ScheduleMaker.getSchedules();
        boolean assertion = sch.size() == 0;
        assertTrue(assertion);
    }
    //TODO rework test using factory
    /* 
    @Test
    public void allOneSection(){
        courseLog.add(new Course("COM212",MWF, 1200, 1350, 3));
        courseLog.add(new Course("COM212",MWF, 900, 950, 3));
        courseLog.add(new Course("COM212",MW, 1000, 1115, 3));
        courseLog.add(new Course("COM212",TTh, 1000, 1050, 3));
        courseLog.add(new Course("COM212",TTh, 1400, 1515, 3));
        ScheduleMaker.generateSchedules();
        ArrayList<Schedule> sch = ScheduleMaker.getSchedules();
        boolean assertion = sch.size() == 0;
        assertTrue(assertion);
    }
    */
    @Test
    public void generatesAnySchedules(){
        for(Class c : courseLog){
            ScheduleMaker.addClass(c);
        }
        ScheduleMaker.generateSchedules();
        ArrayList<Schedule> sch = ScheduleMaker.getSchedules();
        boolean assertion = sch.size() > 0;
        assertTrue(assertion);

    }
    @Test
    public void noEligibleDays(){
        ScheduleMaker.removeEligibleDay(DayOfWeek.MONDAY);
        ScheduleMaker.removeEligibleDay(DayOfWeek.TUESDAY);
        ScheduleMaker.removeEligibleDay(DayOfWeek.WEDNESDAY);
        ScheduleMaker.removeEligibleDay(DayOfWeek.THURSDAY);
        ScheduleMaker.removeEligibleDay(DayOfWeek.FRIDAY);
        for(Class c : courseLog){
            ScheduleMaker.addClass(c);
        }
        ScheduleMaker.generateSchedules();
        ArrayList<Schedule> sch = ScheduleMaker.getSchedules();
        boolean assertion = sch.size() == 0;
        assertTrue(assertion);
    }
    
}