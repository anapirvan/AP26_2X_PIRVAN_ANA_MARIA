package compulsory;

import lombok.AllArgsConstructor;
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

    public void display(){
        for(Item item:items){
            System.out.println(item);
        }
    }
}
