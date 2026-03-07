package compulsory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class Person implements Profile,Comparable<Person>{
    private int id;
    private String name;

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }
}
