package Classes;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class HSeminar extends Class{
    private String seminarId;
    private String secId;
    private boolean semesterHalf;

    public HSeminar(String seminarId, String secId, boolean semesterHalf, ArrayList<DayOfWeek> onDays, int startTime, int endTime, int creditHours, String classType){
        super(onDays, startTime, endTime, creditHours, classType);
        this.seminarId = seminarId;
        this.secId = secId;
        this.semesterHalf = semesterHalf;
    }

    @Override
    public String toString(){
        return this.seminarId + " " + secId;
    }

    public String getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(String seminarId) {
        this.seminarId = seminarId;
    }

    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

    public boolean isSemesterHalf() {
        return semesterHalf;
    }

    public void setSemesterHalf(boolean semesterHalf) {
        this.semesterHalf = semesterHalf;
    }
    
    public String getIdentifier(){
        return this.seminarId + this.secId;
    }

}