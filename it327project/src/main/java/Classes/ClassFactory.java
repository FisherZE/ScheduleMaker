package Classes;

import java.time.*;
import java.util.*;

public class ClassFactory {

    public static Class createCourse(String courseId, String secId, ArrayList<DayOfWeek> onDays, int startTime, int endTime, int creditHours, String courseType, String classType) {
        // create a new instance of a concrete implementation of the Class abstract class
        Class newClass = new Course(courseId, secId, onDays, startTime, endTime, creditHours, classType, classType);
        return newClass;
    }

    public static Class createHExploration(String explorationId, ArrayList<String> meetingDays, ArrayList<DayOfWeek> onDays, int startTime, int endTime, int creditHours, String classType) {
        // create a new instance of a concrete implementation of the Class abstract class
        Class newClass = new HExploration(explorationId, meetingDays, onDays, startTime, endTime, creditHours, classType);
        return newClass;
    }

    public static Class createHSeminar(String courseId, String secId, ArrayList<DayOfWeek> onDays, int startTime, int endTime, int creditHours, String classType) {
        // create a new instance of a concrete implementation of the Class abstract class
        Class newClass = new HSeminar(courseId, secId, false, onDays, startTime, endTime, creditHours, classType);
        return newClass;
    }
}