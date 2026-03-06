package advanced;

import homework.Location;
import homework.Road;

import java.util.*;

public class Problem {
    private Set<Location> locations = new HashSet<>();
    private Set<Road> roads = new HashSet<>();
    private Location start,end;

    public void addLocation(Location location){
        if(!locations.add(location)){
            System.out.println("Location already exists!");
        }
    }

    public void addRoad(Road road){
        if(!roads.add(road)){
            System.out.println("Road already exists");
        }
    }

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

    public Set<Location> getLocations() {
        return locations;
    }
    public Set<Road> getRoads() {
        return roads;
    }
    public Location getStart() {
        return start;
    }
    public Location getEnd() {
        return end;
    }

    public void setStart(Location start) {
        this.start = start;
    }
    public void setEnd(Location end) {
        this.end = end;
    }
}

