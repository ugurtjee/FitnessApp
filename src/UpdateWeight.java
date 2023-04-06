import java.util.Scanner;

public class UpdateWeight {
    public void bewerkGewicht(Profile profile) 
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Voer je nieuwe gewicht in (in kg)");
        profile.SetWeight(s.nextDouble());
        profile.menuObj.backToMenu();
        s.close();
    }
}