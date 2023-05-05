package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HomeGUI extends JFrame implements ActionListener {
    //Basic variable Setup
    private static JFrame frame = new JFrame("ISU Schedule Maker");
    private static JPanel container = new JPanel(new BorderLayout());
    private static JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private static JPanel centerPanel = new JPanel(new GridLayout(0,1));
    private static JPanel eventPanel = new JPanel(new GridLayout(0, 2, 5, 5));
    private static JPanel preferencePanel = new JPanel(new GridLayout(0, 2, 5, 5));
    private static JPanel classPanel = new JPanel(new GridLayout(0, 2, 5, 5));
    
    //Header
    private static JLabel title = new JLabel("Schedule Creator for ISU Students");

    //Event
    private static JLabel eventTitleLabel = new JLabel("Create a weekly event for you schedule here!");
    private static JPanel eventTitlePanel = new JPanel();
    private static JLabel labelEName = new JLabel("Event Name");
    private static JLabel labelEStart = new JLabel("Start Time");
    private static JLabel labelEEnd = new JLabel("End Time");
    private static JLabel labelEOn = new JLabel("Days It Occurs");
    private static JTextField eNameInput = new JTextField("Name");
    private static JTextField eStartInput = new JTextField("ex :13:00");
    private static JTextField eEndInput = new JTextField("ex: 13:30");
    private static JTextField eOnInput= new JTextField("M T W TH F");
    private static JButton addEvent = new JButton("Create Event");

    //Preferences
    private static JLabel minCredLabel = new JLabel("Minimum Credit Hours");
    private static JLabel maxCredLabel = new JLabel("Maximum Credit Hours");
    private static JLabel daysLabel = new JLabel("What Days you want Classes");
    private static JLabel earlyLabel = new JLabel("Earliest Class Time");
    private static JLabel lateLabel = new JLabel("Latest Class Time");
    private static JTextField minCredField = new JTextField("ex: 10");
    private static JTextField maxCredField = new JTextField("ex: 15");
    private static JTextField daysField = new JTextField("ex: M T W TH F");
    private static JTextField earlyField = new JTextField("ex: 9:00");
    private static JTextField lateField = new JTextField("ex: 17:00");
    private static JButton prefButton = new JButton("Add Preferences");

    //Class
    private static JLabel classNameLabel = new JLabel("Course Name");
    private static JTextField classNameField = new JTextField("ex: IT 168");
    private static JLabel honorSeminarLabel = new JLabel("Honors Seminar Name");
    private static JTextField honorSeminarField = new JTextField("ex: HON202A75 Section2");
    private static JLabel honorExplorationLabel = new JLabel("Honors Exploration Name");
    private static JTextField honorExplorationField = new JTextField("ex: A Quick Dive Into the World of Coffee");
    private static JButton classButton = new JButton("Search Course");
    private static JButton seminarButton = new JButton("Search Seminar");
    private static JButton explorationButton = new JButton("Search Exploration");

    //Schedules
    private static JPanel schedulesPanel = new JPanel(new GridLayout(0, 1));
    private static JLabel scheduleLabel = new JLabel("View Schedules");
    private static JButton scheduleButton = new JButton("View Schedules");

    public HomeGUI(){
        //Frame Setup
        frame.add(container);
        container.add(titlePanel, BorderLayout.NORTH);
        centerPanel.add(eventPanel);
        centerPanel.add(preferencePanel);
        centerPanel.add(classPanel);
        container.add(centerPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(700, 900));
        frame.setVisible(true);

        //Container Panel Setup
        container.setBorder(BorderFactory.createEmptyBorder(10, 10,10,10));

        //Title Panel Setup
        titlePanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        titlePanel.add(title);

        //Event Panel Setup
        eventPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        eventTitlePanel.add(eventTitleLabel);
        eventPanel.add(eventTitlePanel);
        eventPanel.add(new JLabel(""));
        eventPanel.add(labelEName);
        eventPanel.add(eNameInput);
        eventPanel.add(labelEStart);
        eventPanel.add(eStartInput);
        eventPanel.add(labelEEnd);
        eventPanel.add(eEndInput);
        eventPanel.add(labelEOn);
        eventPanel.add(eOnInput);
        eventPanel.add(addEvent);
        addEvent.addActionListener(this);

        //Preferences Panel Setup
        preferencePanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        preferencePanel.add(new JLabel("Add your schedule preferences here!"));
        preferencePanel.add(new JLabel("Simply Re-submit to update!"));
        preferencePanel.add(minCredLabel);
        preferencePanel.add(minCredField);
        preferencePanel.add(maxCredLabel);
        preferencePanel.add(maxCredField);
        preferencePanel.add(daysLabel);
        preferencePanel.add(daysField);
        preferencePanel.add(earlyLabel);
        preferencePanel.add(earlyField);
        preferencePanel.add(lateLabel);
        preferencePanel.add(lateField);
        preferencePanel.add(prefButton);
        prefButton.addActionListener(this);

        //Class
        classPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        classPanel.add(new JLabel("Search a course in Course Finder"));
        classPanel.add(new JLabel(""));
        classPanel.add(classNameLabel);
        classPanel.add(classNameField);
        classPanel.add(classButton);
        classButton.addActionListener(this);
        classPanel.add(new JLabel());
        classPanel.add(new JLabel("Search a honors seminar"));
        classPanel.add(new JLabel());
        classPanel.add(honorSeminarLabel);
        classPanel.add(honorSeminarField);
        classPanel.add(seminarButton);
        seminarButton.addActionListener(this);
        classPanel.add(new JLabel());
        classPanel.add(new JLabel("Search a honors exploration"));
        classPanel.add(new JLabel(""));
        classPanel.add(honorExplorationLabel);
        classPanel.add(honorExplorationField);
        classPanel.add(explorationButton);
        explorationButton.addActionListener(this);

        //Open SchedulesList GUI
        schedulesPanel.add(scheduleLabel);
        schedulesPanel.add(scheduleButton);
        scheduleButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addEvent){
            System.out.println("Event");
        } else if (e.getSource() == prefButton){
            System.out.println("Preferences");
        } else if (e.getSource() == classButton){
            System.out.println("Course");
        } else if (e.getSource() == seminarButton){
            System.out.println("Seminar");
        } else if (e.getSource() == explorationButton){
            System.out.println("Exploration");
        } else if (e.getSource() == scheduleButton){
            ScheduleListGUI slGUI = new ScheduleListGUI();
            System.out.println("View Schedules");

        }
    }
    
}
