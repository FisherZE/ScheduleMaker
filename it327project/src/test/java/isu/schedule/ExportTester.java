package isu.schedule;
import java.time.DayOfWeek;
import java.util.ArrayList;

import org.junit.Test;

import Classes.ClassFactory;
import Export.*;
import Schedule.Schedule;
import Schedule.ScheduleMaker;
import Classes.Class;

public class ExportTester {
    static ArrayList<DayOfWeek> MWF;
    ArrayList<Class> courseLog;

    @Test
    public void AppleExportTest(){
        ScheduleMaker.initialize();
        MWF = new ArrayList<DayOfWeek>();
        MWF.add(DayOfWeek.MONDAY);
        MWF.add(DayOfWeek.WEDNESDAY);
        MWF.add(DayOfWeek.FRIDAY);

        courseLog = new ArrayList<Class>();
        courseLog.add(ClassFactory.createCourse("IT168", "1",MWF, 900, 950,3,"Undergraduate","Course"));
        Schedule schedule = new Schedule();
        schedule.setClasses(courseLog);
        Exporter AppleExporter = ExporterFactory.createAppleCalendarExport();
        AppleExporter.callConverter(schedule);
        assert true;
    }

    @Test
    public void GoogleExportTest(){
        ScheduleMaker.initialize();
        MWF = new ArrayList<DayOfWeek>();
        MWF.add(DayOfWeek.MONDAY);
        MWF.add(DayOfWeek.WEDNESDAY);
        MWF.add(DayOfWeek.FRIDAY);

        courseLog = new ArrayList<Class>();
        courseLog.add(ClassFactory.createCourse("IT168", "1",MWF, 900, 950,3,"Undergraduate","Course"));
        Schedule schedule = new Schedule();
        schedule.setClasses(courseLog);
        Exporter GoogleExporter = ExporterFactory.createGoogleExporter();
        GoogleExporter.callConverter(schedule);
        assert true;
    }

    @Test
    public void OutlookExportTest(){
        ScheduleMaker.initialize();
        MWF = new ArrayList<DayOfWeek>();
        MWF.add(DayOfWeek.MONDAY);
        MWF.add(DayOfWeek.WEDNESDAY);
        MWF.add(DayOfWeek.FRIDAY);

        courseLog = new ArrayList<Class>();
        courseLog.add(ClassFactory.createCourse("IT168", "1",MWF, 900, 950,3,"Undergraduate","Course"));
        Schedule schedule = new Schedule();
        schedule.setClasses(courseLog);
        Exporter OutlookExporter = ExporterFactory.createOutlookExporter();
        OutlookExporter.callConverter(schedule);
        assert true;
    }

    @Test
    public void SpreadsheetExportTest(){
        ScheduleMaker.initialize();
        MWF = new ArrayList<DayOfWeek>();
        MWF.add(DayOfWeek.MONDAY);
        MWF.add(DayOfWeek.WEDNESDAY);
        MWF.add(DayOfWeek.FRIDAY);

        courseLog = new ArrayList<Class>();
        courseLog.add(ClassFactory.createCourse("IT168", "1",MWF, 900, 950,3,"Undergraduate","Course"));
        Schedule schedule = new Schedule();
        schedule.setClasses(courseLog);
        Exporter SpreadsheetExporter = ExporterFactory.createSpreadsheetExporter();
        SpreadsheetExporter.callConverter(schedule);
        assert true;
    }

    @Test
    public void EmailExportTest(){
        ScheduleMaker.initialize();
        MWF = new ArrayList<DayOfWeek>();
        MWF.add(DayOfWeek.MONDAY);
        MWF.add(DayOfWeek.WEDNESDAY);
        MWF.add(DayOfWeek.FRIDAY);

        courseLog = new ArrayList<Class>();
        courseLog.add(ClassFactory.createCourse("IT168", "1",MWF, 900, 950,3,"Undergraduate","Course"));
        Schedule schedule = new Schedule();
        schedule.setClasses(courseLog);
        EmailExporter emailExporter = ExporterFactory.createEmailExporter();
        emailExporter.exportEmail(schedule, "schedulemaker312@gmail.com");
        assert true;
    }
}
