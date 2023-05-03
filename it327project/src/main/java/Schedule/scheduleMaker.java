import java.time.*;
import java.util.ArrayList;
 public class scheduleMaker
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

    public scheduleMaker()
    {
        schedules = new ArrayList<Schedule>();
        events = new ArrayList<Event>();
        eligibleDays = new ArrayList<DayOfWeek>(); 
        classes = new ArrayList<Class>();
        requiredCourses = new ArrayList<String>();
        minCreditHours = 0;
        maxCreditHours = 30;
        earliestTime = 0;
        latestTime = 2400;
        
        
    }

    public static void generateSchedules(){
        //Creates a bitmask that is used to track the current combination of schedules being checked
        //A 1 in the bitmask signifies the presence of the class in that index in the current schedule
        ArrayList<Class> classList = new ArrayList<Class>(classes);
        ArrayList<Class> toRemove = new ArrayList<Class>();
        initloop:
        for (Class c: classList){
            for (DayOfWeek day : c.onDays()){
                if (!(eligibleDays.contains(day))){
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
                   Class cur = classList.get(j);
                 
                    for (Class c : newSched.getClasses()) 
                    {
                        // Checks to see if a class with the same identifier is alreadly present in the current schedule 
                        // (Prevents duplicate classes from different sections)
                        if (cur.getIdentifier().equals(c.getIdentifier()))
                        {
                            continue mainloop;
                        }
                        for (DayOfWeek day : cur.onDays()) 
                        {
                            // Checks for overlap between days
                            if (c.onDays().contains(day))
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
                        System.out.print(cur.onDays());
                        for (DayOfWeek day : cur.onDays()) 
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
                    if (requiredCourses.contains(cur.getIdentifier())){
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
            
            if (cred >= 8 && cred <= 21 && includedRequirements == requiredCourses.size())
            {
                schedules.add(newSched);
            }     
                          
        }        
        
    }

    public static void sortSchedules(){

    }
    public static String generateConflicts(){
        return null;
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
        classes.add(c);
    }
    public static void removeClass(Class c){
        classes.remove(c);
    }


 }