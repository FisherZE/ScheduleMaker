package Converter;

import Schedule.Schedule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class CSVConverter extends Converter{
    private static ArrayList<String> fileLocations;
    public CSVConverter(){
    }
    public void outputCSV(Schedule schedule) 
    {
        //set file location to users downloads and store that in the arraylist
        String home = System.getProperty("user.home");
        File file = new File(home+"/Downloads/Schedule.csv");
        fileLocations.add(home+"/Downloads/Schedule.csv");

        try {
            // create FileWriter object with file as parameter
            FileWriter writer = new FileWriter(file);
      
            // adding header to csv
            writer.write("Course,Start Time,End Time\n");
            //loop through rest of the schedule and add the rest of the classes
            for(int i = 0; i < schedule.getClasses().size(); i++)
            {
                String startTime = Integer.toString(schedule.getClasses().get(i).getStartTime());
                String endTime = Integer.toString(schedule.getClasses().get(i).getEndTime());
                writer.write(schedule.getClasses().get(i).getIdentifier()+","+startTime+","+endTime+"\n");
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<String> getFileLocations()
    {
        return fileLocations;
    }
}