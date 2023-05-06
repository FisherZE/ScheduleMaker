package Controllers;

import Classes.Class;
import Schedule.Event;
import Schedule.ScheduleMaker;
import WebScraper.WebScraper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.DayOfWeek;
import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class MakerController {
    public static void findClass(String param) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
        Class[] classes = WebScraper.searchCourse(param);
        for (Class class1 : classes){
            MakerController.addClass(class1);
        }

    }

    public static boolean addClass(Class class1){
        if (class1 == null) return false;
        ScheduleMaker.addClass(class1);
        return true;

    }

    public static boolean editClass(String[] arr, ArrayList<DayOfWeek> days){
        //arr[0] is name and section
        //loop through schedule maker and check class.toString
        for(int i = 0; i < ScheduleMaker.getClasses().size(); i++)
        {
            if(ScheduleMaker.getClasses().get(i).toString().equals(arr[0]))
            {
                Class c = ScheduleMaker.getClasses().get(i);
                c.setCreditHours(Integer.parseInt(arr[1]));
                c.setStartTime(Integer.parseInt(arr[2]));
                c.setEndTime(Integer.parseInt(arr[3]));
                ScheduleMaker.removeClass(ScheduleMaker.getClasses().get(i));
                ScheduleMaker.addClass(c);
                return true;
            }
        }
        return false;
        
    }

    public static boolean removeClass(String arr){
        for(int i = 0; i < ScheduleMaker.getClasses().size(); i++)
        {
            if(ScheduleMaker.getClasses().get(i).toString().equals(arr))
            {
                ScheduleMaker.removeClass(ScheduleMaker.getClasses().get(i));
                return true;
            }
        }
        return false;
    }

    public boolean addPreferences(String[] arr, ArrayList<DayOfWeek> days){
        updatePreferences(arr, days);
        return false;

    }

    public static boolean updatePreferences(String[] arr, ArrayList<DayOfWeek> days){
        if (arr.length != 4) return false;
        if (!arr[0].equals("")){
            ScheduleMaker.setMinCreditHours(Integer.parseInt(arr[0]));
        }
        if (!arr[1].equals("")){
            ScheduleMaker.setMaxCreditHours(Integer.parseInt(arr[1]));
        }
        if (!arr[2].equals("")){
            ScheduleMaker.setEarliestTime(Integer.parseInt(arr[2]));

        }
        if (!arr[3].equals("")){
            ScheduleMaker.setLatestTime(Integer.parseInt(arr[3]));
        }
        if (days.size() != 0){
            ScheduleMaker.setEligibleDays(days);
        }
        return true;
                
    }

    public static boolean deletePreferences(){
        ScheduleMaker.setMinCreditHours(8);
        ScheduleMaker.setMaxCreditHours(30);
        ScheduleMaker.setEarliestTime(0);
        ScheduleMaker.setLatestTime(2400);
        ArrayList<DayOfWeek> days = new ArrayList<DayOfWeek>();
        days.add(DayOfWeek.MONDAY);
        days.add(DayOfWeek.TUESDAY);
        days.add(DayOfWeek.WEDNESDAY);
        days.add(DayOfWeek.THURSDAY);
        days.add(DayOfWeek.FRIDAY);
        ScheduleMaker.setEligibleDays(days);
        return true;

    }

    public boolean addEvent(Event event){
        if (event == null) return false;
        ScheduleMaker.addEvent(event);
        return true;

    }

    public static boolean removeEvent(Event event){
        if (event == null) return false;
        return ScheduleMaker.removeEvent(event);

    }
}