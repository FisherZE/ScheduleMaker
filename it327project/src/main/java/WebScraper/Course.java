package WebScraper;

public class Course
{
    public String courseNo;
    public String secNo;
    public boolean gradCredit;
    public int creditHours;
    public int startTime;
    public int endTime;
    public boolean onDays[] = {false, false, false, false, false, false, false};

    public Course(String courseNo, String secNo, boolean gradCredit, int creditHours, int startTime, int endTime, boolean[] onDays)
    {
        this.courseNo = courseNo;
        this.secNo = secNo;
        this.gradCredit = gradCredit;
        this.creditHours = creditHours;
        this.startTime = startTime;
        this.endTime = endTime;
        this.onDays = onDays;
    }
    public boolean[] getOnDays()
    {
        return this.onDays;
    }
    public void display()
    {
        System.out.println(
            "\nName: " + this.courseNo +
            "\nSection: " + this.secNo +
            "\nGrad Credit: " + this.gradCredit + 
            "\nCredit Hours: " + this.creditHours +
            "\nStart Time: " + this.startTime + 
            "\nEnd Time: " + this.endTime);
        if (onDays[0]) { System.out.print("Sun ");}
        if (onDays[1]) { System.out.print("Mon ");}
        if (onDays[2]) { System.out.print("Tues ");}
        if (onDays[3]) { System.out.print("Wed ");}
        if (onDays[4]) { System.out.print("Thu ");}
        if (onDays[5]) { System.out.print("Fri ");}
        if (onDays[6]) { System.out.print("Sat ");}
        
    }
}