package Export;

import Converter.ICSConverter;
import Schedule.Schedule;

public class GoogleExporter extends Exporter{
    public void callConverter(Schedule schedule)
    {
        ICSConverter converter = new ICSConverter();
        converter.outputICS(schedule);
        ICSConverter.getFileLocations().clear();
    }
}