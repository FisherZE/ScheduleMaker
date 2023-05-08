import GUI.HomeGUI;
import Schedule.Schedule;
import Schedule.ScheduleMaker;

public class Start {
    public static void main(String[] args) {
        ScheduleMaker.initialize();
        HomeGUI gui = new HomeGUI();
        System.out.println("Hi");
    }
}
