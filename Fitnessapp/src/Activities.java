import java.util.InputMismatchException;
import java.util.Scanner;

public class Activities extends ActivitiesController{
    public Activities(Profile profile) {
        super(profile);
    }

    private Scanner scanner = new Scanner(System.in);

    public void showActivities()
    {
        System.out.println("1. Wandelen");
        System.out.println("2. Hardlopen");
        System.out.println("3. Fietsen");
        System.out.println("4. Zwemmen");
        System.out.println("5. Terug naar het menu");

        int ChoiceActivities = -1;
        while (ChoiceActivities < 1 || ChoiceActivities > 5) {
            try {
                ChoiceActivities = scanner.nextInt();

                switch (ChoiceActivities) {
                case 1:
                    Walking walking = new Walking(profileObj);
                    walking.showWalkingSubMenu();
                    break;
                case 2:
                    Running running = new Running(profileObj);
                    running.showRunningSubMenu();
                    break;
                case 3:
                    Cycling cycling = new Cycling(profileObj);
                    cycling.showCyclingSubMenu();
                    break;
                case 4:
                    Swimming swimming = new Swimming(profileObj);
                    swimming.showSwimmingSubMenu();
                    break;
                case 5:
                    profileObj.menuObj.backToMenu();
                    break;
                default:
                    System.out.println("Verkeerde keuze. Graag een geldige keuze maken!");
                    ChoiceActivities = -1;
                    break;
            }
            } catch (InputMismatchException e) {
                System.out.println("Verkeerde input! Graag een geldige keuze maken!");
                scanner.nextLine();
            }
        }
    }

    public void backToActivities()
    {
        Scanner menuinput = new Scanner(System.in);
        System.out.println("Druk op 'Enter' om terug naar activiteiten te gaan.");
        menuinput.hasNextLine();
        showActivities();
        menuinput.close();
    }
}