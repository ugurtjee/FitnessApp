public class CoachData {
    private String name;
    private String surname;
    private int age;
    private String type;
    private String email;
    private String phoneNumber;

    public CoachData(String name, String surname, int age, String type, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.type = type;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}