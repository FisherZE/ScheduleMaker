package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Schedule.Schedule;
import Schedule.ScheduleMaker;

public class ScheduleListGUI extends JFrame implements ActionListener{

     //Basic variable Setup
     private static JFrame frame = new JFrame("Generated Schedules");
     private static JPanel container = new JPanel(new BorderLayout());
     private static JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
     private static JPanel centerPanel = new JPanel(new GridLayout(0,1));
     private static JPanel schedulePanel = new JPanel(new GridLayout(0, 5, 5, 5));
     private static JScrollPane pane = new JScrollPane(schedulePanel);
     private static ArrayList<Schedule> schedules = ScheduleMaker.getSchedules();

     public ScheduleListGUI(){
        ArrayList<Schedule> s = schedules;
        frame.add(container);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Do something before closing the frame
                frame.setVisible(false);
                frame.dispose();
            }
        });
        frame.pack();
        frame.setMinimumSize(new Dimension(900, 800));
        frame.setVisible(true);
        container.add(titlePanel, BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(pane);

        //Title Panel
        JLabel title = new JLabel("Generated Schedule List");
        titlePanel.add(title);

        int count = 1;
        schedulePanel.add(new JLabel("Schedule #"));
        schedulePanel.add(new JLabel("Earliest Class"));
        schedulePanel.add(new JLabel("Latest Class"));
        schedulePanel.add(new JLabel("Total Credit Hours"));
        schedulePanel.add(new JLabel("Click to view Schedule"));
        for(Schedule schedule : schedules){
            JLabel label1 = new JLabel("Schedule "+ count);
            JLabel label2 = new JLabel(schedule.getClasses().get(0).getStartTime()+ "");
            JLabel label3 = new JLabel(schedule.getClasses().get(schedule.getClasses().size()-1).getEndTime() + "");
            JLabel label4 = new JLabel(schedule.getCredithours() + "");
            JButton button = new JButton("View Schedule " + count);
            button.addActionListener(this);
            schedulePanel.add(label1);
            schedulePanel.add(label2);
            schedulePanel.add(label3);
            schedulePanel.add(label4);
            schedulePanel.add(button);
            count++;
        }
     }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 1; i < schedules.size() + 1; i++){
            JButton button = (JButton) e.getSource();
            System.out.println(i + " " + button.getText());
            System.out.println(button.getText().equals(("View Schedule " + i)));
            if(button.getText().equals(("View Schedule " + i))){
                schedules.get(i-1).display();
            }
        }
    }

    public static JFrame getFrame(){
        return frame;
    }
    
}

