package homework;
import java.util.*;

/**
 * Gestioneaza seturile de locatii si drumuri.
 */
public class Problem {
    private Set<Location> locations = new HashSet<>();
    private Set<Road> roads = new HashSet<>();

    /**
     * Adauga o noua locatie in sistem.
     * @param location obiectul de adaugat
     * Afiseaza un mesaj daca locatia exista deja.
     */
    public void addLocation(Location location){
        if(!locations.add(location)){
            System.out.println("Location already exists!");
        }
    }

    /**
     * Adauga un nou drum in sistem.
     * @param road obiectul de adaugat
     * Afiseaza un mesaj daca drumul deja exista.
     */
    public void addRoad(Road road){
        if(!roads.add(road)){
            System.out.println("Road already exists");
        }
    }

    /**
     * Verifica daca datele introdus sunt valide.
     * O instanta este considerata valida daca:
     * 1. Lungimea fiecărui drum este mai mare sau egală cu distanța euclidiană dintre capete.
     * 2. Capetele drumurilor se regăsesc în setul de locații al problemei.
     * @return true daca toate conditiile sunt indeplinite, false altfel.
     */
    public boolean isValid(){
        for(Road road:roads){
            Location from=road.getFrom();
            Location to=road.getTo();

            double euclidianDistance=Math.sqrt(Math.pow((to.getX()-from.getX()),2)+Math.pow((to.getY()-from.getY()),2));
            if(road.getLength()<euclidianDistance){
                System.out.println("Invalid instance!");
                return false;
            }
            if(!locations.contains(to) || !locations.contains(from)){
                System.out.println("Invalid instance");
                return false;
            }
        }

        return true;
    }

    /**
     * Verifica daca exista o cale de acces intre 2 locatii folosind bfs.
     * @param start Locatia de unde incepe cautarea.
     * @param end locatia destinatie
     * @return True daca exista cel putin o cale de acces intre aceste 2 locatii, false altfel.
     */
    public boolean canReach(Location start,Location end){
        if(start.equals(end)){
            return true;
        }

        Map<Location,Set<Location>> listaAdiacenta=new HashMap<>();

        for(Location location:locations){
            listaAdiacenta.put(location,new HashSet<>());
        }

        for(Road road:roads){
            Location from=road.getFrom();
            Location to=road.getTo();

            listaAdiacenta.get(from).add(to);
            listaAdiacenta.get(to).add(from);
        }

        Queue<Location> queue=new LinkedList<>();
        Set<Location> visited=new HashSet<>();
        queue.add(start);
        visited.add(start);

        while(!queue.isEmpty()){
            Location curr=queue.poll();

            Set<Location> neighbours=listaAdiacenta.get(curr);
            for(Location location:neighbours){
                if(location.equals(end)){
                    return true;
                }
                if(!visited.contains(location)){
                    queue.add(location);
                    visited.add(location);
                }
            }
        }
        return false;
    }
}
