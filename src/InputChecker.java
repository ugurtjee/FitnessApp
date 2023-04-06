import java.util.InputMismatchException;
import java.util.Scanner;

// public class InputChecker {
//     private Scanner s = new Scanner(System.in);

//     public int checkInput(int[] acceptInput) {
//         Boolean succes = false;
//         int input = 0;
    
//         while(s.hasNextLine()) {
//             try {    
//                 input = s.nextInt();
            
//                 for (int i : acceptInput) {
//                     if(i == input) 
//                         succes = true;
//                 }

//                 if(succes == false)
//                     System.out.println("Verkeerde input! Graag een geldige keuze maken!");
//                 else
//                     break;
            
//             } catch (InputMismatchException e) {
//             System.out.println("Verkeerde input! Graag een geldige keuze maken!");
//             s.nextLine();
//         }
        
//         }
//         return input;
//     }
public class InputChecker {
    private Scanner scanner = new Scanner(System.in);

    public int handleIntInput(String message) {
        int input = 0;
        boolean validInput = false;
        do {
            System.out.println(message);
            try {
                input = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Ongeldige invoer. Graag een geheel getal invoeren.");
                scanner.nextLine(); // Consume the newline character left in the buffer
            }
        } while (!validInput);
        return input;
    }
    
    public double handleDoubleInput(String message) {
        double input = 0;
        boolean validInput = false;
        do {
            System.out.println(message);
            try {
                input = Double.parseDouble(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige invoer. Graag een getal invoeren.");
            }
        } while (!validInput);
        return input;
    }
}