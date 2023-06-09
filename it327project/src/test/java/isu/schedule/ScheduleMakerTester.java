package isu.schedule;
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
        
        ScheduleMaker.initialize();
        ScheduleMaker.setMinCreditHours(8);
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
        ScheduleMaker.addClass(courseLog.get(1));
        ScheduleMaker.addClass(courseLog.get(5));
        ScheduleMaker.generateSchedules();
        ArrayList<Schedule> sch = ScheduleMaker.getSchedules();
        boolean assertion = sch.size() == 0;
        assertTrue(assertion);
    }
    
    
    @Test
    public void allOneSection(){
        courseLog.add(ClassFactory.createCourse("COM212","1",MWF, 1200, 1350, 3,"Undergraduate","Course"));
        courseLog.add(ClassFactory.createCourse("COM212","2",MWF, 900, 950, 3,"Undergraduate","Course"));
        courseLog.add(ClassFactory.createCourse("COM212","3",MW, 1000, 1115, 3,"Undergraduate","Course"));
        courseLog.add(ClassFactory.createCourse("COM212","4",TTh, 1000, 1050, 3,"Undergraduate","Course"));
        courseLog.add(ClassFactory.createCourse("COM212","5",TTh, 1400, 1515, 3,"Undergraduate","Course"));
        ScheduleMaker.generateSchedules();
        ArrayList<Schedule> sch = ScheduleMaker.getSchedules();
        boolean assertion = sch.size() == 0;
        assertTrue(assertion);
    }
    
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
    @Test 
    public void allEncompassingClass(){
        courseLog.add(ClassFactory.createCourse("VOID404", "99",MWF, 0, 2400,3,"Undergraduate","Course"));
        for(Class c : courseLog){
            ScheduleMaker.addClass(c);
        }
        ScheduleMaker.generateSchedules();
        ArrayList<Schedule> sch = ScheduleMaker.getSchedules();
        boolean test = true;
        for (Schedule sched : sch){
            if (sched.getClasses().contains(courseLog.get(0)) && sched.getClasses().contains(courseLog.get(8))){
                test = false;
            }

        }
        assertTrue(test);
    }
    
}
