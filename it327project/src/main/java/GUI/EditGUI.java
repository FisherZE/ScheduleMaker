package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Controllers.MakerController;

public class EditGUI extends JFrame implements ActionListener{

    //Frame
    private static JFrame frame = new JFrame("ISU Schedule Maker");
    private static JPanel container = new JPanel(new BorderLayout());
    private static JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private static JPanel centerPanel = new JPanel(new GridLayout(0,1));
    private static JScrollPane scrollPane = new JScrollPane(centerPanel);
    private static JPanel classEditPanel = new JPanel(new GridLayout(0, 2));
    private static JPanel preferenceEditPanel = new JPanel(new GridLayout(0, 2));

    //Class edit
    private static JButton editClassButton = new JButton("Edit Preferences");
    private static JLabel className = new JLabel("Class Name");
    private static JLabel credLabel = new JLabel("Credit Hours");
    private static JLabel startLabel = new JLabel("Start Time");
    private static JLabel endTime = new JLabel("End Time");
    private static JLabel onDaysLabel = new JLabel("What Days");
    private static JTextField classNameField = new JTextField("ex: IT351 2");
    private static JTextField credField = new JTextField("ex: 4");
    private static JTextField startField = new JTextField("ex: 1100");
    private static JTextField endField = new JTextField("ex: 1245");
    private static JTextField onDaysField = new JTextField("ex: M T TH");
    private static JButton deleteClassButton = new JButton("Remove Class");

    //Preferences edit
    private static JButton editPrefButton = new JButton("Edit Preferences");
    private static JLabel minCredLabel = new JLabel("Minimum Credits");
    private static JLabel maxCredLabel = new JLabel("Maximum Credits");
    private static JLabel earlyLabel = new JLabel("Earliest Time");
    private static JLabel lateLabel = new JLabel("Latest Time");
    private static JLabel elligbleLabel = new JLabel("Elligible Days");
    private static JTextField minCredField = new JTextField("ex: 12");
    private static JTextField maxCredField = new JTextField("ex: 20");
    private static JTextField earlyField = new JTextField("ex: 0800");
    private static JTextField lateField = new JTextField("ex: 1700");
    private static JTextField elligibleField = new JTextField("ex: M T W TH");
    private static JButton deletePrefButton = new JButton("Reset Preferences");

    public EditGUI(){
        //Frame setup
        frame.add(container);
        container.add(titlePanel, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(700, 900));
        frame.setVisible(true);

        //Class Edit
        classEditPanel.add(new JLabel("Edit Class"));
        classEditPanel.add(new JLabel(""));
        classEditPanel.add(className);
        classEditPanel.add(classNameField);
        classEditPanel.add(credLabel);
        classEditPanel.add(credField);
        classEditPanel.add(startLabel);
        classEditPanel.add(startField);
        classEditPanel.add(endTime);
        classEditPanel.add(endField);
        classEditPanel.add(onDaysLabel);
        classEditPanel.add(onDaysField);
        classEditPanel.add(editClassButton);
        classEditPanel.add(deleteClassButton);
        centerPanel.add(preferenceEditPanel);
        editClassButton.addActionListener(this);
        deleteClassButton.addActionListener(this);

        //Preference 
        preferenceEditPanel.add(new JLabel("Edit Preferences"));
        preferenceEditPanel.add(new JLabel(""));
        preferenceEditPanel.add(minCredLabel);
        preferenceEditPanel.add(minCredField);
        preferenceEditPanel.add(maxCredLabel);
        preferenceEditPanel.add(maxCredField);
        preferenceEditPanel.add(earlyLabel);
        preferenceEditPanel.add(earlyField);
        preferenceEditPanel.add(lateLabel);
        preferenceEditPanel.add(lateField);
        preferenceEditPanel.add(elligbleLabel);
        preferenceEditPanel.add(elligibleField);
        preferenceEditPanel.add(editPrefButton);
        preferenceEditPanel.add(deletePrefButton);
        editPrefButton.addActionListener(this);
        deletePrefButton.addActionListener(this);
        centerPanel.add(preferenceEditPanel);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == editClassButton){
            String[] params = {classNameField.getText(), credField.getText(), startField.getText(), endField.getText()};
            String[] days = onDaysField.getText().split(" ");
            ArrayList<DayOfWeek> onDays = new ArrayList<>();
            for (int i = 0; i < days.length; i++){
                if(days[i].equals("M")){
                    onDays.add(DayOfWeek.MONDAY);
                }
                if(days[i].equals("T")){
                    onDays.add(DayOfWeek.TUESDAY);
                }
                if(days[i].equals("W")){
                    onDays.add(DayOfWeek.WEDNESDAY);
                }
                if(days[i].equals("TH")){
                    onDays.add(DayOfWeek.THURSDAY);
                }
                if(days[i].equals("F")){
                    onDays.add(DayOfWeek.FRIDAY);
                }
            }
            MakerController.editClass(params, onDays);
        } else if (e.getSource() == deleteClassButton){
            MakerController.removeClass(classNameField.getText());
        } else if (e.getSource() == editPrefButton){
            String[] params = {minCredField.getText(), maxCredField.getText(), earlyField.getText(), lateField.getText()};
            String[] days = elligibleField.getText().split(" ");
            ArrayList<DayOfWeek> onDays = new ArrayList<>();
            for (int i = 0; i < days.length; i++){
                if(days[i].equals("M")){
                    onDays.add(DayOfWeek.MONDAY);
                }
                if(days[i].equals("T")){
                    onDays.add(DayOfWeek.TUESDAY);
                }
                if(days[i].equals("W")){
                    onDays.add(DayOfWeek.WEDNESDAY);
                }
                if(days[i].equals("TH")){
                    onDays.add(DayOfWeek.THURSDAY);
                }
                if(days[i].equals("F")){
                    onDays.add(DayOfWeek.FRIDAY);
                }
            }
            MakerController.updatePreferences(params, onDays);
        } else if (e.getSource() == deletePrefButton){
            MakerController.deletePreferences();
        }
    }
    
}
