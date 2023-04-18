import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Profile {
    InputChecker checkInput = new InputChecker();
    Menu menuObj = new Menu(this);
    private String naam;
    private String achternaam;
    private String geboortedatum;
    private String geslacht;
    private String device;
    private int age;
    private double weight;
    private double length;
    private double BMI;
    private double VO2Max;

    public void showProfile() {
        System.out.println("Naam: " + naam);
        System.out.println("Achternaam: " + achternaam);
        System.out.println("Geboortedatum: " + geboortedatum);
        System.out.println("Leeftijd: " + GetAge());
        System.out.println("Geslacht: " + geslacht);
        System.out.println("Device: " + device);
        System.out.println("Gewicht: " + weight);
        System.out.println("Lengte: " + length);
        System.out.printf("BMI: %.2f \n", GetBMI());
        System.out.println("VO2Max: " + VO2Max);
        menuObj.backToMenu();
    }

    public void editProfile() {
        Scanner input = new Scanner(System.in);
        System.out.println("Wat is uw naam?");
        naam = input.nextLine();
        System.out.println("Wat is uw achternaam?");
        achternaam = input.nextLine();
        System.out.println("Wat is uw geboortedatum(Formaat: DD-MM-YYYY)?");
        geboortedatum = input.nextLine();
        System.out.println("Wat is uw geslacht?");
        geslacht = input.nextLine();
        System.out.println("Welke apparaat gebruikt u voor de app(bijvoorbeeld Android of IoS)?");
        device = input.nextLine();
        weight = checkInput.handleDoubleInput("Wat is uw gewicht in kg?");
        length = checkInput.handleDoubleInput("Wat is uw lengte(in meters)?");
        VO2Max = checkInput.handleDoubleInput("Wat is uw VO2 Max(in METS)?");
        showProfile();
        menuObj.backToMenu();
        input.close();
    }

    public void createProfile() {
        Scanner input = new Scanner(System.in);
        System.out.println("Wat is uw naam?");
        naam = input.nextLine();
        System.out.println("Wat is uw achternaam?");
        achternaam = input.nextLine();
        System.out.println("Wat is uw geboortedatum(Formaat: DD-MM-YYYY)?");
        geboortedatum = input.nextLine();
        System.out.println("Wat is uw geslacht?");
        geslacht = input.nextLine();
        System.out.println("Welke apparaat gebruikt u voor de app(bijvoorbeeld Android of IoS)?");
        device = input.nextLine();
        weight = checkInput.handleDoubleInput("Wat is uw gewicht in kg?");
        length = checkInput.handleDoubleInput("Wat is uw lengte(in meters)?");
        VO2Max = checkInput.handleDoubleInput("Wat is uw VO2 Max(in METS)?");
        menuObj.showMenu();
        input.close();
    }

    public double GetWeight() {
        return weight;
    }

    public int GetAge() {
        String[] myArray = geboortedatum.split("-");
        int day = Integer.parseInt(myArray[0]);
        int month = Integer.parseInt(myArray[1]);
        int year = Integer.parseInt(myArray[2]);
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(year, month, day);
        Period p = Period.between(birthday, today);
        age = p.getYears();
        return age;
    }

    public double GetLength() {
        return length;
    }

    public double GetBMI() {
        BMI = (weight / (length * length));
        return BMI;
    }

    public void SetWeight(double weight) {
        this.weight = weight;
    }
}