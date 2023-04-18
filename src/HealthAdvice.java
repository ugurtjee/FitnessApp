public class HealthAdvice {

    public void giveAdvice(Profile profileObj) {
        double JongAankomen = 18.5 * profileObj.GetLength() * profileObj.GetLength();
        double JongAankomenVerschil = Math.round((JongAankomen - profileObj.GetWeight()) * 100) / 100.0;
        double JongAfvallen = 24.99 * profileObj.GetLength() * profileObj.GetLength();
        double JongAfvallenVerschil = Math.round((profileObj.GetWeight() - JongAfvallen) * 100) / 100.0;
        double OudAankomen = 22 * profileObj.GetLength() * profileObj.GetLength();
        double OudAankomenVerschil = Math.round((OudAankomen - profileObj.GetWeight()) * 100) / 100.0;
        double OudAfvallen = 27.99 * profileObj.GetLength() * profileObj.GetLength();
        double OudAfvallenVerschil = Math.round((profileObj.GetWeight() - OudAfvallen) - 100) / 100.0;

        if (profileObj.GetAge() < 70) {
            if (profileObj.GetBMI() < 17.0) {
                System.out.println(
                        "Je gewicht is veel te laag. Dit is niet goed voor je gezondheid. Neem contact op met je huisarts om de mogelijke oorzaak vast te stellen.");
                System.out.println(
                        "Je moet minimaal " + JongAankomenVerschil + " kilo aankomen voor een gezonde gewicht");
            }
            if (17.0 <= profileObj.GetBMI() & profileObj.GetBMI() < 18.5) {
                System.out.println(
                        "Je gewicht is te laag. Dat hoeft niet erg te zijn. Je kunt een Eetmeter invullen om te zien of je genoeg binnenkrijgt.");
                System.out.println(
                        "Is dit niet het geval, wil je aankomen of ben je ongewenst afgevallen? Maak dan een afspraak met je huisarts om dit te bespreken.");
                System.out.println("Voor een gezonde BMI moet u minimaal " + JongAankomenVerschil + " kilo aankomen.");
            }
            if (18.5 <= profileObj.GetBMI() & profileObj.GetBMI() < 25.0)
                System.out.println(
                        "Je gewicht is gezond. Mooi zo! Blijf gezond eten en voldoende bewegen om dat zo te houden.");
            if (25.0 <= profileObj.GetBMI() & profileObj.GetBMI() < 30.0) {
                System.out.println(
                        "Je gewicht is te hoog. Probeer af te vallen of in elk geval niet aan te komen door gezond te eten en voldoende te bewegen.");
                System.out.println(
                        "Je kunt samen met je huisarts bespreken of afvallen nodig is. Voor een gezonde BMI moet u minimaal "
                                + JongAfvallenVerschil + " kilo afvallen.");
            }
            if (30.0 <= profileObj.GetBMI()) {
                System.out.println(
                        "Je gewicht is veel te hoog. Het is beter voor je gezondheid om af te vallen. Overleg met je huisarts voor advies of een doorverwijzing voor hulp bij afvallen.");
                System.out.println("Voor een gezonde BMI moet u minimaal " + JongAfvallenVerschil + " kilo afvallen.");
            }
        }
        if (profileObj.GetAge() >= 70) {
            if (profileObj.GetBMI() < 22.0) {
                System.out.println(
                        "Je gewicht is te laag. Dat hoeft niet erg te zijn. Je kunt een Eetmeter invullen om te zien of je genoeg binnenkrijgt.");
                System.out.println(
                        "Is dit niet het geval, wil je aankomen of ben je ongewenst afgevallen? Maak dan een afspraak met je huisarts om dit te bespreken.");
                System.out.println("Voor een gezonde BMI moet u minimaal " + OudAankomenVerschil + " kilo aankomen.");
            }
            if (22.0 <= profileObj.GetBMI() & profileObj.GetBMI() < 28.0)
                System.out.println(
                        "Je gewicht is gezond. Mooi zo! Blijf gezond eten en voldoende bewegen om dat zo te houden.\n");
            if (28.0 <= profileObj.GetBMI() & profileObj.GetBMI() < 30.0) {
                System.out.println(
                        "Je gewicht is te hoog. Probeer af te vallen of in elk geval niet aan te komen door gezond te eten en voldoende te bewegen.");
                System.out.println(
                        "Je kunt samen met je huisarts bespreken of afvallen nodig is. Voor een gezonde BMI moet u minimaal "
                                + OudAfvallenVerschil + " kilo afvallen.");
            }
            if (30.0 <= profileObj.GetBMI()) {
                System.out.println(
                        "Je gewicht is veel te hoog. Het is beter voor je gezondheid om af te vallen. Overleg met je huisarts voor advies of een doorverwijzing voor hulp bij afvallen.");
                System.out.println("Voor een gezonde BMI moet u minimaal " + OudAfvallenVerschil + " kilo afvallen.");
            }
        }
        profileObj.menuObj.backToMenu();
    }
}