package GUI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Schedule.Event;
import Schedule.Schedule;
import Schedule.ScheduleMaker;
import Classes.Class;
import Controllers.MakerController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;

public class ScheduleGUI extends JFrame implements ActionListener {
    private static JFrame frame = new JFrame("Schedule Viewer");
    private static JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private static JPanel centerPanel = new JPanel(new GridLayout(0,6,0,0));
    private static JPanel container = new JPanel(new BorderLayout());
    private static JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private static JButton exportButton = new JButton("Export");
    private static JButton deleteButton = new JButton("Delete Schedule");
    private static Schedule sched;

    public ScheduleGUI(Schedule schedule){
        //Schedule
        sched = schedule;

        //Frame Setup
        frame.add(container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(450, 300));
        frame.setVisible(true);

        //Title Setup
        container.add(titlePanel, BorderLayout.NORTH);
        titlePanel.add(new JLabel("Schedule"));
        
        //Table Setup
        ArrayList<Integer> startTimes = new ArrayList<>();

        //StartTimes
        int count = 0;
        for (Class class1 : schedule.getClasses()){
            if(!startTimes.contains(class1.getStartTime())){
                startTimes.add(count, class1.getStartTime());
                count++;
            }
        }
        for (Event event : schedule.getEvents()){
            if(!startTimes.contains(event.getStartTime())){
                startTimes.add(count, event.getStartTime());
                count++;
            }
        }
        
        Collections.sort(startTimes);

        String[][] data = new String[6][startTimes.size()];
        for(int i = 0; i < startTimes.size(); i++){
            data[0][i] = startTimes.get(i).toString();
        }
        
        //Add Classes
        for(int i = 1; i < 6; i++){
            for(int j = 0; j < schedule.getClasses().size(); j++){
                Class class1 = schedule.getClasses().get(j);
                if(class1.getOnDays().contains(DayOfWeek.MONDAY)){
                    for(int k = 0; k < startTimes.size(); k++){
                        if(startTimes.get(k) == class1.getStartTime()){
                            data[1][k] = class1.getIdentifier();
                        }
                    }
                }
                if(class1.getOnDays().contains(DayOfWeek.TUESDAY)){
                    for(int k = 0; k < startTimes.size(); k++){
                        if(startTimes.get(k) == class1.getStartTime()){
                            data[2][k] = class1.getIdentifier();
                        }
                    }
                }
                if(class1.getOnDays().contains(DayOfWeek.WEDNESDAY)){
                    for(int k = 0; k < startTimes.size(); k++){
                        if(startTimes.get(k) == class1.getStartTime()){
                            data[3][k] = class1.getIdentifier();
                        }
                    }
                }
                if(class1.getOnDays().contains(DayOfWeek.THURSDAY)){
                    for(int k = 0; k < startTimes.size(); k++){
                        if(startTimes.get(k) == class1.getStartTime()){
                            data[4][k] = class1.getIdentifier();
                        }
                    }
                }
                if(class1.getOnDays().contains(DayOfWeek.FRIDAY)){
                    for(int k = 0; k < startTimes.size(); k++){
                        if(startTimes.get(k) == class1.getStartTime()){
                            data[5][k] = class1.getIdentifier();
                        }
                    }
                }
            }
        }

        //Add Events
        for(int i = 1; i < 6; i++){
            for(int j = 0; j < schedule.getEvents().size(); j++){
                Event event = schedule.getEvents().get(j);
                if(event.getOnDays().contains(DayOfWeek.MONDAY)){
                    for(int k = 0; k < startTimes.size(); k++){
                        if(startTimes.get(k) == event.getStartTime()){
                            data[1][k] = event.getName();
                        }
                    }
                }
                if(event.getOnDays().contains(DayOfWeek.TUESDAY)){
                    for(int k = 0; k < startTimes.size(); k++){
                        if(startTimes.get(k) == event.getStartTime()){
                            data[2][k] = event.getName();
                        }
                    }
                }
                if(event.getOnDays().contains(DayOfWeek.WEDNESDAY)){
                    for(int k = 0; k < startTimes.size(); k++){
                        if(startTimes.get(k) == event.getStartTime()){
                            data[3][k] = event.getName();
                        }
                    }
                }
                if(event.getOnDays().contains(DayOfWeek.THURSDAY)){
                    for(int k = 0; k < startTimes.size(); k++){
                        if(startTimes.get(k) == event.getStartTime()){
                            data[4][k] = event.getName();
                        }
                    }
                }
                if(event.getOnDays().contains(DayOfWeek.FRIDAY)){
                    for(int k = 0; k < startTimes.size(); k++){
                        if(startTimes.get(k) == event.getStartTime()){
                            data[5][k] = event.getName();
                        }
                    }
                }
            }
        }

        //Build and Add table
        String[] columns = {"Time", "Monday", "Tuesday", "Wednesday", "Thrusday", "Friday"};
        for(int i = 0; i < 6; i++){
            JLabel label = new JLabel(columns[i]);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            centerPanel.add(label);
        }
        for(int i = 0; i < startTimes.size(); i++){
            for(int j = 0; j < 6; j++){
                JLabel label = new JLabel(data[j][i]);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                centerPanel.add(label);
            }
        }
        container.add(centerPanel, BorderLayout.CENTER);

        bottomPanel.add(exportButton);
        bottomPanel.add(deleteButton);
        deleteButton.addActionListener(this);
        exportButton.addActionListener(this);
        container.add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exportButton){
            ExportGUI gui = new ExportGUI(sched);
        } else if (e.getSource() == deleteButton){
            ScheduleMaker.removeSchedule(sched);
            ScheduleListGUI.getFrame().validate();
        }
    }
    
}
