package advanced;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter

public class Item {
    private String id;
    private String title;
    private String location;
    private String year, author;

    private List<String> concepts=new ArrayList<>();

    public Item(String id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location;
    }

    public Item(String id, String title, String location, String year, String author) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.year = year;
        this.author = author;
    }

    public void addConcept(String concept){
        concepts.add(concept);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", year='" + year + '\'' +
                ", author='" + author + '\'' +
                ", concepts=" + concepts +
                '}';
    }
}

