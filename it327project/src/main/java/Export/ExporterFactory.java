package Export;

public class ExporterFactory{
    public static Exporter createAppleCalendarExport(){
        Exporter exporter = new AppleCalendarExporter();
        return exporter;
    }

    public static EmailExporter createEmailExporter(){
        EmailExporter exporter = new EmailExporter();
        return exporter;
    }

    public static Exporter createGoogleExporter(){
        Exporter exporter = new GoogleExporter();
        return exporter;
    }

    public static Exporter createOutlookExporter(){
        Exporter exporter = new OutlookExporter();
        return exporter;
    }

    public static Exporter createSpreadsheetExporter(){
        Exporter exporter = new SpreadsheetExporter();
        return exporter;
    }
}