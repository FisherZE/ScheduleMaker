package Classes;

import java.time.*;
import java.util.*;

public abstract class Class{

    private ArrayList<DayOfWeek> onDays;
    private int startTime;
    private int endTime;
    private int creditHours;
    private String classType;

    public abstract ArrayList<DayOfWeek> getOnDays();
    public abstract String getIdentifier();
    public abstract int getStartTime();
    public abstract int getEndTime();
    public abstract int getCreditHours();
}
