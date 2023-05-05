package Converter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

import Classes.Class;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.property.DtStamp;
import Schedule.Schedule;

public class ICSConverter extends Converter{
    private String version =    "VERSION:1.0\r\n";
    private String prodid =     "PRODID://You//\r\n";
    private String calBegin =   "BEGIN:VCALENDAR\r\n";
    private String calEnd =     "END:VCALENDAR\r\n";
    private String eventBegin = "BEGIN:VEVENT\r\n";
    private String eventEnd =   "END:VEVENT\r\n";
    private String UID = "UID:You\r\n";
    private String DTStamp;
    private String Organizer = "ORGANIZER;You:MAILTO:You\r\n";
    private String DTStart;
    private String DTEnd;
    private String Summary;
    private String[] fileComponents = new String[12];
    private ArrayList<String> fileLocations = new ArrayList<>();
    private static final String endL = "\r\n";
    public ICSConverter(){
    }
    public void outputICS(Schedule schedule)
    {
        for(int i = 0; i <schedule.getClasses().size(); i++)
        {
           Summary = schedule.getClasses().get(i).getIdentifier()+endL;
           //check if class is before noon and add a preceding 0
           DTStart = "T" + schedule.getClasses().get(i).getStartTime() + "00"+endL;
           DTEnd = "DTEND:"+"T" + schedule.getClasses().get(i).getEndTime() + "00"+endL;
           DTStamp = "DTSTAMP:"+DTStart;
            DTStart = "DTStart:" + DTStart;
           fileComponents[0] = calBegin;
            fileComponents[1] = version;
            fileComponents[2] = prodid;
            fileComponents[3] = eventBegin;
            fileComponents[4] = UID;
            fileComponents[5] = DTStamp;
            fileComponents[6] = Organizer;
            fileComponents[7] = DTStart;
            fileComponents[8] = DTEnd;
            fileComponents[9] = "SUMMARY:"+Summary+endL;
            fileComponents[10] = eventEnd;
            fileComponents[11] = calEnd;
            write(Summary);
        }
        
   
}
public void write(String name){
    StringBuilder builder = new StringBuilder();
    builder.append(name);
    builder.append(".ics");
    try {
        String fileName = builder.toString();
        String home = System.getProperty("user.home");
        File file = new File(home+"/Downloads/"+fileName);
        fileLocations.add(home+"/Downloads"+fileName);
        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(fileComponents[0]);
        bw.write(fileComponents[1]);
        bw.write(fileComponents[2]);
        bw.write(fileComponents[3]);
        bw.write(fileComponents[4]);
        bw.write(fileComponents[5]);
        bw.write(fileComponents[6]);
        bw.write(fileComponents[7]);
        bw.write(fileComponents[8]);
        bw.write(fileComponents[9]);
        bw.write(fileComponents[10]);
        bw.write(fileComponents[11]);

        bw.close();

        System.out.println("Done");
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    public ArrayList<String> getFileLocations()
    {
        return fileLocations;
    }
}
