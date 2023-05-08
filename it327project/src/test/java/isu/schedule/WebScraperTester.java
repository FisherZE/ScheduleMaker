package isu.schedule;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import WebScraper.WebScraper;
import Classes.Class;
import Classes.Course;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.DayOfWeek;
import java.util.ArrayList;
public class WebScraperTester 
{
    @Test
    public void scrapeTest() throws FailingHttpStatusCodeException, MalformedURLException, IOException
    {
        ArrayList<DayOfWeek> arr = new ArrayList<DayOfWeek>();
        arr.add(DayOfWeek.TUESDAY);
        arr.add(DayOfWeek.THURSDAY);

        Course test = new Course("IT 326", "001", arr, 1235, 150, 3, "Graduate", "Lecture");
        Class[] courses = WebScraper.searchCourse("IT 326");
        assertEquals(courses[0], test);

    }
}
