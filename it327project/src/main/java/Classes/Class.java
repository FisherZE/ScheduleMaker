package Classes;

import java.time.*;
import java.util.*;

public abstract class Class{

    public abstract ArrayList<DayOfWeek> getOnDays();
    public abstract String getIdentifier();
    public abstract int getStartTime();
    public abstract int getEndTime();
    public abstract int getCreditHours();
}
