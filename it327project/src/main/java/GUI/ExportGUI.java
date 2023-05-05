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
    private static JLabel FileTypeLabel = new JLabel("File Type");
    private static JTextField FileTypeField = new JTextField("ex: ICS or CSV");

    //Download
    private static JButton downloadButton = new JButton("Download File");
    private static JPanel downloadPanel = new JPanel(new GridLayout(0, 2));
    private static JLabel downloadLabel = new JLabel("Download");

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
        //Email
        emailButton.addActionListener(this);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);
        emailPanel.add(FileTypeLabel);
        emailPanel.add(FileTypeField);
        emailPanel.add(emailButton);
        centerPanel.add(emailPanel);

        //Download
        downloadButton.addActionListener(this);
        downloadPanel.add(downloadLabel);
        downloadPanel.add(new JLabel());
        downloadPanel.add(FileTypeLabel);
        downloadPanel.add(FileTypeField);
        downloadPanel.add(downloadButton);
        centerPanel.add(downloadPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == emailButton){
            if(emailField.getText().equals("") || FileTypeField.getText().equals("")){
                return;
            }
            ExportController.exportEmail(emailField.getText(), FileTypeField.getText(), schedule);
        } else if(e.getSource() == downloadButton){
            if(FileTypeField.getText().equals("")){
                return;
            }
            ExportController.downloadFile(FileTypeField.getText(), schedule);
        }
    }
    
}
