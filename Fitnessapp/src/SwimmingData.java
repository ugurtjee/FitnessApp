import java.text.SimpleDateFormat;
import java.util.Date;

public class SwimmingData {
    private int time;
    private int laps;
    private Date date;

    public SwimmingData(int time, int laps, Date date) {
        this.time = time;
        this.laps = laps;
        this.date = date;
    }

    public int getDistance() {
        int distance = laps * 25;
        return distance;
    }

    public int getTime() {
        return time;
    }

    public int getSwimLaps() {
        return laps;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateAsString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(date);
    }
}