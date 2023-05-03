package Schedule;

import java.time.*;
import java.util.ArrayList;
 public class scheduleMaker
 {
    private static ArrayList<Schedule> schedules;
    private static ArrayList<Event> events;
    private static int minCreditHours;
    private static int maxCreditHours;
    private static int earliestTime;
    private static int latestTime;
    public static void initialize()
    {
        schedules = new ArrayList<Schedule>();
        events = new ArrayList<Event>();
        minCreditHours = 0;
        maxCreditHours = 30;
        earliestTime = 0;
        latestTime = 2400;
    }

    public static void generateSchedules(ArrayList<Class> classList){
        //Creates a bitmask that is used to track the current combination of schedules being checked
        //A 1 in the bitmask signifies the presence of the class in that index in the current schedule
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
            // Checking for 1s which signifies a class
            for (int j = 0; j < mask.length; j++)
            {
                if (mask[j] == 1)
                {
                   Class cur = classList.get(j);
                   // Checks to see if the included class meets user time preferences
                   if (cur.getStartTime() < earliestTime || cur.getEndTime() > latestTime)
                   {
                    continue mainloop;
                   }
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
                        for (DayOfWeek day : cur.onDays()) 
                        {      
                            if (e.getOnDays().contains(day))
                            {
                                if ((cur.getStartTime() >= e.getStartTime() && cur.getStartTime() <= e.getEndTime())||(cur.getEndTime() >= e.getStartTime() && cur.getEndTime() <= e.getEndTime()))
                                {
                                    continue mainloop;
                                }

                            }
                        }
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
            if (cred >= minCreditHours && cred <= maxCreditHours)
            {
                schedules.add(newSched);
            }                      
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
    

 }