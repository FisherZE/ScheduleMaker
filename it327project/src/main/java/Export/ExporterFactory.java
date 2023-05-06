package Export;

public class ExporterFactory{
    public Exporter createAppleCalendarExport(){
        Exporter exporter = new AppleCalendarExporter();
        return exporter;
    }

    public EmailExporter createEmailExporter(){
        EmailExporter exporter = new EmailExporter();
        return exporter;
    }

    public Exporter createGoogleExporter(){
        Exporter exporter = new GoogleExporter();
        return exporter;
    }

    public Exporter createOutlookExporter(){
        Exporter exporter = new OutlookExporter();
        return exporter;
    }

    public Exporter createSpreadsheetExporter(){
        Exporter exporter = new SpreadsheetExporter();
        return exporter;
    }
}
