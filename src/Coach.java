public class Coach {

    CoachData[] coaches = {
            new CoachData("Kees", "Babbelaar", 35, "Zwemcoach", "Kees.Babbelaar@hotmail.com", "0652817495"),
            new CoachData("Laura", "van Dijk", 28, "Fitness coach", "Laura.van.Dijk@hotmail.com", "0685947612"),
            new CoachData("Wesley", "Sneijder", 38, "Hardloop coach", "Wesley.Sneijder@hotmail.com", "0685947325"),
            new CoachData("Jans", "Leerdam", 32, "Fiets coach", "Jans.Leerdam@hotmail.com", "0695725465")
    };

    public void ShowCoaches(Profile profile) {
        System.out.printf("%-10s%-15s%-10s%-20s%-30s%-10s\n", "Naam", "Achternaam", "Leeftijd", "Type", "Email",
                "Telefoonnummer");
        System.out.println(
                "---------------------------------------------------------------------------------------------");
        for (CoachData coachdata : coaches) {
            System.out.printf("%-10s%-15s%-10d%-20s%-30s%-10s\n", coachdata.getName(), coachdata.getSurname(),
                    coachdata.getAge(), coachdata.getType(), coachdata.getEmail(), coachdata.getPhoneNumber());
        }
        profile.menuObj.backToMenu();
    }
}