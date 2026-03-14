package homework;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter

public class Person implements Profile, Comparable<Person> {
    protected int id;
    protected String name, birthDate;
    private Map<Profile, String> relationships = new HashMap<>();

    public Person(String name, int id, String birthDate) {
        this.name = name;
        this.id = id;
        this.birthDate = birthDate;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }

    public void addRelationship(Profile p, String value) {
        relationships.put(p, value);
    }
}
