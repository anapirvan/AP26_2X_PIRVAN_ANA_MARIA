package homework;

public class Designer extends Person {
    private String designSpecialty;

    public Designer(int id, String name, String birthDate, String designSpecialty) {
        super(name, id, birthDate);
        this.designSpecialty = designSpecialty;
    }
}
