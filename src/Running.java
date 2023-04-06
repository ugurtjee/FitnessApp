import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Running extends ActivitiesController{

    public Running(Profile profile) {
        super(profile);
    }
    
    private ArrayList<RunningData> runningList = new ArrayList<>();
    private File dataFile = new File("src/running_data.txt");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private Scanner scanner = new Scanner(System.in);

    public void showRunningSubMenu() {
        loadDataRunning();

        System.out.println("1. Hardlopen overzicht");
        System.out.println("2. Hardlopen registreren");
        System.out.println("3. Terug naar activiteiten");

        int ChoiceRunning = -1;
        while (ChoiceRunning < 1 || ChoiceRunning > 3) {
            try {
                ChoiceRunning = scanner.nextInt();

                switch (ChoiceRunning) {
                case 1:
                    viewRunningData();
                    break;
                case 2:
                    addRunningData();
                    break;
                case 3:
                    saveDataRunning();
                    ActivitiesController activitiesControllerObj = new Activities(profileObj);
                    activitiesControllerObj.backToActivities();
                    break;
                default:
                    System.out.println("Verkeerde keuze. Graag een geldige keuze maken!");
                    ChoiceRunning = -1;
                    break;
            }
            } catch (InputMismatchException | ParseException e) {
                System.out.println("Verkeerde input! Graag een geldige keuze maken!");
                scanner.nextLine();
            }
        }
    }

    public void backToSubmenuRunning()
    {
        Scanner menuinput = new Scanner(System.in);
        System.out.println("Druk op 'Enter' om terug te gaan.");
        menuinput.hasNextLine();
        showRunningSubMenu();
        menuinput.close();
    }

    private void viewRunningData() {
        if (runningList.isEmpty()) {
            System.out.println("Nog geen activiteiten geregistreerd.");
            backToSubmenuRunning();
        }
        
        System.out.println("Hardlopen overzicht:");
        for (RunningData entry : runningList) {
            System.out.println("U heeft " + entry.getDistance() + " meter hardgelopen in " + entry.getTime() + " minuten, met een snelheid van " + entry.getTempo()
             + " km/h. Uw gemeten hartslag was " + entry.getHeartRate() + " slagen per minuut. Geregistreerd op " + entry.getDateAsString());
        }
        backToSubmenuRunning();
    }
    
    private void addRunningData() throws ParseException {
        System.out.print("Hoeveel minuten heeft u hardgelopen? ");
        int time = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Hoeveel meter heeft u hardgelopen? ");
        double distance = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Wat is uw gemeten hartslag? ");
        int heartRate = scanner.nextInt();
        scanner.nextLine();

        Date date = new Date();
        RunningData entry = new RunningData(time, distance, heartRate, date);
        runningList.add(entry);
        
        System.out.println("Hardloopactiviteit toegevoegd. Lekker bezig!");
        backToSubmenuRunning();
    }

    private void loadDataRunning() {
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
                for (RunningData data : runningList) {
                    if (data.getDate().equals(date)) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (!isDuplicate) {
                    RunningData entry = new RunningData(time, distance, heartRate, date);
                    runningList.add(entry);
                }
            }
            
            fileInput.close();
        } catch (IOException | ParseException e) {
            System.out.println("Error met het laden van de data: " + e.getMessage());
            backToSubmenuRunning();
        }
    }
    
    private void saveDataRunning() {
        try {
            FileWriter fileOutput = new FileWriter(dataFile, false);
    
            for (RunningData entry : runningList) {
                String line = entry.getTime() + "," + entry.getDistance() + "," + entry.getHeartRate() + "," + entry.getDateAsString() + "\n";
                fileOutput.write(line);
            }
    
            fileOutput.close();
        } catch (IOException e) {
            System.out.println("Error met het opslaan van de data: " + e.getMessage());
            backToSubmenuRunning();
        }
    }
}