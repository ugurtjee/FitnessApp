import java.text.SimpleDateFormat;
import java.util.Date;

public class WalkingData {
    private int time;
    private double distance;
    private Date date;
    
    public WalkingData(int time, double distance, Date date) {
        this.time = time;
        this.distance = distance;
        this.date = date;
    }
    
    public int getTime() {
        return time;
    }
    
    public double getDistance() {
        return distance;
    }
    
    public Date getDate() {
        return date;
    }

    public String getDateAsString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(date);
    }
}