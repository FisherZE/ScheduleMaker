package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.Class;
import Controllers.ExportController;
import Controllers.MakerController;
import Schedule.*;

public class ExportGUI extends JFrame implements ActionListener{
    //Basic setup
    private static JFrame frame = new JFrame("ISU Schedule Maker");
    private static JPanel container = new JPanel(new BorderLayout());
    private static JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private static JPanel centerPanel = new JPanel(new GridLayout(0,1));
    private static Schedule schedule;

    //Email
    private static JButton emailButton = new JButton("Send Email");
    private static JPanel emailPanel = new JPanel(new GridLayout(0, 2));
    private static JLabel emailLabel = new JLabel("Email");
    private static JTextField emailField = new JTextField("example@gmail.com");

    //Google Calender
    private static JButton googleButton = new JButton("Create Google Calender File");
    private static JPanel googlePanel = new JPanel(new GridLayout(0, 1));

    //Apple Calender
    private static JButton appleButton = new JButton("Create Apple Calender File");
    private static JPanel applePanel = new JPanel(new GridLayout(0, 1));

    //Outlook Calender
    private static JButton outlookButton = new JButton("Create Outlook Calender File");
    private static JPanel outlookPanel = new JPanel(new GridLayout(0, 1));

    //Download
    private static JButton downloadButton = new JButton("Download File");
    private static JPanel downloadPanel = new JPanel(new GridLayout(0, 2));
    private static JLabel downloadLabel = new JLabel("Download CSV File");

    public ExportGUI(Schedule sched){
        //Schedule
        schedule = sched;

        //Frame Setup
        frame.add(container);
        container.add(titlePanel, BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(700, 900));
        frame.setVisible(true);

        //Title Panel
        titlePanel.add(new JLabel("Export"));

        //Center Panel Setup
        //Email - CSV
        emailButton.addActionListener(this);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);
        emailPanel.add(emailButton);
        centerPanel.add(emailPanel);

        //Google Calender -ICS Exporter
        googleButton.addActionListener(this);
        googlePanel.add(googleButton);

        //Apple Calender -ICS Exporter
        appleButton.addActionListener(this);
        applePanel.add(appleButton);

        //Outlook Calender -ICS Exporter
        outlookButton.addActionListener(this);
        outlookPanel.add(outlookButton);

        //Download -CSV
        downloadButton.addActionListener(this);
        downloadPanel.add(downloadLabel);
        downloadPanel.add(new JLabel());
        downloadPanel.add(downloadButton);
        centerPanel.add(downloadPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == emailButton){
            if(emailField.getText().equals("")){
                return;
            }
            ExportController.exportEmail(emailField.getText(), "CSV", schedule);
        } else if(e.getSource() == downloadButton){
            ExportController.downloadFile("CSV", schedule);
        } else if (e.getSource() == googleButton){
            ExportController.downloadFile("ICS", schedule);
        } else if (e.getSource() == appleButton){
            ExportController.downloadFile("ICS", schedule);
        } else if (e.getSource() == outlookButton){
            ExportController.downloadFile("ICS", schedule);
        }
    }
    
}
