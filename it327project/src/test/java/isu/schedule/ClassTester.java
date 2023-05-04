package isu.schedule;

import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.util.ArrayList;

import org.junit.Test;

import Classes.Class;

import Classes.ClassFactory;

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
}
