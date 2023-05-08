package Controllers;

import java.io.IOException;
import java.time.DayOfWeek;

import Converter.CSVConverter;
import Converter.ICSConverter;
import Export.EmailExporter;
import Schedule.Schedule;

public class ExportController {
    //Without mapping out the entire year with depth first search, this is the alternative way
    //to create a weeks schedule with .ics files since they require a date
    //If I had time to research more libraries and find one that could reliably get 
    //current time I would
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
        EmailExporter emailExporter = new EmailExporter();
        emailExporter.exportEmail(Schedule, email);
    }

    public static void downloadFile(String fileType, Schedule schedule) throws IOException{
        if(fileType.equals("CSV")){
            CSVConverter csv = new CSVConverter();
            csv.outputCSV(schedule);
            CSVConverter.getFileLocations().clear();
        } else if (fileType.equals("ICS")){
            ICSConverter ics = new ICSConverter();
            ics.outputICS(schedule);
            ICSConverter.getFileLocations().clear();
        }
    }

}