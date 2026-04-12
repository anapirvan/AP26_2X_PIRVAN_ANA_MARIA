package homework;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter

public class Actor {
    private int id;
    private String firstName, lastName, birthDate, nationality;

    public Actor(String firstName, String lastName, String birthDate, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
