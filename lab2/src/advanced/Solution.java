package advanced;


import homework.Location;
import homework.Road;

import java.util.*;

public class Solution {
    private Problem pb;
    private boolean time;

    public Solution(Problem pb, boolean time) {
        this.pb = pb;
        this.time = time;
    }

    public void solve(){
        Location start=pb.getStart();
        Location end=pb.getEnd();

        Map<Location,Float> dist=new HashMap<>();
        PriorityQueue<Location> pq=new PriorityQueue<>(Comparator.comparing(dist::get));
        Map<Location,Location> parent=new HashMap<>();

        for(Location location:pb.getLocations()){
            dist.put(location,Float.MAX_VALUE);
        }
        dist.put(start,0f);
        pq.add(start);

        while(!pq.isEmpty()){
            Location l1=pq.poll();
            if(l1.equals(end)){
                break;
            }
            for(Road road: pb.getRoads()){
                Location l2=null;

                if(l1.equals(road.getFrom())) {
                    l2 = road.getTo();
                } else if (l1.equals(road.getTo())) {
                    l2 = road.getFrom();
                }

                if(l2!=null){
                    float weight;
                    if(time){
                        weight= road.getLength()/road.getSpeedLimit();
                    }
                    else{
                        weight=road.getLength();
                    }

                    if(dist.get(l1)+weight<dist.get(l2)){
                        dist.put(l2,dist.get(l1)+weight);
                        pq.add(l2);
                        parent.put(l2,l1);
                    }
                }
            }
        }
        List<Location> path=new LinkedList<>();
        Location l=end;
        while(l!=null){
            path.add(0,l);
            l=parent.get(l);
        }

        if(path.isEmpty()|| !path.get(0).equals(start)){
            System.out.println("Nu exista drum!");
        }
        else {
            for (Location location : path) {
                System.out.println(location.getName());
            }
        }
    }
}
