
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RunningData {
    private int time;
    private double distance;
    private Date date;
    private int heartRate;

    public RunningData(int time, double distance, int heartRate, Date date) {
        this.time = time;
        this.distance = distance;
        this.heartRate = heartRate;
        this.date = date;
    }
    
    public String getTempo() {
    double timeHours = time / 60.0;
    double distanceKm = distance / 1000.0;
    double speedKm = (distanceKm / timeHours);
    DecimalFormat df = new DecimalFormat("#.##");
    String tempo = df.format(speedKm);
    return tempo;
    }

    public int getTime() {
        return time;
    }
    
    public double getDistance() {
        return distance;
    }

    public int getHeartRate() {
        return heartRate;
    }
    
    public Date getDate() {
        return date;
    }

    public String getDateAsString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(date);
    }
}