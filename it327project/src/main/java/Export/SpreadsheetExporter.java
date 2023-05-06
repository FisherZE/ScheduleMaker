package Export;

import Converter.CSVConverter;
import Schedule.Schedule;

public class SpreadsheetExporter extends Exporter{
    public void callConverter(Schedule schedule)
    {
        CSVConverter converter = new CSVConverter();
        converter.outputCSV(schedule);
        CSVConverter.getFileLocations().clear();
    }
}