package Export;

public abstract class Exporter{
    private String desiredType;
    
    public void setFileExport(String wantType)
    {
        this.desiredType = wantType;
    }
}
