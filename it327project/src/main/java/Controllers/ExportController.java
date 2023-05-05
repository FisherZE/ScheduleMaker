package Controllers;

import java.time.DayOfWeek;

import Schedule.Schedule;

public class ExportController {
    public static String toDate(DayOfWeek d){ 
        if (d == DayOfWeek.MONDAY){
            return "20230501";
        }else if (d == DayOfWeek.TUESDAY){
            return "20230502";
        }else if (d == DayOfWeek.WEDNESDAY){
            return "20230503";
        }else if (d == DayOfWeek.THURSDAY){
            return "20230504";
        }else if (d == DayOfWeek.FRIDAY){
            return "20230505";
        }else if (d == DayOfWeek.SATURDAY){
            return "20230506";
        }else {
            return "20230507";
        }
        
    }

    public static void exportEmail(String email, String fileType, Schedule Schedule){

    }

    public static void downloadFile(String fileType, Schedule schedule){

    }

}
