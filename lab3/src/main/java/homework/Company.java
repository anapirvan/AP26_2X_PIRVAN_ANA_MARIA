package homework;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class Company implements Profile, Comparable<Company> {
    private int id;
    private String name;

    @Override
    public int compareTo(Company o) {
        return this.name.compareTo(o.name);
    }
}
