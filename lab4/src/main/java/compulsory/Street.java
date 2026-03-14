package compulsory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class Street implements Comparable<Street>{
    private String name;
    private float length;
    private Intersection intersection1,intersection2;

    @Override
    public int compareTo(Street o) {
        return Float.compare(this.length,o.length);
    }

    @Override
    public String toString() {
        return "Street{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", intersection1=" + intersection1 +
                ", intersection2=" + intersection2 +
                '}';
    }
}
