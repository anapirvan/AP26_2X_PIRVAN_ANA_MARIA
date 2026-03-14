package advanced;

import homework.Company;
import homework.Person;
import homework.Profile;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor

public class SocialNetwork {
    private List<Profile> profiles = new ArrayList<>();


    //Tarjan
    private Set<Profile> visited;
    private Map<Profile, Integer> tin;
    private Map<Profile, Integer> low;
    private Set<Profile> cutPoints;
    private int[] timer;


    private Deque<Profile[]> edgeStack = new ArrayDeque<>();
    private List<Set<Profile>> biconnectedComponents;


    public void addProfile(Profile profile) {
        profiles.add(profile);
    }

    public int computeImportance(Profile profile) {
        if (profile instanceof Person person) {
            return person.getRelationships().size();
        }
        if (profile instanceof Company company) {
            int count = 0;
            for (Profile p : profiles) {
                if (p instanceof Person person && person.getRelationships().containsKey(company)) {
                    count++;
                }
            }
            return count;
        }
        return 0;
    }

    public void printNetwork() {
        profiles.sort(Comparator.comparingInt(p -> -computeImportance(p)));

        for (Profile profile : profiles) {
            System.out.println(profile.getName() + " [importance: " + computeImportance(profile) + "]");

            if (profile instanceof Person person) {
                for (Map.Entry<Profile, String> entry : person.getRelationships().entrySet()) {
                    System.out.println("  -> " + entry.getKey().getName() + " (" + entry.getValue() + ")");
                }
            }
        }
    }

    private void dfs(Profile v, Profile parent, Map<Profile, Set<Profile>> adj) {
        visited.add(v);
        tin.put(v, timer[0]);
        low.put(v, timer[0]);
        timer[0]++;

        int children = 0;
        for (Profile to : adj.get(v)) {
            if (to.equals(parent)) continue;
            if (visited.contains(to)) {
                if (tin.get(to) < tin.get(v)) {
                    edgeStack.push(new Profile[]{v, to});
                    low.put(v, Math.min(low.get(v), tin.get(to)));
                }
            } else {
                edgeStack.push(new Profile[]{v, to});
                dfs(to, v, adj);
                low.put(v, Math.min(low.get(v), low.get(to)));

                if (low.get(to) >= tin.get(v)) {
                    Set<Profile> component = new HashSet<>();
                    while (true) {
                        Profile[] edge = edgeStack.pop();
                        component.add(edge[0]);
                        component.add(edge[1]);
                        if (edge[0].equals(v) && edge[1].equals(to)) break;
                    }
                    biconnectedComponents.add(component);

                    if (parent != null) {
                        cutPoints.add(v);
                    }
                }
                children++;
            }
        }
        if (parent == null && children > 1) {
            cutPoints.add(v);
        }
    }

    public List<Set<Profile>> findBiconnectedComponents() {
        visited = new HashSet<>();
        tin = new HashMap<>();
        low = new HashMap<>();
        cutPoints = new HashSet<>();
        timer = new int[]{0};
        edgeStack = new ArrayDeque<>();
        biconnectedComponents = new ArrayList<>();

        Map<Profile, Set<Profile>> adj = buildGraph();
        for (Profile profile : profiles) {
            if (!visited.contains(profile)) {
                dfs(profile, null, adj);
            }
        }

        return biconnectedComponents;
    }

    private Map<Profile, Set<Profile>> buildGraph() {
        Map<Profile, Set<Profile>> graph = new HashMap<>();

        for (Profile profile : profiles) {
            graph.put(profile, new HashSet<>());
        }

        for (Profile profile : profiles) {
            if (profile instanceof Person person) {
                for (Profile neighbor : person.getRelationships().keySet()) {
                    graph.get(person).add(neighbor);
                    if (graph.containsKey(neighbor)) {
                        graph.get(neighbor).add(person);
                    }
                }
            }
        }

        return graph;
    }

    public List<Profile> findCutpoints() {
        visited = new HashSet<>();
        tin = new HashMap<>();
        low = new HashMap<>();
        cutPoints = new HashSet<>();
        timer = new int[]{0};
        edgeStack = new ArrayDeque<>();
        biconnectedComponents = new ArrayList<>();

        Map<Profile, Set<Profile>> adj = buildGraph();
        for (Profile profile : profiles) {
            if (!visited.contains(profile)) {
                dfs(profile, null, adj);
            }
        }

        return new ArrayList<>(cutPoints);
    }


}

