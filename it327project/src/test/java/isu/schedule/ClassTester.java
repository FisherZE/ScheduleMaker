package isu.schedule;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.DayOfWeek;
import java.util.ArrayList;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import Classes.Class;

import Classes.ClassFactory;
import Controllers.MakerController;
import Schedule.ScheduleMaker;
import WebScraper.WebScraper;

public class ClassTester {
    @Test
    public void factoryTest(){
        String[] course = {"IT180", "1", "Undergrad", "Course"};
        ArrayList<DayOfWeek> day = new ArrayList<DayOfWeek>();
        day.add(DayOfWeek.MONDAY);
        int[] intarr = {1000, 1300, 3};
        Class courseTest = ClassFactory.createCourse(course[0], course[1], day, intarr[0], intarr[1], intarr[2], course[2], course[3]);
        assertEquals(courseTest.getClassType(), "Course");
        assertEquals(courseTest.getIdentifier(), "IT1801");
    }

    @Test
    public void editClass() throws FailingHttpStatusCodeException, MalformedURLException, IOException{
        Class[] classes = WebScraper.searchCourse("IT166");
        ScheduleMaker.initialize();
        for (Class c : classes){
            ScheduleMaker.addClass(c);
        }
        String[] arr = {"IT 166  001", "3", "", ""};
        MakerController.editClass(arr, new ArrayList<DayOfWeek>(){});
        Class c1 = ScheduleMaker.getClasses().get(0);
        assertEquals(c1.getCreditHours(), 3);
    }

    @Test
    public void removeClass() throws FailingHttpStatusCodeException, MalformedURLException, IOException{
        Class[] classes = WebScraper.searchCourse("IT166");
        Class c1 = classes[0];
        ScheduleMaker.initialize();
        for (Class c : classes){
            ScheduleMaker.addClass(c);
        }
        String arr = "IT 166 001";
        MakerController.removeClass(arr);
        boolean flag = ScheduleMaker.getClasses().contains(c1);
        assert(flag);
    }
}
