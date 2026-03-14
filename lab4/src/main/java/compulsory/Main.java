package compulsory;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        var intersections = IntStream.rangeClosed(0, 9)
                .mapToObj(i -> new Intersection("intersection" + i) )
                .toArray(Intersection[]::new);
        var streets=IntStream.rangeClosed(0,8)
                .mapToObj(i->new Street("street"+i,i*2,intersections[i],intersections[i+1]))
                .toArray(Street[]::new);

        List<Street> streetList=new LinkedList<>();
        streetList.addAll(Arrays.asList(streets));

        Collections.sort(streetList, Comparator.comparing(Street::getLength));

        Set<Intersection> intersectionSet=new HashSet<>();
        intersectionSet.addAll(Arrays.asList(intersections));

        for(Street street:streetList){
            System.out.println(street);
        }

        for(Intersection intersection:intersectionSet){
            System.out.println(intersection);
        }

    }
}
