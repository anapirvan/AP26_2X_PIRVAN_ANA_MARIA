package advanced;

import java.util.*;

import static advanced.Main.minSetCover;

public class ProblemGenerator {
    public void test() {

        int[] conceptCounts  = {10, 50, 100, 500};
        int[] resourceCounts = {20, 100, 200, 1000};

        Random random = new Random();

        for (int i = 0; i < conceptCounts.length; i++) {
            int nrConcepts  = conceptCounts[i];
            int nrResources = resourceCounts[i];

            Set<String> concepts = new HashSet<>();
            for (int j = 0; j < nrConcepts; j++) {
                concepts.add("Concept" + j);
            }

            Catalog catalog = new Catalog("test");
            List<String> conceptList = new ArrayList<>(concepts);

            for (int j = 0; j < nrResources; j++) {
                Item item = new Item("item" + j, "resource " + j, "location" + j);

                int numTags = random.nextInt(5) + 1;
                for (int k = 0; k < numTags; k++) {
                    String randomConcept = conceptList.get(random.nextInt(conceptList.size()));
                    item.addConcept(randomConcept);
                }
                catalog.add(item);
            }

            long t1 = System.currentTimeMillis();
            List<Item> result = minSetCover(concepts, catalog);
            long t2 = System.currentTimeMillis();


            System.out.printf("Concepts: %d | Resources: %d | Result size: %3d | Time: %3d ms%n",
                    nrConcepts, nrResources, result.size(), t2-t1);
        }
    }
}
