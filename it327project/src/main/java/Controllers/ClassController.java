package Controllers;

import Classes.Class;

public class ClassController{
    public static String findCourse(String[] name){
        boolean flag = false;
        
        if (flag){
            return "Course Found";
        } else {
            return "Course not Found";
        }
    }

    public static String addClass(Class class1) {
        return "Successful";
    }
}