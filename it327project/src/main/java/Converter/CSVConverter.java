package Converter;

import Schedule.Schedule;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Classes.Class;

public class CSVConverter {
    private String fileName;
    private ArrayList<String> fileLocations;
    public CSVConverter(){
    }
    public void outputCSV(String fileName, Schedule schedule) throws IOException
    {
        List<String[]> csvData = createCsvDataSimple(schedule);

        // default all fields are enclosed in double quotes
        // default separator is a comma
        String home = System.getProperty("user.home");
        try (CSVWriter writer = new CSVWriter(new FileWriter(new File(home+"/Downloads/"+fileName+".csv")))) {
            fileLocations.add(home+"/Downloads"+fileName+".csv");
            writer.writeAll(csvData);
        }
    }
    private static List<String[]> createCsvDataSimple(Schedule schedule) {
        String[] header = {"Course", "Start Time", "End Time"};
    
        List<String[]> list = new ArrayList<>();
        list.add(header);
        for(int i = 0; i < schedule.getClasses().size(); i++)
        {
            String startTime = Integer.toString(schedule.getClasses().get(i).getStartTime());
            String endTime = Integer.toString(schedule.getClasses().get(i).getEndTime());
            String[] record = {schedule.getClasses().get(i).getIdentifier(), startTime, endTime};
            list.add(record);
        }
        return list;
    }
    public ArrayList<String> getFileLocations()
    {
        return fileLocations;
    }
}
