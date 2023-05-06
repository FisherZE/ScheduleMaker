package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.Class;
import Controllers.MakerController;
import Schedule.*;

public class ConfirmGUI extends JFrame implements ActionListener{
    //Frames and Panels
    private static JFrame frame = new JFrame("Confirmation Window");
    private static JPanel container = new JPanel(new BorderLayout());
    private static JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private static JPanel centerPanel = new JPanel(new GridLayout(0,1));
    private static JButton yesButton = new JButton("Yes");
    private static JButton noButton = new JButton("No");
    private static String constructorType = "";
    private Class class1;
    private Event event;

    public ConfirmGUI(Class class1){
        constructorType = "class";
        this.class1 = class1;

        //Frame Setup
        frame.add(container);
        container.add(titlePanel, BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setVisible(true);

        //Title Panel
        titlePanel.add(new JLabel("Confirmation Message"));

        //Center Panel
        centerPanel.add(new JLabel(class1.getIdentifier()));
        centerPanel.add(new JLabel(""));
        centerPanel.add(yesButton);
        centerPanel.add(noButton);
        yesButton.addActionListener(this);
        noButton.addActionListener(this);
    }

    public ConfirmGUI(Event event){
        constructorType = "event";
        this.event = event;

        //Frame Setup
        frame.add(container);
        container.add(titlePanel, BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setVisible(true);

        //Title Panel
        titlePanel.add(new JLabel("Confirmation Message"));

        //Center Panel
        centerPanel.add(new JLabel(event.getName()));
        centerPanel.add(new JLabel(""));
        centerPanel.add(yesButton);
        centerPanel.add(noButton);
        yesButton.addActionListener(this);
        noButton.addActionListener(this);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == yesButton){
            frame.dispose();
        } else if(e.getSource() == noButton && constructorType.equals("class")){
            MakerController.removeClass(class1.toString());
        } else if(e.getSource() == noButton && constructorType.equals("event")){
            MakerController.removeEvent(event);
        }
    }

}