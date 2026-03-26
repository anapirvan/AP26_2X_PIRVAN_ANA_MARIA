package advanced;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Item item1 = new Item("knuth67", "The Art of Computer Programming", "C:\\Users\\User-PC\\Downloads\\class-diagram.png");
        Item item2 = new Item("jvm25", "The Java Virtual Machine Specification", "https://docs.oracle.com/javase/specs/jvms/se25/html/index.html");
        Item item3 = new Item("java25", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se25/jls25.pdf");

        item1.addConcept("Algorithm design techniques");
        item1.addConcept("Graph theory");

        item2.addConcept("Object-oriented programming");
        item2.addConcept("Neural Networks");

        item3.addConcept("Object-oriented programming");
        item3.addConcept("Algorithm design techniques");

        Catalog catalog = new Catalog("catalog");

        catalog.add(item1);
        catalog.add(item2);
        catalog.add(item3);

        Set<String> C = new HashSet<>(Set.of("Graph theory", "Neural Networks", "Algorithm design techniques", "Object-oriented programming"));

        //List<Item> result=minSetCover(C,catalog);
        /*for(Item item:result){
            System.out.println(item);
        }*/

        ProblemGenerator problemGenerator = new ProblemGenerator();
        problemGenerator.test();
    }

    public static List<Item> minSetCover(Set<String> C, Catalog catalog) {
        Set<String> uncovered = new HashSet<>(C);
        List<Item> result = new ArrayList<>();
        List<Item> remaining = new ArrayList<>(catalog.getItems());

        while (!uncovered.isEmpty() && !remaining.isEmpty()) {
            Item best = null;
            Set<String> bestCovered = new HashSet<>();

            for (Item item : remaining) {
                Set<String> covered = new HashSet<>(item.getConcepts());
                covered.retainAll(uncovered);
                if (covered.size() > bestCovered.size()) {
                    best = item;
                    bestCovered = covered;
                }
            }

            if (best == null || bestCovered.isEmpty()) break;

            result.add(best);
            uncovered.removeAll(bestCovered);
            remaining.remove(best);
        }

        return result;
    }

}


