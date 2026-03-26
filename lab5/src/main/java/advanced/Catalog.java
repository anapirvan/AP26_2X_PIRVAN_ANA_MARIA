package advanced;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter

public class Catalog {
    private String name;
    private List<Item> items = new ArrayList<>();

    public Catalog(String name) {
        this.name = name;
    }

    public void add(Item item) {
        items.add(item);
    }

    public void display() {
        if (items.isEmpty()) {
            System.out.println("Catalog is empty!");
            return;
        }
        for (Item item : items) {
            System.out.println(item);
        }
    }

    public Item findById(String id) {
        return items.stream()
                .filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }
}


