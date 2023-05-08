package WebScraper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Scanner;
import Classes.Class;
import Classes.ClassFactory;
import Classes.Course;
import java.util.ArrayList;
import java.time.DayOfWeek;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

public class WebScraper 
{

public static Class[] searchCourse(String query) throws FailingHttpStatusCodeException, MalformedURLException, IOException 
{
    WebClient client = new WebClient(); // instantiate new "browser"

    // I have no idea what these do but if they aren't here the program throws an exception and dies
    client.getOptions().setCssEnabled(false); 
	client.getOptions().setJavaScriptEnabled(false);
    
    Scanner scan = new Scanner(System.in);
    HtmlPage searchPage = client.getPage("https://coursefinder.illinoisstate.edu/"); // connect to search page

    // find the form where the name is entered. The form is the only form on the page, so .get(0) will suffice
    HtmlForm form = (HtmlForm) searchPage.getByXPath("//form").get(0); 

    // find the field where the name is entered
    HtmlInput searchBar = form.getInputByName("q"); 

    // find the submit button, it is the third input on the page
    HtmlInput submitButton = (HtmlInput) searchPage.getByXPath("//input").get(2); 
    
    // type in the field and search, saving the results page
    searchBar.type(query); 
    HtmlPage resultsPage = submitButton.click();

    // find and click the first link to get to the class page
    List<HtmlAnchor> links = resultsPage.getAnchors();
    HtmlAnchor link = links.get(14); // the first search result is the 15th link on the page
    HtmlPage classPage = link.click();
    Class[] courses = parseResults(classPage);
    scan.close();
    return courses;
    
}


// private method to read in the results of the search and return a list of player objects
private static Class[] parseResults(HtmlPage page)
{
    String gradCredit = "Undergraduate";
    ArrayList<DayOfWeek> onDays = new ArrayList<DayOfWeek>();
    int startTime;
    int endTime;
    int creditHours;
    int numSections = 0;
    String secNo;
    String courseNo;
    HtmlElement element;
    // first, get the things that are the same for each section

    // credit hours
    element = (HtmlElement) page.getByXPath("//p").get(2);
    String creditHoursString = element.asText();
    creditHours = Integer.parseInt(creditHoursString.substring(14, 15));
    //System.out.println("credit hours = " + creditHours);

    // grad credit
    String pageText = page.asText();
    if (pageText.contains("Graduate Level Course"))
    { gradCredit = "Graduate";}
    //System.out.println("grad credit = " + gradCredit);

    // courseNo
    element = page.getFirstByXPath("//h1");
    String headingText = element.asText();
    courseNo = headingText.substring(0, headingText.indexOf("-"));
    //System.out.println("courseNo = " + courseNo);

    // numSections
    String tester;
    for (int i = 1; i < 100; i++)
    {   
        tester = "00" + Integer.toString(i) + " - Lecture";
        if (pageText.contains(tester))
        {
            numSections++;
        }
        else
        {
            i = 99;
        }
    }
    // section differing starts here
    Course[] courses = new Course[numSections]; // array to return the courses
    for (int i = 1; i < numSections + 1; i++)
    {
        
        // secno
        if (i < 10) { secNo = "00" + Integer.toString(i);}
        else if (i < 100) {secNo = "0" + Integer.toString(i);}
        else {secNo = Integer.toString(i);}
        //System.out.println("secNo = " + secNo);
        String sectionInfo = pageText.substring(pageText.indexOf(secNo),pageText.indexOf(secNo)+70);
        //System.out.println(sectionInfo);
        // onDays
        if (sectionInfo.contains(" M ")) { onDays.add(DayOfWeek.MONDAY);}
        if (sectionInfo.contains(" T ")) { onDays.add(DayOfWeek.TUESDAY);}
        if (sectionInfo.contains(" W ")) { onDays.add(DayOfWeek.WEDNESDAY);}
        if (sectionInfo.contains(" Th ")) { onDays.add(DayOfWeek.THURSDAY);}
        if (sectionInfo.contains(" F ")) { onDays.add(DayOfWeek.FRIDAY);}

        // startTime
        String startTimeString = sectionInfo.substring(sectionInfo.indexOf(":")+1);
        String preColon = "";
        String postColon = "";
        String fullString = "";
        for (int j = 0; j < startTimeString.length(); j++)
        {
            // look for the colon, denoting the time
            if (startTimeString.charAt(j) == ':')
            {
                // try going back two indices to see if the time is double digits or not
                if (startTimeString.charAt(j-2) == ' ') // time is not double digits
                {
                    preColon = startTimeString.substring(j-1, j);
                }  
                else // time is double digits
                {
                    preColon = startTimeString.substring(j-2, j);
                }

                // grab the other half of the time, which will always be two digits
                postColon = startTimeString.substring(j+1, j+3);

                // check if AM or PM
                if (startTimeString.charAt(j+4) == 'P') // is PM, convert to military time
                {
                    int start = Integer.parseInt(preColon);
                    if (start < 12) { start = start + 12; }
                    preColon = Integer.toString(start);
                
                }
                // done with startTime
                j = startTimeString.length();
            }
            fullString = preColon + postColon;
            
        }
        startTime = Integer.parseInt(fullString);
        //System.out.println("startTime = "+ startTime);

        // end time
        String endTimeString = startTimeString.substring(startTimeString.indexOf(":")+1, startTimeString.length() - 1);
        for (int j = 0; j < endTimeString.length(); j++)
        {
            if (endTimeString.charAt(j) == ':')
            {
                // try going back two indices to see if the time is double digits or not
                if (endTimeString.charAt(j-2) == ' ') // time is not double digits
                {
                    preColon = endTimeString.substring(j-1, j);
                }  
                else // time is double digits
                {
                    preColon = endTimeString.substring(j-2, j);
                }

                // grab the other half of the time, which will always be two digits
                postColon = endTimeString.substring(j+1, j+3);

                // check if AM or PM
                if (endTimeString.charAt(j+4) == 'P') // is PM, convert to military time
                {
                    int start = Integer.parseInt(preColon);
                    if (start < 12) { start = start + 12; }
                    preColon = Integer.toString(start);
                
                }
                // done with endTime
                j = endTimeString.length();
            }
            fullString = preColon + postColon;
        }
        endTime = Integer.parseInt(fullString);
        //System.out.println("endTime = "+ endTime);
        courses[i - 1] = ClassFactory.createCourse(courseNo, secNo, new ArrayList<DayOfWeek>(), startTime, endTime, creditHours, gradCredit, "Lecture"  );
	    // add days into the course
        for (int j = 0; j < onDays.size(); j++)
        {
            courses[i-1].addDay(onDays.get(j));
        }   
        onDays.clear();
    }

    return courses;
}
}