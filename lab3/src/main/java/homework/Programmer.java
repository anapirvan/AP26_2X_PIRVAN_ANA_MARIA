package homework;

public class Programmer extends Person {
    private String programmingArea;

    public Programmer(int id, String name, String birthDate, String programmingArea) {
        super(name, id, birthDate);
        this.programmingArea = programmingArea;
    }
}
