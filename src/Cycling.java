import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cycling extends ActivitiesController{

    public Cycling(Profile profile) {
        super(profile);
    }
    
    private ArrayList<CyclingData> cyclingList = new ArrayList<>();
    private File dataFile = new File("src/cycling_data.txt");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private Scanner scanner = new Scanner(System.in);

    public void showCyclingSubMenu() {
        loadDataCycling();

        System.out.println("1. Fietsen overzicht");
        System.out.println("2. Fietsen registreren");
        System.out.println("3. Terug naar activiteiten");

        int ChoiceCycling = -1;
        while (ChoiceCycling < 1 || ChoiceCycling > 3) {
            try {
                ChoiceCycling = scanner.nextInt();

                switch (ChoiceCycling) {
                case 1:
                    viewCyclingData();
                    break;
                case 2:
                    addCyclingData();
                    break;
                case 3:
                    saveDataCycling();
                    ActivitiesController activitiesControllerObj = new Activities(profileObj);
                    activitiesControllerObj.backToActivities();
                    break;
                default:
                    System.out.println("Verkeerde keuze. Graag een geldige keuze maken!");
                    ChoiceCycling = -1;
                    break;
            }
            } catch (InputMismatchException | ParseException e) {
                System.out.println("Verkeerde input! Graag een geldige keuze maken!");
                scanner.nextLine();
            }
        }
    }

    public void backToSubmenuCycling()
    {
        Scanner menuinput = new Scanner(System.in);
        System.out.println("Druk op 'Enter' om terug te gaan.");
        menuinput.hasNextLine();
        showCyclingSubMenu();
        menuinput.close();
    }

    private void viewCyclingData() {
        if (cyclingList.isEmpty()) {
            System.out.println("Nog geen activiteiten geregistreerd.");
            backToSubmenuCycling();
        }
        
        System.out.println("Fietsen overzicht:");
        for (CyclingData entry : cyclingList) {
            System.out.println("U heeft " + entry.getDistance() + " meter gefietst in " + entry.getTime() + " minuten, met een snelheid van " + entry.getTempo()
             + " km/h. Uw gemeten hartslag was " + entry.getHeartRate() + " slagen per minuut. Geregistreerd op " + entry.getDateAsString());
        }
        backToSubmenuCycling();
    }
    
    private void addCyclingData() throws ParseException {
        System.out.print("Hoeveel minuten heeft u gefietst? ");
        int time = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Hoeveel meter heeft u gefietst? ");
        double distance = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Wat is uw gemeten hartslag? ");
        int heartRate = scanner.nextInt();
        scanner.nextLine();

        Date date = new Date();
        CyclingData entry = new CyclingData(time, distance, heartRate, date);
        cyclingList.add(entry);
        
        System.out.println("Fietsactiviteit toegevoegd. Lekker bezig!");
        backToSubmenuCycling();
    }

    private void loadDataCycling() {
        try {
            Scanner fileInput = new Scanner(dataFile);
    
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                String[] fields = line.split(",");
    
                int time = Integer.parseInt(fields[0]);
                double distance = Double.parseDouble(fields[1]);
                int heartRate = Integer.parseInt(fields[2]);
                Date date = dateFormat.parse(fields[3]);
    
                boolean isDuplicate = false;
                for (CyclingData data : cyclingList) {
                    if (data.getDate().equals(date)) {
                        isDuplicate = true;
                        break;
                    }
                }
    
                if (!isDuplicate) {
                    CyclingData entry = new CyclingData(time, distance, heartRate, date);
                    cyclingList.add(entry);
                }
            }
    
            fileInput.close();
        } catch (IOException | ParseException e) {
            System.out.println("Error met het laden van de data: " + e.getMessage());
            backToSubmenuCycling();
        }
    }
    
    private void saveDataCycling() {
        try {
            FileWriter fileOutput = new FileWriter(dataFile, true);
    
            for (CyclingData entry : cyclingList) {
                String line = entry.getTime() + "," + entry.getDistance() + "," + entry.getHeartRate() + "," + entry.getDateAsString() + "\n";
                fileOutput.write(line);
            }
    
            fileOutput.close();
        } catch (IOException e) {
            System.out.println("Error met het opslaan van de data: " + e.getMessage());
            backToSubmenuCycling();
        }
    }
}