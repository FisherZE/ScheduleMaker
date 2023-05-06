package Export;

import Schedule.Schedule;

public abstract class Exporter{
    private String desiredType;

    public abstract void callConverter(Schedule schedule);
    
    public void setFileExport(String wantType)
    {
        this.desiredType = wantType;
    }
}