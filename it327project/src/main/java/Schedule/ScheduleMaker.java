package Schedule;
import java.time.*;
import java.util.ArrayList;

import Classes.Class;
import Classes.Course;

 public class ScheduleMaker
 {
    
    private static ArrayList<Schedule> schedules;
    private static ArrayList<DayOfWeek> eligibleDays;
    private static ArrayList<Event> events;
    private static ArrayList<String> requiredCourses;
    private static ArrayList<Class> classes;
    private static int minCreditHours;
    private static int maxCreditHours;
    private static int earliestTime;
    private static int latestTime;

    // Starts up the schedulemaker. Stands in for a constructor
    public static void initialize()
    {
        schedules = new ArrayList<Schedule>();
        events = new ArrayList<Event>();
        eligibleDays = new ArrayList<DayOfWeek>(); 
        classes = new ArrayList<Class>();
        requiredCourses = new ArrayList<String>();
        minCreditHours = 1;
        maxCreditHours = 30;
        earliestTime = 0;
        latestTime = 2400;
        eligibleDays.add(DayOfWeek.MONDAY);
        eligibleDays.add(DayOfWeek.TUESDAY);
        eligibleDays.add(DayOfWeek.WEDNESDAY);
        eligibleDays.add(DayOfWeek.THURSDAY);
        eligibleDays.add(DayOfWeek.FRIDAY);
    } 

    public static void generateSchedules(){
        //Creates a bitmask that is used to track the current combination of schedules being checked
        //A 1 in the bitmask signifies the presence of the class in that index in the current schedule
        ArrayList<Class> classList = new ArrayList<Class>(classes);
        ArrayList<Class> toRemove = new ArrayList<Class>();
        initloop:
        for (Class c: classList){
            for (DayOfWeek day : c.getOnDays()){
                if (!(eligibleDays.contains(day))){
                    toRemove.add(c);
                    continue initloop;
                }else if (c.getStartTime() < earliestTime || c.getEndTime() > latestTime){
                    toRemove.add(c);
                    continue initloop;
                }
            }

        }
        for (Class c : toRemove){
            classList.remove(c);
        }
        int[] mask = new int[classList.size()];
        mainloop:
        for (int i = 0; i < Math.pow(2, mask.length); i++)
        {      
            Schedule newSched = new Schedule();
            if (i != 0)
            {
                mask[0]++;
            }
            //Increments the mask
            for (int j = 0; j < mask.length; j++)
            {
                if (mask[j] > 1)
                {
                    mask[j] = 0;
                    if (j != mask.length - 1)
                    {
                        mask[j+1]++;
                    }
                }
            }
            // Tracks how many required courses are included
            // Will be referenced after the schedule is finalizied to see if it meets requirements
            int includedRequirements = 0;

            // Checking for 1s in the bitmask which signifies a class is included in the current schedule
            for (int j = 0; j < mask.length; j++)
            {
                if (mask[j] == 1)
                {
                    //Reassigns the current class for easier access
                   Course cur = (Course) classList.get(j);
                 
                    for (Class c : newSched.getClasses()) 
                    {
                        // Checks to see if a class with the same identifier is alreadly present in the current schedule 
                        // (Prevents duplicate classes from different sections)
                        if (cur.getCourseId().equals(((Course) c).getCourseId()))
                        {
                            continue mainloop;
                        }
                        for (DayOfWeek day : cur.getOnDays()) 
                        {
                            // Checks for overlap between days
                            if (c.getOnDays().contains(day))
                            {
                                if ((cur.getStartTime() >= c.getStartTime() && cur.getStartTime() <= c.getEndTime())||(cur.getEndTime() >= c.getStartTime() && cur.getEndTime() <= c.getEndTime()))
                                {
                                    continue mainloop;
                                }
                            }
                        }       
                    }
                    // Checks for overlap with events
                    for (Event e : events) 
                    {
                        System.out.print(cur.getOnDays());
                        for (DayOfWeek day : cur.getOnDays()) 
                        {    
                            
                            if (e.getOnDays().contains(day))
                            {
                                if ((cur.getStartTime() >= e.getStartTime() && cur.getStartTime() <= e.getEndTime())||(cur.getEndTime() >= e.getStartTime() && cur.getEndTime() <= e.getEndTime()))
                                {
                                    System.out.println("event conflict");
                                    continue mainloop;
                                }

                            }
                        }
                    }       
                    if (requiredCourses.contains(((Course) cur).getCourseId())){
                        includedRequirements++;
                    }      
                    newSched.getClasses().add(cur);
                }
            }
            
            int cred = 0;
            for (Class c : newSched.getClasses()) 
            {
                cred += c.getCreditHours();
            }
            
            // Checks to see if the current schedule meets user credit hour preferences
            newSched.setCredithours(cred);
            boolean flag = (cred >= minCreditHours && cred <=maxCreditHours && includedRequirements == requiredCourses.size());
            if (flag)
            {
                schedules.add(newSched);
            }      
                          
        }        
        courseConflicts();
    }
    //Sorts the schedules by earliest start time
    public static void sortSchedules(){
        ArrayList<Schedule> sortedSchedules = new ArrayList<Schedule>();
       
        while (!schedules.isEmpty()){
            int earlyTime = 2401;
            int earlyIndex = 0;
           for (int i = 0; i < schedules.size(); i++){
            for (Class c : schedules.get(i).getClasses()){
                if (c.getStartTime() < earlyTime){
                    earlyTime = c.getStartTime();
                    earlyIndex = i;
                }

            }
           } 
           sortedSchedules.add(schedules.get(earlyIndex));
           schedules.remove(earlyIndex);
        }
        schedules = sortedSchedules;
        

    }
    // Checks to see which class has the most conflictions. Returns null if no culprit is found
    public static void courseConflicts(){
        int maxConflict = 0;
        Class conflictingClass = null;
        boolean tie = false;
        for (Class c : classes){
            int conflict = 0;
            for (Class c2 : classes){
                if (!((Course)c2).getCourseId().equals(((Course) c).getCourseId())){
                    if ((c.getStartTime() >= c2.getStartTime() && c.getStartTime() <= c2.getEndTime())||(c.getEndTime() >= c2.getStartTime() && c.getEndTime() <= c2.getEndTime()) || ((c2.getStartTime() >= c.getStartTime() && c2.getStartTime() <= c.getEndTime())||(c2.getEndTime() >= c.getStartTime() && c2.getEndTime() <= c.getEndTime()))){
                        
                        boolean possibleConflict =false;
                        for (DayOfWeek day : c.getOnDays()){
                            if (c2.getOnDays().contains(day)){
                                possibleConflict = true;
                            }
                        }
                        if (possibleConflict)
                        conflict++;
                
                    }
                }
                
            }
            System.out.println(c.getIdentifier() + " conflicts:" + conflict);
            if (conflict > maxConflict){
                    maxConflict = conflict;
                    tie = false;
                    conflictingClass = c;

                }else if(conflict == maxConflict){
                    tie = true;

                }

        }
        if (conflictingClass != null && !tie){
            System.out.println(conflictingClass.getIdentifier() + " caused the most conflicts during generation");
        }else{
            System.out.println("No course caused more conflicts than any other during generation");
        }
        
    }

    public static void setMinCreditHours(int hours)
    {
        minCreditHours = hours;
    }
    public static void setMaxCreditHours(int hours)
    {
        maxCreditHours = hours;
    }
    public static void setEarliestTime(int time)
    {
        earliestTime = time;
    }
    public static void setLatestTime(int time)
    {
        latestTime = time;
    }

    public static ArrayList<Schedule> getSchedules()
    {
        return schedules;
    }

    public static void addEvent(Event e)
    {
        events.add(e);
    }
    public static boolean removeEvent(Event e)
    {
            return events.remove(e);
    }
    
    public static void addEligibleDay(DayOfWeek day){
        eligibleDays.add(day);

    }
    public static void removeEligibleDay(DayOfWeek day){
        eligibleDays.remove(day);
    }

    public static void addRequiredCourse(String name){
        requiredCourses.add(name);
    }
    public static void removeRequiredCourse(String name){
        requiredCourses.remove(name);
    }

    public static void addClass(Class c){
        for (int i = 0; i < classes.size(); i++){
            if (classes.get(i).getStartTime() >= c.getStartTime()){
                classes.add(i, c);
                return;
            }
        }
        classes.add(c);
    }
    
    public static ArrayList<Class> getClasses(){
        return classes;
    }
    public static void removeClass(Class c){
        classes.remove(c);
    }
    public static void setScheduleList(ArrayList<Schedule> sch){
        schedules = sch;
    }
    public static void setEligibleDays(ArrayList<DayOfWeek> days){
        eligibleDays = days;

    }

    public static void removeSchedule(Schedule sched){
        schedules.remove(sched);
    }


 }