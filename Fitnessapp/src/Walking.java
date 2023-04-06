import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Walking extends ActivitiesController{

    public Walking(Profile profile) {
        super(profile);
    }

    private ArrayList<WalkingData> walkingList = new ArrayList<>();
    private File dataFile = new File("src/walking_data.txt");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private Scanner scanner = new Scanner(System.in);

    public void showWalkingSubMenu() {
        loadDataWalking();

        System.out.println("1. Wandelingen overzicht");
        System.out.println("2. Wandeling registreren");
        System.out.println("3. Terug naar activiteiten");

        int ChoiceWalking = -1;
        while (ChoiceWalking < 1 || ChoiceWalking > 3) {
            try {
                ChoiceWalking = scanner.nextInt();

                switch (ChoiceWalking) {
                case 1:
                    viewWalkingData();
                    break;
                case 2:
                    addWalkingData();
                    break;
                case 3:
                    saveDataWalking();
                    ActivitiesController activitiesControllerObj = new Activities(profileObj); // Polymorphism!
                    activitiesControllerObj.backToActivities();
                    break;
                default:
                    System.out.println("Verkeerde keuze. Graag een geldige keuze maken!");
                    ChoiceWalking = -1;
                    break;
            }
            } catch (InputMismatchException | ParseException e) {
                System.out.println("Verkeerde input! Graag een geldige keuze maken!");
                scanner.nextLine();
            }
        }
    }

    public void backToSubmenuWalking() {
        Scanner menuinput = new Scanner(System.in);
        System.out.println("Druk op 'Enter' om terug te gaan.");
        menuinput.hasNextLine();
        showWalkingSubMenu();
        menuinput.close();
    }

    private void viewWalkingData() {
        if (walkingList.isEmpty()) {
            System.out.println("Nog geen activiteiten geregistreerd.");
            backToSubmenuWalking();
        }
        
        System.out.println("Wandelingen overzicht:");
        for (WalkingData entry : walkingList) {
            System.out.println("U heeft " + entry.getDistance() + " meter gewandeld in " + entry.getTime() + " minuten, geregistreerd op " + entry.getDateAsString());
        }
        backToSubmenuWalking();
    }
    
    private void addWalkingData() throws ParseException {
        System.out.print("Hoeveel minuten heeft u gewandeld? ");
        int time = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Hoeveel meter heeft u gewandeld? ");
        double distance = scanner.nextDouble();
        scanner.nextLine();
        
        Date date = new Date();
        WalkingData entry = new WalkingData(time, distance, date);
        walkingList.add(entry);
        
        System.out.println("Wandeling toegevoegd. Goed bezig!");
        backToSubmenuWalking();
    }

    private void loadDataWalking() {
        try {
            Scanner fileInput = new Scanner(dataFile);

            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                String[] fields = line.split(",");
                
                int time = Integer.parseInt(fields[0]);
                double distance = Double.parseDouble(fields[1]);
                Date date = dateFormat.parse(fields[2]);
                
                boolean isDuplicate = false;
                for (WalkingData data : walkingList) {
                    if (data.getDate().equals(date)) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (!isDuplicate) {
                    WalkingData entry = new WalkingData(time, distance, date);
                    walkingList.add(entry);
                }
            }
            
            fileInput.close();
        } catch (IOException | ParseException e) {
            System.out.println("Error met het laden van de data: ");
            backToSubmenuWalking();
        }
    }
    
    private void saveDataWalking() {
        try {
            FileWriter fileOutput = new FileWriter(dataFile, false);
    
            for (WalkingData entry : walkingList) {
                String line = entry.getTime() + "," + entry.getDistance() + "," + entry.getDateAsString() + "\n";
                fileOutput.write(line);
            }
    
            fileOutput.close();
        } catch (IOException e) {
            System.out.println("Error met het opslaan van de data.");
            backToSubmenuWalking();
        }
    }
}