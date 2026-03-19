package homework;

import lombok.Getter;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.spanning.PrimMinimumSpanningTree;
import org.graph4j.spanning.WeightedSpanningTreeIterator;

import java.util.*;

@Getter

public class City {
    private final String name;
    private final Map<Intersection, List<Street>> cityMap = new HashMap<>();
    private final Map<Integer, Intersection> reverse = new HashMap<>();


    public City(String name) {
        this.name = name;
    }

    public void addIntersection(Intersection intersection) {
        if (!cityMap.containsKey(intersection)) {
            cityMap.put(intersection, new ArrayList<>());
        }
    }

    public void addStreet(Street street) {
        Intersection from = street.getFrom();
        Intersection to = street.getTo();

        if (cityMap.containsKey(from)) {
            cityMap.get(from).add(street);
        } else {
            System.out.println("Nu exista intersectia in map!");
        }
        if (cityMap.containsKey(to)) {
            cityMap.get(to).add(street);
        } else {
            System.out.println("Nu exista intersectia in map!");
        }
    }

    public void filterStreets(float minLength) {
        cityMap.values().stream()
                .flatMap(Collection::stream)
                .distinct()
                .filter(street -> street.getLength() > minLength)
                .filter(street -> {
                    Intersection from = street.getFrom();
                    List<Street> streetsFromThere = cityMap.get(from);

                    Intersection to = street.getTo();
                    List<Street> streetsToThere = cityMap.get(to);
                    return streetsFromThere.size() >= 3 || streetsToThere.size() >= 3;
                })
                .forEach(System.out::println);
    }

    //alg
    public Graph buidGraph() {
        Map<Intersection, Integer> intersectionToInteger = new HashMap<>();
        int nr = 0;
        for (Intersection intersection : cityMap.keySet()) {
            intersectionToInteger.put(intersection, nr);
            reverse.put(nr, intersection);
            nr++;
        }

        GraphBuilder graphTemp = GraphBuilder.numVertices(intersectionToInteger.size());

        cityMap.values()
                .stream()
                .flatMap(Collection::stream)
                .distinct()
                .forEach(street -> graphTemp.addEdge((int) intersectionToInteger.get(street.getFrom()), (int) intersectionToInteger.get(street.getTo())));

        Graph graph = graphTemp.buildGraph();

        cityMap.values()
                .stream()
                .flatMap(Collection::stream)
                .distinct()
                .forEach(street -> graph.setEdgeWeight(intersectionToInteger.get(street.getFrom()), intersectionToInteger.get(street.getTo()), street.getLength()));

        return graph;
    }

    public Street getStreetBetween(Intersection from, Intersection to) {
        for (Street street : cityMap.get(from)) {
            if (street.getFrom().equals(to) || street.getTo().equals(to)) {
                return street;
            }
        }
        return null;
    }

    public void calculateDisplayMST(Graph graph) {
        Graph MST = new PrimMinimumSpanningTree(graph).getTree();

        for (int node1 : MST.vertices()) {
            for (var it = MST.neighborIterator(node1); it.hasNext(); ) {
                int node2 = it.next();

                if (node1 < node2) {
                    Intersection from = reverse.get(node1);
                    Intersection to = reverse.get(node2);

                    Street street = getStreetBetween(from, to);
                    System.out.println(street);
                }
            }
        }
    }

    public void otherMSTs(int nr) {
        Graph graph = buidGraph();
        WeightedSpanningTreeIterator it = new WeightedSpanningTreeIterator(graph);

        for (int i = 0; i < nr; i++) {
            System.out.println("nr " + (i + 1));
            var edges = it.next();

            for (var edge : edges) {
                int node1 = edge.source();
                int node2 = edge.target();

                Intersection from = reverse.get(node1);
                Intersection to = reverse.get(node2);

                Street street = getStreetBetween(from, to);
                System.out.println(street);
            }
        }
    }

}



