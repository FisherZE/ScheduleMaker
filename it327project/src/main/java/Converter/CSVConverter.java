package Converter;

import Schedule.Schedule;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVConverter extends Converter{
    private String fileName;
    private ArrayList<String> fileLocations;
    public CSVConverter(){
    }
    public void outputCSV(String fileName, Schedule schedule) throws IOException
    {
        String home = System.getProperty("user.home");
        File file = new File(home+"/Downloads/Schedule.csv");
        fileLocations.add(home+"/Downloads/Schedule.csv");

        try {
            // create FileWriter object with file as parameter
            FileWriter writer = new FileWriter(file);
      
            // adding header to csv

            writer.write("Course,Start Time,End Time\n");
            for(int i = 0; i < schedule.getClasses().size(); i++)
            {
            String startTime = Integer.toString(schedule.getClasses().get(i).getStartTime());
            String endTime = Integer.toString(schedule.getClasses().get(i).getEndTime());
            writer.write(schedule.getClasses().get(i).getIdentifier()+","+startTime+","+endTime+"\n");
            }
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public ArrayList<String> getFileLocations()
    {
        return fileLocations;
    }
}
