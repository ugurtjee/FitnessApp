import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Swimming extends ActivitiesController {

    public Swimming(Profile profile) {
        super(profile);
    }

    private ArrayList<SwimmingData> swimmingList = new ArrayList<>();
    private File dataFile = new File("src/swimming_data.txt");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private Scanner scanner = new Scanner(System.in);

    public void showSwimmingSubMenu() {
        loadDataSwimming();

        System.out.println("1. Zwemmen overzicht");
        System.out.println("2. Zwemactiviteit registreren");
        System.out.println("3. Terug naar activiteiten");

        int ChoiceSwimming = -1;
        while (ChoiceSwimming < 1 || ChoiceSwimming > 3) {
            try {
                ChoiceSwimming = scanner.nextInt();

                switch (ChoiceSwimming) {
                    case 1:
                        viewSwimmingData();
                        break;
                    case 2:
                        addSwimmingData();
                        break;
                    case 3:
                        saveDataSwimming();
                        ActivitiesController activitiesControllerObj = new Activities(profileObj);
                        activitiesControllerObj.backToActivities();
                        break;
                    default:
                        System.out.println("Verkeerde keuze. Graag een geldige keuze maken!");
                        ChoiceSwimming = -1;
                        break;
                }
            } catch (InputMismatchException | ParseException e) {
                System.out.println("Verkeerde input! Graag een geldige keuze maken!");
                scanner.nextLine();
            }
        }
    }

    public void backToSubmenuSwimming() {
        Scanner menuinput = new Scanner(System.in);
        System.out.println("Druk op 'Enter' om terug te gaan.");
        menuinput.hasNextLine();
        showSwimmingSubMenu();
        menuinput.close();
    }

    private void viewSwimmingData() {
        if (swimmingList.isEmpty()) {
            System.out.println("Nog geen activiteiten geregistreerd.");
            backToSubmenuSwimming();
        }

        System.out.println("Zwemmen overzicht:");
        for (SwimmingData entry : swimmingList) {
            System.out.println("U heeft " + entry.getSwimLaps() + " kleine baantjes gezwommen in " + entry.getTime() + " minuten, wat gelijk is aan " + entry.getDistance() + " meter. Geregistreerd op " + entry.getDateAsString());
        }
        backToSubmenuSwimming();
    }

    private void addSwimmingData() throws ParseException {
        System.out.print("Hoeveel minuten heeft u gezwommen? ");
        int time = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Hoeveel kleine banen heeft u gezwommen(een kleine baan is gelijk aan 25 meter)? ");
        int swimLaps = scanner.nextInt();
        scanner.nextLine();

        Date date = new Date();
        SwimmingData entry = new SwimmingData(time, swimLaps, date);
        swimmingList.add(entry);

        System.out.println("Zwemactiviteit toegevoegd. Lekker bezig!");
        backToSubmenuSwimming();
    }

    private void loadDataSwimming() {
        try {
            Scanner fileInput = new Scanner(dataFile);
    
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                String[] fields = line.split(",");
    
                int time = Integer.parseInt(fields[0]);
                int laps = Integer.parseInt(fields[1]);
                Date date = dateFormat.parse(fields[2]);
    
                boolean isDuplicate = false;
                for (SwimmingData data : swimmingList) {
                    if (data.getDate().equals(date)) {
                        isDuplicate = true;
                        break;
                    }
                }
    
                if (!isDuplicate) {
                    SwimmingData entry = new SwimmingData(time, laps, date);
                    swimmingList.add(entry);
                }
            }
    
            fileInput.close();
        } catch (IOException | ParseException e) {
            System.out.println("Error met het laden van de data: ");
            backToSubmenuSwimming();
        }
    }
    
    private void saveDataSwimming() {
        try {
            FileWriter fileOutput = new FileWriter(dataFile, true);
    
            for (SwimmingData entry : swimmingList) {
                String line = entry.getSwimLaps() + "," + entry.getTime() + "," + entry.getDateAsString() + "\n";
                fileOutput.write(line);
            }
    
            fileOutput.close();
        } catch (IOException e) {
            System.out.println("Error met het opslaan van de data.");
            backToSubmenuSwimming();
        }
    }
}