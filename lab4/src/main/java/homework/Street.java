package homework;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class Street implements Comparable<Street> {
    private String name;
    private float length;
    private Intersection from, to;

    @Override
    public int compareTo(Street o) {
        return Float.compare(this.length, o.length);
    }

    @Override
    public String toString() {
        return "Street{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}

