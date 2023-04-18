import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    Profile profileObj;

    public Menu(Profile profile) {
        profileObj = profile;
    }

    public void showMenu() {
        System.out.println("Hallo, Welkom bij de Fitness App!");
        System.out.println("1. Profiel bekijken");
        System.out.println("2. Profiel bewerken");
        System.out.println("3. Activiteiten");
        System.out.println("4. Coaches");
        System.out.println("5. Gezondheidsadvies");
        System.out.println("6. Gewicht bijwerken");

        int ChoiceMenu = 0;
        while (ChoiceMenu < 1 || ChoiceMenu > 6) {
            try {
                ChoiceMenu = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Inputmismatch!");
                scanner.nextLine();
            }

            switch (ChoiceMenu) {
                case 1:
                    profileObj.showProfile();
                    break;
                case 2:
                    profileObj.editProfile();
                    break;
                case 3:
                    Activities activiteitenObj = new Activities(profileObj);
                    activiteitenObj.showActivities();
                    break;
                case 4:
                    Coach coach = new Coach();
                    coach.ShowCoaches(profileObj);
                    break;
                case 5:
                    HealthAdvice healthAdviceObj = new HealthAdvice();
                    healthAdviceObj.giveAdvice(profileObj);
                    break;
                case 6:
                    UpdateWeight updateWeightObj = new UpdateWeight();
                    updateWeightObj.bewerkGewicht(profileObj);
                    break;
                default:
                    System.out.println("Verkeerde keuze. Graag een geldige keuze maken!");
                    ChoiceMenu = -1;
                    break;
            }
        }
    }

    public void backToMenu() {
        Scanner menuinput = new Scanner(System.in);
        System.out.println("Druk op 'Enter' om terug naar het menu te gaan.");
        menuinput.hasNextLine();
        showMenu();
        menuinput.close();
    }
}